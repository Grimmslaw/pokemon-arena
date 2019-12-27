package com.grimmslaw.pokemon.attributes;

import java.util.HashMap;
import java.util.Map;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.exceptions.EVOneSlotFullException;
import com.grimmslaw.pokemon.exceptions.EVSlotsFullException;

/**
 * Defines an EffortValues object, with EVs corresponding to each stat and causing each stat to grow at a speed
 * relative to its value.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class EffortValues {

    private Map<Stat, Integer> valuesMap;
    private int totalEVs = 0;

    /**
     * Empty constructor initializing a map containing an EV for each stat, setting each value to 0.
     */
    public EffortValues() {
        valuesMap = new HashMap<>();
        for (Stat stat : Statistics.STATS) {
            valuesMap.put(stat, 0);
        }
    }

    /**
     * Constructor initializing a map containing an EV for each stat, copying each value from the given map.
     *
     * @param initEVs	the map to copy the EVs' values from
     */
    public EffortValues(Map<Stat, Integer> initEVs) {
        valuesMap = initEVs;
        for (Stat stat : initEVs.keySet()) {
            totalEVs += initEVs.get(stat);
        }
    }

    public Map<Stat, Integer> getEVs() {
        return valuesMap;
    }

    public int getTotalEVs() {
        return totalEVs;
    }

    public void setTotalEVs(int value) {
        totalEVs = value;
    }

    /**
     * Retrieves one EV corresponding to the given stat name.
     *
     * @param stat	the name of the stat whose EV should be retrieved
     * @return the corresponding EV
     */
    public int getOneEV(Stat stat) {
        return valuesMap.get(stat);
    }

    /**
     * Updates one EV corresponding to the given stat name to the given value.
     *
     * This method assumes that the EV *can* be set to the given value (i.e. setting it to the given value will not
     * cause the individual values to exceed 255 or the total values to exceed 511).
     *
     * @param stat	the name of the stat whose EV should be updated
     * @param value	the value to set the EV to
     */
    private void updateOneEV(Stat stat, int value) {
        // assumes totals checks have been made
        getEVs().put(stat, value);
        setTotalEVs(getTotalEVs() + value);
    }

    /**
     * Sets the EV corresponding to the given stat to the given value, performing necessary checks to ensure each
     * value does not exceed 255 and the total values do not exceed 511.
     *
     * @param stat	the stat whose value should be set
     * @param value	the value to set the stat to
     * @throws EVOneSlotFullException	if the given value exceeds 255
     * @throws EVSlotsFullException		if adding the given value causes the total values to exceed 511
     */
    public void setOneEV(Stat stat, int value) throws EVOneSlotFullException, EVSlotsFullException {
        if (value < 256) {
            if (getTotalEVs() + value < 511) {
                updateOneEV(stat, value);
            } else {
                throw new EVOneSlotFullException(getClass(), "setOneEV", stat, value);
            }
        } else {
            throw new EVSlotsFullException(getClass(), "setOneEV", stat, value, getTotalEVs());
        }
    }

}
