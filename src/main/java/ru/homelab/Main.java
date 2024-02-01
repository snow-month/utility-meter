package ru.homelab;

import ru.homelab.controller.ControllerMeters;
import ru.homelab.in.ExitMenu;
import ru.homelab.in.InputConsole;
import ru.homelab.in.InputUtility;
import ru.homelab.service.userinputoutputservice.UserInputOutputService;
import ru.homelab.model.MeterImpl;
import ru.homelab.model.Role;
import ru.homelab.model.Table;
import ru.homelab.model.User;
import ru.homelab.out.Menu;
import ru.homelab.security.Authorization;
import ru.homelab.security.CreateNewUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс запуска программы.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        init();

        Menu menu = new Menu();
        InputUtility inputUtility = new InputUtility();
        InputConsole inputConsole = new InputConsole();
        ExitMenu exitMenu = new ExitMenu();
        ControllerMeters controllerMeters = new ControllerMeters(inputUtility);
        Authorization authorization = new Authorization();
        CreateNewUser createNewUser = new CreateNewUser();

        UserInputOutputService userInputOutputService = new UserInputOutputService(menu, inputConsole, exitMenu, controllerMeters, authorization,
                createNewUser);

        userInputOutputService.start();
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
