package com.grimmslaw.pokemon.pokemon;

import java.util.Map;

import com.grimmslaw.pokemon.abilities.Ability;
import com.grimmslaw.pokemon.attributes.EffortValues;
import com.grimmslaw.pokemon.attributes.IndividualValues;
import com.grimmslaw.pokemon.attributes.StatSet;
import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.model.StatMap;
import com.grimmslaw.pokemon.moves.Moveset;
import com.grimmslaw.pokemon.natures.Nature;
import com.grimmslaw.pokemon.statuses.Status;
import com.grimmslaw.pokemon.types.Type;
import com.grimmslaw.pokemon.util.MathUtilities;

/**
 * Defines the attributes common to any pokemon and provides methods to initialize, set, and retrieve their values.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public abstract class AbstractPokemon {

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

    /**
     * Empty constructor.
     *
     * Sets attributes to empty or default values.
     */
    public AbstractPokemon() {
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
        // current stats should be manually initialized
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
    public AbstractPokemon(String name, StatSet baseStats, Type type, int level, Nature nature,
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
        this.statStages = initStatStages();

        initCurrentStats();
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

    public void setName(String name) {
        this.name = name;
    }

    public StatSet getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(StatSet baseStats) {
        this.baseStats = baseStats;
    }

    public StatSet getCurrentStats() {
        return currentStats;
    }

    public void setCurrentStats(StatSet currentStats) {
        this.currentStats = currentStats;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Ability getAbility() { return ability; }

    public void setAbility(Ability ability) { this.ability = ability; }

    public IndividualValues getIVs() {
        return ivs;
    }

    public void setIVs(IndividualValues ivs) {
        this.ivs = ivs;
    }

    public void setIVs(StatMap<Integer> ivsMap) {
        this.ivs = initIVs(ivsMap);
    }

    public EffortValues getEVs() {
        return evs;
    }

    public void setEVs(EffortValues evs) {
        this.evs = evs;
    }

    public void setEVs(StatMap<Integer> evsMap) {
        this.evs = initEVs(evsMap);
    }

    public Moveset getMoveset() {
        return moveset;
    }

    public void setMoveset(Moveset moveset) {
        this.moveset = moveset;
    }

    public int getCriticalStage() {
        return criticalStage;
    }

    public void setCriticalStage(int criticalStage) {
        this.criticalStage = criticalStage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status initStatus() {
        return new Status();
    }

    public Map<Stat, Integer> getStatStages() {
        return statStages;
    }

    public void setStatStages(StatMap<Integer> statStages) {
        this.statStages = statStages;
    }

    /**
     * Initializes {@code currentStats} by looping through all stats and calculating their values individually.
     */
    public void initCurrentStats() {
        for (Stat stat : Statistics.STATS) {
            if (stat == Stat.HIT_POINTS) {
                currentStats.setOneStat(Stat.HIT_POINTS, MathUtilities.calculateHP(this));
            } else {
                currentStats.setOneStat(stat, MathUtilities.calculateStat(this, stat));
            }
        }
    }

}
