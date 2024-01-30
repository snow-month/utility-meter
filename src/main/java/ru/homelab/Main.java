package ru.homelab;

import ru.homelab.in.ExitMenu;
import ru.homelab.in.InputConsole;
import ru.homelab.model.*;
import ru.homelab.out.Menu;
import ru.homelab.out.ShowValueMeters;
import ru.homelab.security.Authorization;
import ru.homelab.security.CreateNewUser;
import ru.homelab.utils.log.Audit;
import ru.homelab.utils.log.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        InputConsole inputConsole = new InputConsole();
        ExitMenu exitMenu = new ExitMenu();
        ShowValueMeters showValueMeters = new ShowValueMeters();
        Authorization authorization = new Authorization();
        CreateNewUser createNewUser = new CreateNewUser();
        init();

        int point;
        do {
            Menu.greetingMenu();
            point = inputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    authorization(authorization, inputConsole, showValueMeters, exitMenu);
                    break;
                case 2:
                    createNewUser(createNewUser, inputConsole, exitMenu);
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }

    private static void authorization(Authorization authorization, InputConsole inputConsole,
                                      ShowValueMeters showValueMeters,
                                      ExitMenu exitMenu) {
        String login = inputConsole.readingStr("login:");
        String password = inputConsole.readingStr("password:");
        boolean auth = authorization.authorization(login, password);
        if (auth) {
            if (authorization.currentUser().role().equals(Role.USER)) {
                userMenu(authorization, inputConsole, showValueMeters, exitMenu);
            } else {
                adminMenu(authorization, inputConsole, showValueMeters, exitMenu);
            }
        } else {
            exitMenu.incorrectUsernameOrPassword();
        }
    }

    private static void userMenu(Authorization authorization, InputConsole inputConsole,
                                 ShowValueMeters showValueMeters, ExitMenu exitMenu) {
        int point;
        do {
            Menu.mainMenuUser(authorization.currentUser());
            point = inputConsole.readingNumber();
            String login = authorization.currentUser().login();
            switch (point) {
                case 0:
                    authorization.logout();
                    break;
                case 1:
                    Logger.log(login, Audit.VIEW_CURRENT_READINGS);
                    System.out.println("Current meter readings:");
                    showValueMeters.currentValuesMeters(login, Table.HEATING, NameMeter.HEATING);
                    showValueMeters.currentValuesMeters(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    showValueMeters.currentValuesMeters(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitMainMenu();
                    break;
                case 2:
                    addValueMenu(login, inputConsole, exitMenu);
                    break;
                case 3:
                    Logger.log(login, Audit.VIEWING_READINGS_FOR_THE_MONTH);
                    int month = inputConsole.enterTheMonthNumber();
                    showValueMeters.valueForMonth(login, month, Table.HEATING, NameMeter.HEATING);
                    showValueMeters.valueForMonth(login, month, Table.WATER_COLD, NameMeter.WATER_COLD);
                    showValueMeters.valueForMonth(login, month, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitMainMenu();
                    break;
                case 4:
                    Logger.log(login, Audit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
                    showValueMeters.allValues(login, Table.HEATING, NameMeter.HEATING);
                    showValueMeters.allValues(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    showValueMeters.allValues(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitMainMenu();
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }

    private static void adminMenu(Authorization authorization, InputConsole inputConsole,
                                  ShowValueMeters showValueMeters,
                                  ExitMenu exitMenu) {
        int point;
        do {
            Menu.mainMenuAdmin(authorization.currentUser());
            point = inputConsole.readingNumber();
            switch (point) {
                case 0:
                    authorization.logout();
                    break;
                case 1:
                    String login = inputConsole.enterUser();
                    showValueMeters.allValues(login, Table.HEATING, NameMeter.HEATING);
                    showValueMeters.allValues(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    showValueMeters.allValues(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitMainMenu();
                    break;
                default:
            }
        } while (point != 0);
    }

    private static void createNewUser(CreateNewUser createNewUser,
                                      InputConsole inputConsole, ExitMenu exitMenu) {
        String login = inputConsole.readingStr("Creating a new user. Enter login:");
        String password = inputConsole.readingNewPassword();
        User user = createNewUser.createNewUser(login, password);
        Logger.log(user.login(), Audit.CREATE_NEW_USER);
        System.out.println("success");
        exitMenu.exitMenu();
    }

    private static void addValueMenu(String login, InputConsole inputConsole,
                                     ExitMenu exitMenu) {
        int point;
        do {
            Menu.addValueMenu(login);
            point = inputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    inputConsole.addValue(login, Table.HEATING, NameMeter.HEATING);
                    exitMenu.exitAddValueMenu();
                    break;
                case 2:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    inputConsole.addValue(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    exitMenu.exitAddValueMenu();
                    break;
                case 3:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    inputConsole.addValue(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitAddValueMenu();
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }

    private static void init() {
        User user = new User("user", Role.USER, "user");
        Table.USERS.put(user.login(), user);
        Table.LOGGER.put(user.login(), new ArrayList<>());

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
        Table.LOGGER.put(admin.login(), new ArrayList<>());
    }
}
