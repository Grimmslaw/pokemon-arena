package com.grimmslaw.pokemon.pokemon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.grimmslaw.pokemon.abilities.Ability;
import com.grimmslaw.pokemon.attributes.EffortValues;
import com.grimmslaw.pokemon.attributes.IndividualValues;
import com.grimmslaw.pokemon.attributes.StatSet;
import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.model.Damageable;
import com.grimmslaw.pokemon.model.Faintable;
import com.grimmslaw.pokemon.model.StatMap;
import com.grimmslaw.pokemon.moves.Moveset;
import com.grimmslaw.pokemon.natures.Nature;
import com.grimmslaw.pokemon.statuses.Status;
import com.grimmslaw.pokemon.types.Type;
import com.grimmslaw.pokemon.util.MathUtilities;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Defines the attributes of a single-type pokemon and provides methods to initialize, set, and retrieve their values.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class Pokemon implements Damageable, Faintable {

    private static final Logger logger = LogManager.getLogger(Pokemon.class);

    protected String name;
    protected StatSet baseStats;
    protected StatSet currentStats;
    protected Type type;
    protected int level;

    protected Nature nature;
    protected Ability ability;
    protected IndividualValues ivs;
    protected EffortValues evs;
    protected Moveset moveset;

    protected int criticalStage;
    protected Status status;
    protected StatMap<Integer> statStages;

    protected boolean fainted;

    /**
     * Empty constructor.
     *
     * Sets attributes to empty or default values.
     */
    public Pokemon() {
        name = "";
        baseStats = new StatSet();
        level = 0;
        ivs = initIVs();
        evs = initEVs();
        ability = Ability.NONE;
        moveset = new Moveset();
        criticalStage = 0;
        status = new Status();
        statStages = initStatStages();
        fainted = false;
        // current stats should be manually initialized

        logger.trace("New Pokemon created with empty constructor.");
    }

    /**
     * Constructor creating a pokemon object, given the values known before initialization.
     *
     * @param name		the specific species of pokemon
     * @param baseStats	the stats this pokemon would have at level 1
     * @param type		the pokemon's type (or primary type for dual-type pokemon)
     * @param level		the pokemon's level (in the range [1-100])
     * @param nature	the pokemon's nature, affecting which stats grow faster or slower than average
     * @param evs		effort values; maximum 63 per stat and 255 total
     * @param ability	an attribute causing certain stat, battle, etc. attributes and values to differ from defaults
     * @param moveset	the (up to) four moves usable by this pokemon
     */
    public Pokemon(String name, StatSet baseStats, Type type, int level, Nature nature,
                   StatMap<Integer> evs, Ability ability, Moveset moveset) {
        this.name = name;
        this.baseStats = baseStats;
        this.type = type;
        this.level = level;
        this.nature = nature;
        this.ivs = initIVs();
        this.evs = initEVs(evs);
        this.ability = ability;
        this.moveset = moveset;
        this.criticalStage = 0;
        this.status = initStatus();
        this.currentStats = new StatSet();
        this.statStages = initStatStages();
        this.fainted = false;

        initCurrentStats();

        logger.trace("New Pokemon created: Pokemon=" + this.toGsonString());
    }

    /**
     * Creates an empty {@linkplain IndividualValues} object.
     *
     * @return an empty IndividualValues object
     */
    private IndividualValues initIVs() {
        return new IndividualValues();
    }

    /**
     * Creates an {@linkplain IndividualValues} object and initializes it with the given values.
     *
     * @param valuesMap	a map of values to populate the IndividualValues object with
     * @return the initialized and populated IndividualValues object
     */
    private IndividualValues initIVs(StatMap<Integer> valuesMap) {
        return new IndividualValues(valuesMap);
    }

    /**
     * Creates an empty {@linkplain EffortValues} object.
     *
     * @return an empty EffortValues object
     */
    private EffortValues initEVs() {
        return new EffortValues();
    }

    /**
     * Creates an {@linkplain EffortValues} object and initializes it with the given values.
     *
     * @param valuesMap	a map of values to populate the EfforValues object with
     * @return the initialized and populated EffortVAlues object
     */
    private EffortValues initEVs(StatMap<Integer> valuesMap) {
        return new EffortValues(valuesMap);
    }

    /**
     * Cycles through the pokemon's stats and sets them to zero.
     *
     * @return a map of the stat stages, having been initialized
     */
    private StatMap<Integer> initStatStages() {
        StatMap<Integer> statStages = new StatMap<>();
        for (Stat stat : Statistics.STATS) {
            statStages.put(stat, 0);
        }
        return statStages;
    }

    public String getName() {
        return name;
    }

    public StatSet getBaseStats() {
        return baseStats;
    }

    public StatSet getCurrentStats() {
        return currentStats;
    }

    public Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public Nature getNature() {
        return nature;
    }

    public Ability getAbility() { return ability; }

    public IndividualValues getIVs() {
        return ivs;
    }

    public EffortValues getEVs() {
        return evs;
    }

    public Moveset getMoveset() {
        return moveset;
    }

    public int getCriticalStage() {
        return criticalStage;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Generates a new (empty) {@linkplain Status} object.
     *
     * @return a new (empty) {@code Status} object
     */
    public Status initStatus() {
        return new Status();
    }

    public StatMap<Integer> getStatStages() {
        return statStages;
    }

    public boolean isFainted() {
        return fainted;
    }

    /**
     * Initializes {@code currentStats} by looping through all stats and calculating their values individually.
     */
    public void initCurrentStats() {
        for (Stat stat : Statistics.STATS) {
            if (stat == Stat.HIT_POINTS) {
                try {
                    currentStats.setOneStat(Stat.HIT_POINTS, MathUtilities.calculateHP(this));
                } catch (NullPointerException npe) {
                    logger.warn("currentStats=" + currentStats);
                }
            } else {
                currentStats.setOneStat(stat, MathUtilities.calculateStat(this, stat));
            }
        }
    }

    @Override
    public double receiveDamage(int damage) {
        double currentHP = getCurrentStats().get(Stat.HIT_POINTS);
        double hpToSet = damage >= currentHP ? 0.0 : currentHP - damage;
        getCurrentStats().setOneStat(Stat.HIT_POINTS, hpToSet);
        return hpToSet;
    }

    @Override
    public void doFaint() {
        logger.debug("Pokemon=" + this + " has fainted.");

        fainted = true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pokemon.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("baseStats=" + baseStats)
                .add("currentStats=" + currentStats)
                .add("type=" + type)
                .add("level=" + level)
                .add("nature=" + nature)
                .add("ability=" + ability)
                .add("ivs=" + ivs)
                .add("evs=" + evs)
                .add("moveset=" + moveset)
                .add("criticalStage=" + criticalStage)
                .add("status=" + status)
                .add("statStages=" + statStages)
                .add("fainted=" + fainted)
                .toString();
    }

    public String toGsonString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        return gson.toJson(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return level == pokemon.level &&
                criticalStage == pokemon.criticalStage &&
                fainted == pokemon.fainted &&
                Objects.equals(name, pokemon.name) &&
                Objects.equals(baseStats, pokemon.baseStats) &&
                Objects.equals(currentStats, pokemon.currentStats) &&
                type == pokemon.type &&
                nature == pokemon.nature &&
                ability == pokemon.ability &&
                Objects.equals(ivs, pokemon.ivs) &&
                Objects.equals(evs, pokemon.evs) &&
                Objects.equals(moveset, pokemon.moveset) &&
                Objects.equals(status, pokemon.status) &&
                Objects.equals(statStages, pokemon.statStages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseStats, currentStats, type, level, nature, ability, ivs, evs, moveset,
                criticalStage, status, statStages, fainted);
    }
}
