package ru.homelab.out;

import ru.homelab.in.InputConsole;
import ru.homelab.model.Meter;
import ru.homelab.model.NameMeter;

import java.util.List;
import java.util.Map;

public class ShowValueMeters {

    public static void currentValuesMeters(String login, Map<String, Meter> map, NameMeter meter) {
        Integer value = map.get(login).currentValue();
        if (value == null) {
            System.out.println(meter.name() + ": нет показаний");
        } else {
            System.out.println(meter.name() + ": " + value);
        }
    }

    public static void addValue(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("Введите показание счётчика " + meter.name() + ":");
        boolean addValue = map.get(login).addValue(InputConsole.readingNumber());
        if (addValue) {
            System.out.println("Показания добавлены.");
        } else {
            System.out.println("Показания можно добавлять раз в месяц");
        }
        Menu.exitAddValueMenu();
    }

    public static void valueForMonth(String login, int month, Map<String, Meter> map,
                                     NameMeter meter) {
        int value = map.get(login).valueForMonth(month - 1);
        System.out.println("Показание за месяц " + (month + 1) + " " + meter.name() + ": "
                + value);
    }

    public static void allValues(String login, Map<String, Meter> map, NameMeter meter) {
        System.out.println("История подачи показаний " + meter.name() + ":");
        Map<Integer, List<Integer>> allValuesHeating = map.get(login).allValues();
        for (Map.Entry<Integer, List<Integer>> entry : allValuesHeating.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.print(value + " "));
            System.out.println();
        }
    }
}
