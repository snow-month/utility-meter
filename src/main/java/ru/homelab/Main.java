package ru.homelab;

import ru.homelab.in.InputConsole;
import ru.homelab.model.*;
import ru.homelab.out.Menu;
import ru.homelab.out.ShowValueMeters;
import ru.homelab.security.Authorization;
import ru.homelab.security.CreateNewUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Authorization authorization = new Authorization();
        CreateNewUser createNewUser = new CreateNewUser();
        init();

        int point;
        do {
            Menu.greetingMenu();
            point = InputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    authorization(authorization);
                    break;
                case 2:
                    createNewUser(createNewUser);
                    break;
                default:
                    System.out.println("Неверный пункт");
                    Menu.exitGreetingMenu();
            }
        } while (point != 0);
    }

    private static void init() {
        User user = new User("user", Role.USER, "user");
        Table.USERS.put(user.login(), user);

        List<Integer> heating = new ArrayList<>(
                Arrays.asList(12, 25, 44, 57, 74, 82, 91, 105, 120, 132, 149, 160));
        MeterImpl meterHeating = new MeterImpl(2023, heating);
        Table.HEATING.put(user.login(), meterHeating);

        List<Integer> waterCold = new ArrayList<>(
                Arrays.asList(121, 252, 414, 567, 734, 832, 931, 1052, 1202, 1322, 1491, 1603));
        MeterImpl meterWaterCold = new MeterImpl(2023, waterCold);
        Table.WATER_COLD.put(user.login(), meterWaterCold);

        List<Integer> waterHot = new ArrayList<>(
                Arrays.asList(122, 253, 441, 574, 745, 826, 911, 1055, 1208, 1322, 1491, 1601));
        MeterImpl meterWaterHot = new MeterImpl(2023, waterHot);
        Table.WATER_HOT.put(user.login(), meterWaterHot);

        User admin = new User("admin", Role.ADMIN, "admin");
        Table.USERS.put(admin.login(), admin);
    }

    private static void authorization(Authorization authorization) {
        boolean auth = authorization.authorization();
        if (auth) {
            if (authorization.currentUser().role().equals(Role.USER)) {
                userMenu(authorization);
            } else {
                adminMenu(authorization);
            }
        } else {
            System.out.println("Неправильный логин или пароль");
            Menu.exitGreetingMenu();
        }
    }

    private static void userMenu(Authorization authorization) {
        int point;
        do {
            Menu.mainMenuUser(authorization.currentUser());
            point = InputConsole.readingNumber();
            String login = authorization.currentUser().login();
            switch (point) {
                case 0:
                    break;
                case 1:
                    System.out.println("Текущие показания счётчиков:");
                    ShowValueMeters.currentValuesMeters(login, Table.HEATING, NameMeter.HEATING);
                    ShowValueMeters.currentValuesMeters(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    ShowValueMeters.currentValuesMeters(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    Menu.exitMainMenu();
                    break;
                case 2:
                    addValueMenu(login);
                    break;
                case 3:
                    System.out.println("Введите номер месяца (1-12)");
                    int month = InputConsole.readingNumber();
                    ShowValueMeters.valueForMonth(login, month, Table.HEATING, NameMeter.HEATING);
                    ShowValueMeters.valueForMonth(login, month, Table.WATER_COLD, NameMeter.WATER_COLD);
                    ShowValueMeters.valueForMonth(login, month, Table.WATER_HOT, NameMeter.WATER_HOT);
                    Menu.exitMainMenu();
                    break;
                case 4:
                    ShowValueMeters.allValues(login, Table.HEATING, NameMeter.HEATING);
                    ShowValueMeters.allValues(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    ShowValueMeters.allValues(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    Menu.exitMainMenu();
                    break;
                default:
                    System.out.println("Нет такого пункта");
                    Menu.exitMainMenu();
            }
        } while (point != 0);
    }

    private static void adminMenu(Authorization authorization) {
        int point;
        do {
            Menu.mainMenuAdmin(authorization.currentUser());
            point = InputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    String login;
                    do {
                        System.out.println("Для просмотра показаний пользователей введите имя пользоателя и нажмите энтер");
                        Table.USERS.entrySet().stream()
                                .filter(stringUserEntry -> !stringUserEntry.getKey().equals("admin"))
                                .forEach(stringUserEntry -> System.out.print(stringUserEntry.getKey() + " "));
                        System.out.println();
                        login = InputConsole.readingStr();
                        if (!Table.USERS.containsKey(login)) {
                            System.out.println("нет такого пользователя");
                        }
                    } while (!Table.USERS.containsKey(login));
                    ShowValueMeters.allValues(login, Table.HEATING, NameMeter.HEATING);
                    ShowValueMeters.allValues(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    ShowValueMeters.allValues(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    Menu.exitMainMenu();
                    break;
                default:
            }
        } while (point != 0);
    }

    private static void createNewUser(CreateNewUser createNewUser) {
        boolean newUser = createNewUser.createNewUser();
        System.out.println("успех");
        Menu.exitGreetingMenu();
    }

    private static void addValueMenu(String login) {
        int point;
        do {
            Menu.addValueMenu(login);
            point = InputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    ShowValueMeters.addValue(login, Table.HEATING, NameMeter.HEATING);
                    break;
                case 2:
                    ShowValueMeters.addValue(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    break;
                case 3:
                    ShowValueMeters.addValue(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    break;
                default:
                    System.out.println("Нет такого пункта");
                    Menu.exitMainMenu();
            }
        } while (point != 0);
    }
}
