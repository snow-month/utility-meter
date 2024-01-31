package ru.homelab.model;

import java.util.List;
import java.util.Map;

/**
 * The interface Meter.
 */
public interface Meter {
    /**
     * Current value integer.
     *
     * @return the integer
     */
    Integer currentValue();

    /**
     * Add value boolean.
     *
     * @param value the value
     * @return the boolean
     */
    boolean addValue(int value);

    /**
     * Value for month integer.
     *
     * @param month the month
     * @return the integer
     */
    Integer valueForMonth(int month) throws Exception;

    /**
     * All values map.
     *
     * @return the map
     */
    Map<Integer, List<Integer>> allValues();
}
