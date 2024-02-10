package ru.homelab.controller;

import ru.homelab.entity.MeterTypeName;
import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.service.MeterValueService;
import ru.homelab.service.impl.AuthorizationService;

import java.util.List;

public class MeterValueController {
    private final MeterValueService meterValueService;

    public MeterValueController(MeterValueService meterValueService) {
        this.meterValueService = meterValueService;
    }

    public void currentValue() {
        Integer value;
        String message;
        try {
            value = meterValueService.currentValue();
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "There is no indication";
        }
        System.out.println(MeterTypeName.HEATING + ": " + message);
    }

    public void addValue() {
//        try {
//            meterValueService.addValue(value);
//            System.out.println("Readings added");
//        } catch (ValueAlreadyExistsException e) {
//            System.out.println("Readings can be added once a month");
//        }
    }

    public void valueForMonth(int year, int month) {
        String message;
        try {
            int value = meterValueService.valueForMonth(year, month,
                    AuthorizationService.CURRENT_USER.get().getId());
            message = String.valueOf(value);
        } catch (NoValueException e) {
            message = "нет значения";
        }
        System.out.println("значение за месяц: " + message);
    }

    public void allValuesUser() {
        List<MeterValue> meterValues = meterValueService.allValuesUser();
        printAllValues(meterValues);
    }

    public void allValues() {
        List<MeterValue> meterValues = meterValueService.allValue();
        printAllValues(meterValues);
    }

    // todo как вывести login id
    private void printAllValues(List<MeterValue> meterValues) {
        if (meterValues.isEmpty()) {
            System.out.println("Нет показаний " + MeterTypeName.HEATING + ".");
        } else {
            System.out.println("Все показания " + MeterTypeName.HEATING + ":");
            for (MeterValue meterValue : meterValues) {
//                System.out.println("year " + meterValue.year() + ", month " + meterValue.month()
//                        + ", value: " + meterValue.value());
            }
        }
    }
}