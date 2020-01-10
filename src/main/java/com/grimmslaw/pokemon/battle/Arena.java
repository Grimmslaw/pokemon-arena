package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.model.AttackResult;
import com.grimmslaw.pokemon.model.PokeMove;
import com.grimmslaw.pokemon.moves.MoveEffect;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.teams.Team;
import com.grimmslaw.pokemon.util.MathUtilities;
import com.grimmslaw.pokemon.util.PrettyPrintable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Arena implements PrettyPrintable {

    private static final Logger logger = LogManager.getLogger(Arena.class);

    private Supervisor supervisor;
    private Weather currentWeather;
    private Team team1;
    private Team team2;

    public Arena() {
        supervisor = Supervisor.getInstance();
        currentWeather = new Weather();
        team1 = new Team();
        team2 = new Team();

        logger.trace("New Arena created");
    }

    // TODO: I think this is wrong; it doesn't account for the target of each move
    private List<PokeMove> determineAttackOrder(PokeMove... pokeMoves) {
        List<PokeMove> listToReturn = Arrays.asList(pokeMoves);
        listToReturn.sort(PokeMove::comparePokeMoves);
        return listToReturn;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    private List<PokeMove> readInPokeMoves() {
        // TODO:
        return new ArrayList<>();
    }

    private boolean attackIsCritical(Pokemon attacker) {
        double criticalChance = MathUtilities.calculateCritChanceStageAdjusted(attacker);
        // TODO: check this logic
        return criticalChance >= ThreadLocalRandom.current().nextDouble(0,2);
    }

    private AttackResult doOneAttack(PokeMove attack) {
        AttackResult attackResult;

        if (attack.getMove().attackDoesHit(attack.getAttacker(), attack.getTarget())) {
            BattleContext battleContext = new BattleContext(1, getCurrentWeather(),
                    attackIsCritical(attack.getAttacker()), 1.0);
            attackResult = attack.getMove().doAttack(attack.getAttacker(),
                    attack.getTarget(), battleContext);
        } else {
            return new AttackResult(attack.getAttacker(), attack.getTarget(),false,
                    0, new MoveEffect());
        }

        return attackResult;
    }

    private List<AttackResult> doOneTurnOfAttacks(List<PokeMove> pokeMoves) {
        List<AttackResult> attackResults = new ArrayList<>();
        for (PokeMove pokeMove : pokeMoves) {
            attackResults.add(doOneAttack(pokeMove));
        }
        return attackResults;
    }

    public void tick() {
        logger.trace("tick() called");
        List<PokeMove> pokeMoves = readInPokeMoves();
        List<AttackResult> attackResults = doOneTurnOfAttacks(pokeMoves);
        supervisor.resolveOneTurnOfAttackResults(attackResults);
    }

}
