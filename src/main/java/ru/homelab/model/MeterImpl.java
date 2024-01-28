package ru.homelab.model;

import java.util.*;

public class MeterImpl implements Meter {
    private final Map<Integer, List<Integer>> meter = new HashMap<>();
    private Integer currentValue;
    private final Calendar calendar = Calendar.getInstance();

    public MeterImpl() {
    }

    // Чтобы заполнить тестовыми данными для user
    public MeterImpl(int year, List<Integer> values) {
        meter.put(year, values);
        currentValue = values.get(values.size() - 1);
    }

    @Override
    public Integer currentValue() {
        return currentValue;
    }

    @Override
    public boolean addValue(int value) {
        if (checkingValueCurrentMonth()) {
            int year = currentYear();
            int month = currentMonth();
            if (!meter.containsKey(year)) {
                meter.put(year, initMonthYear(month));
            }
            meter.get(year).add(month, value);
            currentValue = value;
            return true;
        }
        return false;
    }

    @Override
    public Integer valueForMonth(int month) {
        int year = currentYear();
        if (meter.containsKey(year) && currentMonth() >= month) {
            return meter.get(year).get(month);
        }
        return 0;
    }

    @Override
    public Map<Integer, List<Integer>> allValues() {
        return meter;
    }

    private int currentYear() {
        return calendar.get(Calendar.YEAR);
    }

    private int currentMonth() {
        return calendar.get(Calendar.MONTH);
    }

    private List<Integer> initMonthYear(int month) {
        return new ArrayList<>(Collections.nCopies(month, 0));
    }

    private boolean checkingValueCurrentMonth() {
        int year = currentYear();
        if (!meter.containsKey(year)) {
            return true;
        }
        int month = currentMonth();
        int countValuesOfYear = meter.get(year).size();

        return countValuesOfYear == month;
    }
}
