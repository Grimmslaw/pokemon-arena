package com.grimmslaw.pokemon.attributes;

import com.grimmslaw.pokemon.constants.Statistics;
import com.grimmslaw.pokemon.constants.Statistics.Stat;
import com.grimmslaw.pokemon.exceptions.attributes.EVOneSlotFullException;
import com.grimmslaw.pokemon.exceptions.attributes.EVSlotsFullException;
import com.grimmslaw.pokemon.model.StatMap;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Defines an EffortValues object, with EVs corresponding to each stat and causing each stat to grow at a speed
 * relative to its value.
 *
 * @author wesrickey
 * @since 0.0.1
 */
public class EffortValues {

    private StatMap<Integer> valueMap;
    private int totalEVs = 0;

    /**
     * Empty constructor initializing a map containing an EV for each stat, setting each value to 0.
     */
    public EffortValues() {
        valueMap = new StatMap<>();
        for (Stat stat : Statistics.STATS) {
            valueMap.put(stat, 0);
        }
    }

    /**
     * Constructor initializing a map containing an EV for each stat, copying each value from the given map.
     *
     * @param initEVs	the map to copy the EVs' values from
     */
    public EffortValues(StatMap<Integer> initEVs) {
        valueMap = initEVs;
        for (Stat stat : initEVs.keySet()) {
            totalEVs += initEVs.get(stat);
        }
    }

    public StatMap<Integer> getEVs() {
        return valueMap;
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
        return valueMap.get(stat);
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

    @Override
    public String toString() {
        return new StringJoiner(", ", EffortValues.class.getSimpleName() + "[", "]")
                .add("valueMap=" + valueMap)
                .add("totalEVs=" + totalEVs)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EffortValues that = (EffortValues) o;
        return totalEVs == that.totalEVs &&
                Objects.equals(valueMap, that.valueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueMap, totalEVs);
    }
}
