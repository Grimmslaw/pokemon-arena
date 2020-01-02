package com.grimmslaw.pokemon.util;

import com.grimmslaw.pokemon.abilities.Ability;
import com.grimmslaw.pokemon.battle.Weather;
import com.grimmslaw.pokemon.battle.Weather.WeatherType;
import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.moves.Move;
import com.grimmslaw.pokemon.moves.PhysicalMove;
import com.grimmslaw.pokemon.moves.SpecialMove;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.pokemon.DualTypePokemon;
import com.grimmslaw.pokemon.statuses.containers.NonVolatileStatusContainer.NonVolatileStatusType;
import com.grimmslaw.pokemon.types.Type;

import java.util.concurrent.ThreadLocalRandom;

public class MathUtilities {

    private static double determineWeatherMultiplier(Move move, Weather weather) {
        WeatherType currentWeather = weather.getCurrentWeather();
        if (move.getType() == Type.WATER) {
            if (currentWeather == WeatherType.RAIN) {
                return 1.5;
            } else if (currentWeather == WeatherType.HARSH_SUNLIGHT) {
                return 0.5;
            }
        } else if (move.getType() == Type.FIRE) {
            if (currentWeather == WeatherType.HARSH_SUNLIGHT) {
                return 1.5;
            } else if (currentWeather == WeatherType.RAIN) {
                return 0.5;
            }
        }

        return 1.0;
    }

    private static double determineSameTypeAttackBonus(Move move, Pokemon attacker) {
        boolean hasAdaptability = attacker.getAbility() == Ability.ADAPTABILITY;
        if (attacker instanceof DualTypePokemon) {
            DualTypePokemon dualTypeAttacker = (DualTypePokemon) attacker;
            if (move.getType() == dualTypeAttacker.getType() || move.getType() == dualTypeAttacker.getTypeSecond()) {
                return hasAdaptability ? 2.0 : 1.5;
            }
        } else if (move.getType() == attacker.getType()) {
            return hasAdaptability ? 2.0 : 1.5;
        }

        return 1.0;
    }

    private static double determineBurnMultiplier(Pokemon attacker, Move move) {
        if ((attacker.getStatus().getCurrentNonVolatileStatus() == NonVolatileStatusType.BURN)
                && (attacker.getAbility() != Ability.GUTS)
                && (move instanceof PhysicalMove)) {
            return 0.5;
        }

        return 1.0;
    }

    private static double calculateModifier(double target, double weather, double badge, double critical, double random,
                                            double stab, double typeEffectiveness, double burn, double other) {
        return target * weather * badge * critical * random * stab * typeEffectiveness * burn * other;
    }

    public static double calculateDamage(Move move, Pokemon attacker, Pokemon defender, int numTargets,
                                         Weather weather, boolean isCritical, double otherMultiplier) {
        double attackOrSpAttackVal;
        double defenseOrSpDefenseVAl;
        if (move instanceof PhysicalMove) {
            attackOrSpAttackVal = Statistics.getStatValueIncludingStage(Stat.ATTACK, attacker);
            defenseOrSpDefenseVAl = Statistics.getStatValueIncludingStage(Stat.DEFENSE, defender);
        } else if (move instanceof SpecialMove) {
            attackOrSpAttackVal = Statistics.getStatValueIncludingStage(Stat.SPECIAL_ATTACK, attacker);
            // TODO: account for special moves that use defenders ATTACK instead
            defenseOrSpDefenseVAl = Statistics.getStatValueIncludingStage(Stat.SPECIAL_DEFENSE, defender);
        } else {
            // TODO: figure out what to do here?
            attackOrSpAttackVal = 1.0;
            defenseOrSpDefenseVAl = 1.0;
        }

        double targetMult = (numTargets > 1) ? 0.75 : 1.0;
        double weatherMult = determineWeatherMultiplier(move, weather);
        double badgeMult = 1.0;
        double criticalMult = isCritical ? 1.5 : 1.0;
        double randomMult = ThreadLocalRandom.current().nextDouble(0.85, 1.1);
        double stab = determineSameTypeAttackBonus(move, attacker);
        double typeEffectivenessMult = move.calculateTypeEffectiveness(defender);
        double burnMult = determineBurnMultiplier(attacker, move);
        // TODO: otherMult logic
        double otherMult = 1.0;

        double modifier = calculateModifier(targetMult, weatherMult, badgeMult, criticalMult, randomMult, stab,
                typeEffectivenessMult, burnMult, otherMult);

        return ((((((2.0*attacker.getLevel())/5.0)+2.0)*move.getPower()
                *(attackOrSpAttackVal/defenseOrSpDefenseVAl))/50)+2)*modifier;
    }

    public static int calculateHP(Pokemon pokemon) {
        return (int) Math.floor((((2*pokemon.getBaseStats().get(Stat.HIT_POINTS))+pokemon.getIVs().get(Stat.HIT_POINTS)
                +Math.floor(pokemon.getEVs().getOneEV(Stat.HIT_POINTS)/4.0))*pokemon.getLevel())/100)
                +pokemon.getLevel()+10;
    }

    public static int calculateStat(Pokemon pokemon, Stat statToSet) {
        return (int) (Math.floor(Math.floor(((2*pokemon.getBaseStats().get(statToSet))
                +pokemon.getIVs().get(statToSet)+Math.floor(pokemon.getEVs().getOneEV(statToSet)/4.0))
                *pokemon.getLevel()/100)+5)*pokemon.getNature().getMultiplierForStat(statToSet));
    }

    private static double calculateStatAdjustedStage(Pokemon attacker, Stat stat) {
        double statValue = attacker.getCurrentStats().get(stat);
        double statStageMultiplier = Statistics.getStatValueIncludingStage(stat, attacker);
        return statValue * statStageMultiplier;
    }

    public static int compareStageAdjustedStat(Pokemon pokemon1, Pokemon pokemon2, Stat stat) {
        double pokemon1AdjustedStat = calculateStatAdjustedStage(pokemon1, stat);
        double pokemon2AdjustedStat = calculateStatAdjustedStage(pokemon2, stat);
        return Double.compare(pokemon1AdjustedStat, pokemon2AdjustedStat);
    }

    public static double determineAttackHitAdjustedStages(Pokemon attacker, Pokemon defender) {
        // TODO: account for abilities or specific moves that affect accuracy/evasion
        return (Statistics.getStatValueIncludingStage(Stat.ACCURACY, attacker)
                - Statistics.getStatValueIncludingStage(Stat.EVASION, defender));
    }

    public static double calculateCritChanceStageAdjusted(Pokemon attacker) {
        double result;
        if (attacker.getCriticalStage() < 0) {
            result = 1.0;
        } else {
            switch (attacker.getCriticalStage()) {
                case 0:
                    result = 0.0625;
                    break;
                case 1:
                    result = 0.125;
                    break;
                case 2:
                    result = 0.25;
                    break;
                case 3:
                    result = 0.3333333333333333;
                    break;
                default:
                    result = 0.5;
                    break;
            }
        }

        return result;
    }

    private static double determineAttackOtherMods(Pokemon attacker, Pokemon defender, Move move) {
        // TODO: actually create the logic for this -- abilities and special moves
        return 1.0;
    }

    public static double calculateHitThreshold(Pokemon attacker, Pokemon defender, Move move) {
        return move.getAccuracy()
                * determineAttackHitAdjustedStages(attacker, defender)
                * determineAttackOtherMods(attacker, defender, move);
    }

}
