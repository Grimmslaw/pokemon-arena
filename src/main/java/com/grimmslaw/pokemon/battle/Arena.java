package com.grimmslaw.pokemon.battle;

import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.model.PokeMove;
import com.grimmslaw.pokemon.moves.Move;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.teams.Team;
import com.grimmslaw.pokemon.util.MathUtilities;
import com.grimmslaw.pokemon.util.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Arena {

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

    private PokeMove getFirstMover(PokeMove pokeMove1, PokeMove pokeMove2) {
        if (pokeMove1.getMove().getPriority() > pokeMove2.getMove().getPriority()) {
            return pokeMove1;
        } else if (pokeMove1.getMove().getPriority() < pokeMove1.getMove().getPriority()) {
            return pokeMove2;
        }

        int statComparisonValue = MathUtilities.compareStageAdjustedStat(
                pokeMove1.getPokemon(), pokeMove2.getPokemon(), Stat.SPEED);
        if (statComparisonValue > 0) {
            return pokeMove1;
        } else if (statComparisonValue < 0) {
            return pokeMove2;
        } else {
            // equal priorities and equal speeds mean the first mover is chosen at random
            return ThreadLocalRandom.current().nextInt(0,2) == 0 ? pokeMove1 : pokeMove2;
        }
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

    private void doOneTurnOfAttacks(PokeMove pokeMove1, PokeMove pokeMove2) {
        PokeMove firstMover = getFirstMover(pokeMove1, pokeMove2);
        PokeMove secondMover = (firstMover.equals(pokeMove1)) ? pokeMove2 : pokeMove1;

        if (firstMover.getMove().attackDoesHit(firstMover.getPokemon(), secondMover.getPokemon())) {

        }
    }

    public void tick() {
        logger.trace("tick() called");
        // TODO: do one full turn and resolve effects
    }

}
