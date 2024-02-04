package ru.homelab.controller;

import ru.homelab.entity.HeatMeter;
import ru.homelab.entity.NameMeter;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.in.InputConsole;
import ru.homelab.service.impl.AuthorizationService;
import ru.homelab.service.HeatMeterService;

import java.util.List;

public class HeatMeterController {
    private final HeatMeterService heatMeterService;

    public HeatMeterController(HeatMeterService heatMeterService) {
        this.heatMeterService = heatMeterService;
    }

    public void currentValue() {
        Integer value;
        String message;
        try {
            value = heatMeterService.currentValue();
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "There is no indication";
        }
        System.out.println(NameMeter.HEATING + ": " + message);
    }

    public void addValue() {
        InputConsole inputConsole = new InputConsole();
        Integer value = inputConsole.valueInput(NameMeter.HEATING);
        try {
            heatMeterService.addValue(value);
            System.out.println("Readings added");
        } catch (ValueAlreadyExistsException e) {
            System.out.println("Readings can be added once a month");
        }
    }

    public void valueForMonth(int year, int month) {
        String message;
        try {
            int value = heatMeterService.valueForMonth(year, month,
                    AuthorizationService.CURRENT_USER.get().getId());
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "нет значения";
        }
        System.out.println("значение за месяц: " + message);
    }

    public void allValuesUser() {
        List<HeatMeter> heatMeters = heatMeterService.allValuesUser();
        printAllValues(heatMeters);
    }

    public void allValues() {
        List<HeatMeter> heatMeters = heatMeterService.allValue();
        printAllValues(heatMeters);
    }

    // todo как вывести login id
    private void printAllValues(List<HeatMeter> heatMeters) {
        if (heatMeters.isEmpty()) {
            System.out.println("Нет показаний " + NameMeter.HEATING + ".");
        } else {
            System.out.println("Все показания " + NameMeter.HEATING + ":");
            for (HeatMeter heatMeter : heatMeters) {
                System.out.println("year " + heatMeter.year() + ", month " + heatMeter.month()
                        + ", value: " + heatMeter.value());
            }
        }
    }
}