package ru.homelab.logicprogram;

import ru.homelab.controller.ControllerMeters;
import ru.homelab.in.ExitMenu;
import ru.homelab.in.InputConsole;
import ru.homelab.model.NameMeter;
import ru.homelab.model.Role;
import ru.homelab.model.Table;
import ru.homelab.model.User;
import ru.homelab.out.Menu;
import ru.homelab.security.Authorization;
import ru.homelab.security.CreateNewUser;
import ru.homelab.utils.log.Audit;
import ru.homelab.utils.log.Logger;

public class Core {
    private final Menu menu;
    private final InputConsole inputConsole;
    private final ExitMenu exitMenu;
    private final ControllerMeters showValueMeters;
    private final Authorization authorization;
    private final CreateNewUser createNewUser;

    public Core(Menu menu, InputConsole inputConsole,
                ExitMenu exitMenu, ControllerMeters showValueMeters, Authorization authorization,
                CreateNewUser createNewUser) {
        this.menu = menu;
        this.inputConsole = inputConsole;
        this.exitMenu = exitMenu;
        this.showValueMeters = showValueMeters;
        this.authorization = authorization;
        this.createNewUser = createNewUser;
    }

    public void start() {
        int point;
        do {
            menu.greetingMenu();
            point = inputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    authorization();
                    break;
                case 2:
                    createNewUser();
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }

    private void authorization() {
        String login = inputConsole.readingStr("login:");
        String password = inputConsole.readingStr("password:");
        boolean auth = authorization.authorization(login, password);
        if (auth) {
            if (authorization.currentUser().role().equals(Role.USER)) {
                userMenu();
            } else {
                adminMenu();
            }
        } else {
            exitMenu.incorrectUsernameOrPassword();
        }
    }

    private void userMenu() {
        int point;
        do {
            menu.mainMenuUser(authorization.currentUser());
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
                    addValueMenu(login);
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

    private void adminMenu() {
        int point;
        do {
            menu.mainMenuAdmin(authorization.currentUser());
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

    private void createNewUser() {
        String login = inputConsole.readingStr("Creating a new user. Enter login:");
        String password = inputConsole.readingNewPassword();
        User user = createNewUser.createNewUser(login, password);
        Logger.log(user.login(), Audit.CREATE_NEW_USER);
        System.out.println("success");
        exitMenu.exitMenu();
    }

    private void addValueMenu(String login) {
        int point;
        do {
            menu.addValueMenu(login);
            point = inputConsole.readingNumber();
            switch (point) {
                case 0:
                    break;
                case 1:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    showValueMeters.addValue(login, Table.HEATING, NameMeter.HEATING);
                    exitMenu.exitAddValueMenu();
                    break;
                case 2:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    showValueMeters.addValue(login, Table.WATER_COLD, NameMeter.WATER_COLD);
                    exitMenu.exitAddValueMenu();
                    break;
                case 3:
                    Logger.log(login, Audit.GIVING_EVIDENCE);
                    showValueMeters.addValue(login, Table.WATER_HOT, NameMeter.WATER_HOT);
                    exitMenu.exitAddValueMenu();
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }
}
