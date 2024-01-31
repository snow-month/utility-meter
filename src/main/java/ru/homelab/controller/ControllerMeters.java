package ru.homelab.controller;

import ru.homelab.in.InputUtility;
import ru.homelab.model.Meter;
import ru.homelab.model.NameMeter;

import java.util.List;
import java.util.Map;

public class ControllerMeters {
    private final InputUtility inputUtility;

    public ControllerMeters(InputUtility inputUtility) {
        this.inputUtility = inputUtility;
    }

    public void currentValuesMeters(String login, Map<String, Meter> map, NameMeter meter) {
        Integer value = map.get(login).currentValue();
        if (value == null) {
            System.out.println(meter.name() + ": There is no indication");
        } else {
            System.out.println(meter.name() + ": " + value);
        }
    }

    public void addValue(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("Enter the meter reading " + meter.name() + ":");
        boolean addValue = map.get(login).addValue(inputUtility.readingNumber());
        if (addValue) {
            System.out.println("Readings added");
        } else {
            System.out.println("Readings can be added once a month");
        }
    }

    public void valueForMonth(String login, int month, Map<String, Meter> map,
                              NameMeter meter) {
        try {
            int value = map.get(login).valueForMonth(month - 1);
            System.out.println("Monthly reading " + month + " " + meter.name() + ": " + value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
