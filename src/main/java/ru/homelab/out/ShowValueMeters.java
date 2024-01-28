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
    private final Menu menu;

    /**
     * Instantiates a new Show value meters.
     *
     * @param inputConsole the input console
     * @param menu         the menu
     */
    public ShowValueMeters(InputConsole inputConsole, Menu menu) {
        this.inputConsole = inputConsole;
        this.menu = menu;
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
            System.out.println(meter.name() + ": нет показаний");
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
        System.out.println("Введите показание счётчика " + meter.name() + ":");
        boolean addValue = map.get(login).addValue(inputConsole.readingNumber());
        if (addValue) {
            System.out.println("Показания добавлены.");
        } else {
            System.out.println("Показания можно добавлять раз в месяц");
        }
        menu.exitAddValueMenu();
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
        System.out.println("Показание за месяц " + (month + 1) + " " + meter.name() + ": "
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
        System.out.println("История подачи показаний " + meter.name() + ":");
        Map<Integer, List<Integer>> allValuesHeating = map.get(login).allValues();
        for (Map.Entry<Integer, List<Integer>> entry : allValuesHeating.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(value -> System.out.print(value + " "));
            System.out.println();
        }
    }
}
