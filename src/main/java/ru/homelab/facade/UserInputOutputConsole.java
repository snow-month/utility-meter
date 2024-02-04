package ru.homelab.facade;

import ru.homelab.controller.HeatMeterController;
import ru.homelab.controller.UserController;
import ru.homelab.controller.WaterColdController;
import ru.homelab.controller.WaterHotController;
import ru.homelab.entity.Role;
import ru.homelab.in.ExitMenu;
import ru.homelab.in.InputConsole;
import ru.homelab.out.Menu;
import ru.homelab.security.Authorization;

/**
 * Основной класс программы, где происходит считывание и отображение данных.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class UserInputOutputConsole {
    private final Menu menu;
    private final InputConsole inputConsole;
    private final ExitMenu exitMenu;
    private final HeatMeterController heatMeterController;
    private final WaterColdController waterColdController;
    private final WaterHotController waterHotController;
    private final UserController userController;
    private final Authorization authorization;

    /**
     * Instantiates a new Core.
     *
     * @param menu         меню для выбора пункта
     * @param inputConsole чтение данных из консоли
     * @param exitMenu     меню выхода в верхнее меню
     */
    public UserInputOutputConsole(Menu menu, InputConsole inputConsole, ExitMenu exitMenu,
                                  HeatMeterController heatMeterController, WaterColdController waterColdController, WaterHotController waterHotController,
                                  UserController userController, Authorization authorization) {
        this.menu = menu;
        this.inputConsole = inputConsole;
        this.exitMenu = exitMenu;
        this.heatMeterController = heatMeterController;
        this.waterColdController = waterColdController;
        this.waterHotController = waterHotController;
        this.userController = userController;
        this.authorization = authorization;
    }

    /**
     * Метод запускает стартовое меню.
     */
    public void start() {
        int point;
        do {
            menu.greetingMenu();
            point = inputConsole.inputNumberNotString();
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
        String login = inputConsole.stringEntry("login:");
        String password = inputConsole.stringEntry("password:");
        boolean auth = authorization.authorization(login, password);
        if (auth) {
            userMenu();
        } else {
            exitMenu.incorrectUsernameOrPassword();
        }
    }

    private void userMenu() {
        int point;
        do {
            menu.mainMenuUser(Authorization.CURRENT_USER.get());
            point = inputConsole.inputNumberNotString();
            String login = Authorization.CURRENT_USER.get().getLogin();
            switch (point) {
                case 0:
                    authorization.logout();
                    break;
                case 1:
                    System.out.println("Current meter readings:");
                    heatMeterController.currentValue();
                    waterColdController.currentValue();
                    waterHotController.currentValue();
                    exitMenu.exitMenu();
                    break;
                case 2:
                    addValueMenu(login);
                    break;
                case 3:
                    int year = inputConsole.numberEntry("Enter the year:");
                    int month = inputConsole.numberEntry("Enter the month (1-12):");
                    heatMeterController.valueForMonth(year, month);
                    waterColdController.valueForMonth(year, month);
                    waterHotController.valueForMonth(year, month);
                    exitMenu.exitMenu();
                    break;
                case 4:
                    heatMeterController.allValuesUser();
                    waterColdController.allValuesUser();
                    waterHotController.allValuesUser();
                    exitMenu.exitMenu();
                    break;
                case 5:
                    String role = Authorization.CURRENT_USER.get().getRole();
                    if (role.equals(Role.ADMIN.name())) {
                        adminMenu();
                    } else {
                        System.out.println("ONLY ADMIN");
                        exitMenu.exitMenu();
                    }
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }

    private void adminMenu() {
        int point;
        do {
            menu.mainMenuAdmin(Authorization.CURRENT_USER.get());
            point = inputConsole.inputNumberNotString();
            switch (point) {
                case 0:
                    break;
                case 1:
                    heatMeterController.allValues();
                    waterColdController.allValues();
                    waterHotController.allValues();
                    exitMenu.exitMenu();
                    break;
                default:
            }
        } while (point != 0);
    }

    private void createNewUser() {
        String login = inputConsole.stringEntry("Creating a new user. Enter login:");
        String password = inputConsole.newPasswordEntry();
        userController.createNewUser(login, password);
        System.out.println("success");
        exitMenu.exitMenu();
    }

    private void addValueMenu(String login) {
        int point;
        do {
            menu.addValueMenu(login);
            point = inputConsole.inputNumberNotString();
            switch (point) {
                case 0:
                    break;
                case 1:
                    heatMeterController.addValue();
                    exitMenu.exitMenu();
                    break;
                case 2:
                    waterColdController.addValue();
                    exitMenu.exitMenu();
                    break;
                case 3:
                    waterHotController.addValue();
                    exitMenu.exitMenu();
                    break;
                default:
                    exitMenu.noSuchPoint();
            }
        } while (point != 0);
    }
}
