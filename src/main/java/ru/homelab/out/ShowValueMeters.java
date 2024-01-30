package ru.homelab.out;

import ru.homelab.in.InputConsole;
import ru.homelab.model.Meter;
import ru.homelab.model.NameMeter;

import java.util.List;
import java.util.Map;

/**
 * The type Show value meters.
 */
public class ShowValueMeters {
    private final InputConsole inputConsole;

    /**
     * Instantiates a new Show value meters.
     *
     * @param inputConsole the input console
     */
    public ShowValueMeters(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    /**
     * Current values meters.
     *
     * @param login the login
     * @param map   the map
     * @param meter the meter
     */
    public void currentValuesMeters(String login, Map<String, Meter> map, NameMeter meter) {
        Integer value = map.get(login).currentValue();
        if (value == null) {
            System.out.println(meter.name() + ": There is no indication");
        } else {
            System.out.println(meter.name() + ": " + value);
        }
    }

    /**
     * Add value.
     *
     * @param login the login
     * @param map   the map
     * @param meter the meter
     */
    public void addValue(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("Enter the meter reading " + meter.name() + ":");
        boolean addValue = map.get(login).addValue(inputConsole.readingNumber());
        if (addValue) {
            System.out.println("Readings added");
        } else {
            System.out.println("Readings can be added once a month");
        }
    }

    /**
     * Value for month.
     *
     * @param login the login
     * @param month the month
     * @param map   the map
     * @param meter the meter
     */
    public void valueForMonth(String login, int month, Map<String, Meter> map,
                              NameMeter meter) {
        int value = map.get(login).valueForMonth(month - 1);
        System.out.println("Monthly reading " + (month + 1) + " " + meter.name() + ": "
                + value);
    }

    /**
     * All values.
     *
     * @param login the login
     * @param map   the map
     * @param meter the meter
     */
    public void allValues(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("The history of giving evidence " + meter.name() + ":");
        Map<Integer, List<Integer>> allValuesHeating = map.get(login).allValues();
        for (Map.Entry<Integer, List<Integer>> entry : allValuesHeating.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.print(value + " "));
            System.out.println();
        }
    }
}
