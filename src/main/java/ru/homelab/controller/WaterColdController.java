package ru.homelab.controller;

import ru.homelab.entity.NameMeter;
import ru.homelab.entity.WaterCold;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.in.InputConsole;
import ru.homelab.service.impl.AuthorizationService;
import ru.homelab.service.WaterColdService;

import java.util.List;

/**
 * The type Water cold controller.
 */
public class WaterColdController {
    private final WaterColdService waterColdService;

    /**
     * Instantiates a new Water cold controller.
     *
     * @param waterColdService the water cold service
     */
    public WaterColdController(WaterColdService waterColdService) {
        this.waterColdService = waterColdService;
    }

    /**
     * Current value.
     */
    public void currentValue() {
        Integer value;
        String message;
        try {
            value = waterColdService.currentValue();
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "There is no indication";
        }
        System.out.println(NameMeter.WATER_COLD + ": " + message);
    }

    /**
     * Add value.
     */
    public void addValue() {
        InputConsole inputConsole = new InputConsole();
        Integer value = inputConsole.valueInput(NameMeter.WATER_COLD);
        try {
            waterColdService.addValue(value);
            System.out.println("Readings added");
        } catch (ValueAlreadyExistsException e) {
            System.out.println("Readings can be added once a month");
        }
    }

    /**
     * Value for month.
     *
     * @param year  the year
     * @param month the month
     */
    public void valueForMonth(int year, int month) {
        String message;
        try {
            int value = waterColdService.valueForMonth(year, month,
                    AuthorizationService.CURRENT_USER.get().getId());
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "нет значения";
        }
        System.out.println("значение за месяц: " + message);
    }

    /**
     * All values user.
     */
    public void allValuesUser() {
        List<WaterCold> waterColds = waterColdService.allValuesUser();
        printAllValues(waterColds);
    }

    /**
     * All values.
     */
    public void allValues() {
        List<WaterCold> waterColds = waterColdService.allValue();
        printAllValues(waterColds);
    }

    // todo как вывести login
    private void printAllValues(List<WaterCold> waterColds) {
        if (waterColds.isEmpty()) {
            System.out.println("Нет показаний " + NameMeter.WATER_COLD + ".");
        } else {
            System.out.println("Все показания " + NameMeter.WATER_COLD + ":");
            for (WaterCold waterCold : waterColds) {
                System.out.println("year " + waterCold.year() + ", month " + waterCold.month()
                        + ", value: " + waterCold.value());
            }
        }
    }
}