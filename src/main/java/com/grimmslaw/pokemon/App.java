package com.grimmslaw.pokemon;

import com.grimmslaw.pokemon.abilities.Ability;
import com.grimmslaw.pokemon.attributes.StatSet;
import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.exceptions.moves.MovesetException;
import com.grimmslaw.pokemon.model.StatMap;
import com.grimmslaw.pokemon.moves.Move;
import com.grimmslaw.pokemon.moves.Moveset;
import com.grimmslaw.pokemon.moves.PhysicalMove;
import com.grimmslaw.pokemon.moves.PowerPoints;
import com.grimmslaw.pokemon.natures.Nature;
import com.grimmslaw.pokemon.pokemon.DualTypePokemon;
import com.grimmslaw.pokemon.pokemon.Pokemon;
import com.grimmslaw.pokemon.types.Type;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger = LogManager.getLogger(Move.class);

    public static void main( String[] args ) {

        StatMap<Integer> statMap = StatMap.getIntegerStatMap(100, 100, 100, 100, 100, 100);
        PowerPoints pp = new PowerPoints(35, 56);
        Move tackle = new PhysicalMove("Tackle", Type.NORMAL, pp, 40, 100, 1);
        Moveset moveset;
        try {
            moveset = new Moveset(tackle);
        } catch (MovesetException me) {
            logger.warn("Moveset exception creating tackle");
            moveset = new Moveset();
        }

        Pokemon charizard = new DualTypePokemon("Charizard",
                new StatSet(78.0, 81.0, 78.0, 109.0, 85.0, 100.0),
                Type.FIRE, Type.FLYING, 100, Nature.BRAVE, statMap, Ability.PRISM_ARMOR, moveset);

    }

}
