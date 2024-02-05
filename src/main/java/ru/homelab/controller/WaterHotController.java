package ru.homelab.controller;

import ru.homelab.entity.NameMeter;
import ru.homelab.entity.WaterHot;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.in.InputConsole;
import ru.homelab.service.impl.AuthorizationService;
import ru.homelab.service.WaterHotService;

import java.util.List;

/**
 * The type Water hot controller.
 */
public class WaterHotController {
    private final WaterHotService waterHotService;

    /**
     * Instantiates a new Water hot controller.
     *
     * @param waterHotService the water hot service
     */
    public WaterHotController(WaterHotService waterHotService) {
        this.waterHotService = waterHotService;
    }

    /**
     * Current value.
     */
    public void currentValue() {
        Integer value;
        String message;
        try {
            value = waterHotService.currentValue();
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "There is no indication";
        }
        System.out.println(NameMeter.WATER_HOT + ": " + message);
    }

    /**
     * Add value.
     */
    public void addValue() {
        InputConsole inputConsole = new InputConsole();
        Integer value = inputConsole.valueInput(NameMeter.WATER_HOT);
        try {
            waterHotService.addValue(value);
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
    public void valueForMonth(int year,int month) {
        String message;
        try {
            int value = waterHotService.valueForMonth(year, month,
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
        List<WaterHot> waterHots = waterHotService.allValuesUser();
        printAllValues(waterHots);
    }

    /**
     * All values.
     */
    public void allValues() {
        List<WaterHot> waterHots = waterHotService.allValue();
        printAllValues(waterHots);
    }

    // todo как вывести login
    private void printAllValues(List<WaterHot> waterHots) {
        if (waterHots.isEmpty()) {
            System.out.println("Нет показаний " + NameMeter.WATER_HOT + ".");
        } else {
            System.out.println("Все показания " + NameMeter.WATER_HOT + ":");
            for (WaterHot waterHot : waterHots) {
                System.out.println("year " + waterHot.year() + ", month " + waterHot.month()
                        + ", value: " + waterHot.value());
            }
        }
    }
}