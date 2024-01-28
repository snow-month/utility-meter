package ru.homelab.model;

import java.util.List;
import java.util.Map;

public interface Meter {
    Integer currentValue();

    boolean addValue(int value);

    Integer valueForMonth(int month);

    Map<Integer, List<Integer>> allValues();
}
