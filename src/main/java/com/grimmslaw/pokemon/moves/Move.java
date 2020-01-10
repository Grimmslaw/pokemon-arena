package com.grimmslaw.pokemon.moves;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grimmslaw.pokemon.battle.BattleContext;
import com.grimmslaw.pokemon.model.AttackResult;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.pokemon.DualTypePokemon;
import com.grimmslaw.pokemon.types.Type;
import com.grimmslaw.pokemon.util.MathUtilities;
import com.grimmslaw.pokemon.util.PrettyPrintable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The parent class to all move classes.
 *
 * This class provides the basic mechanics that all move subclasses require.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public abstract class Move implements PrettyPrintable {

    private static final Logger logger = LogManager.getLogger(Move.class);

    private String name;
    private Type type;
    private PowerPoints pp;
    private int power;
    private int accuracy;
    private int priority;
    private MoveEffect moveEffect;

    public Move(String name, Type type, PowerPoints pp, int power, int accuracy, int priority, MoveEffect moveEffect) {
        this.name = name;
        this.type = type;
        this.pp = pp;
        this.power = power;
        this.accuracy = accuracy;
        this.priority = priority;
        this.moveEffect = moveEffect;
    }

    public Move(String name, Type type, PowerPoints pp, int power, int accuracy, int priority) {
        this(name, type, pp, power, accuracy, priority, null);
    }

    public Move(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority) {
        this(name, type, new PowerPoints(basePP, maxPP), power, accuracy, priority);
    }

    public Move(String name, Type type, int basePP, int maxPP, int power, int accuracy, int priority,
                MoveEffect moveEffect) {
        this(name, type, new PowerPoints(basePP, maxPP), power, accuracy, priority, moveEffect);
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public PowerPoints getPp() {
        return pp;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getPriority() {
        return priority;
    }

    public boolean hasEffect() {
        return getMoveEffect() != null;
    }

    public MoveEffect getMoveEffect() {
        return moveEffect;
    }

    public double calculateTypeEffectiveness(Pokemon defender) {
        if (defender instanceof DualTypePokemon) {
            return getType().checkEffect(defender.getType(), ((DualTypePokemon) defender).getTypeSecond());
        } else {
            return getType().checkEffect(defender.getType());
        }
    }

    public boolean attackDoesHit(Pokemon attacker, Pokemon defender) {
        double thresholdToHit = MathUtilities.calculateHitThreshold(attacker, defender, this);
        int hitPercent = ThreadLocalRandom.current().nextInt(0, 101);

        logger.debug("Attack does " + ((hitPercent <= thresholdToHit) ? "" : "not ") + "hit.");
        return hitPercent <= thresholdToHit;
    }

    public AttackResult doAttack(Pokemon attacker, Pokemon defender, BattleContext battleContext) {
        boolean attackShouldHit = attackDoesHit(attacker, defender);
        int damageToDeal = (int) MathUtilities.calculateDamage(this, attacker, defender,
                battleContext.getNumberOfTargets(), battleContext.getCurrentWeather(), battleContext.isCritical(),
                battleContext.getOtherMultipliers());
        MoveEffect effectToApply = getMoveEffect();

        return new AttackResult(attacker, defender, attackShouldHit, damageToDeal, effectToApply);
    }

    // TODO: move this to supervisor
    public void applyEffect() {
        // TODO: hasEffect() should be checked already by the time this method is called
        // TODO: apply the effect
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Move.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("type=" + type)
                .add("pp=" + pp)
                .add("power=" + power)
                .add("accuracy=" + accuracy)
                .add("priority=" + priority)
                .add("moveEffect=" + moveEffect)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return power == move.power &&
                accuracy == move.accuracy &&
                priority == move.priority &&
                Objects.equals(moveEffect, move.moveEffect) &&
                Objects.equals(name, move.name) &&
                type == move.type &&
                Objects.equals(pp, move.pp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, pp, power, accuracy, priority, moveEffect);
    }

}
