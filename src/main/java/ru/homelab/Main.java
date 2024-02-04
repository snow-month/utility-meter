package ru.homelab;

import ru.homelab.controller.HeatMeterController;
import ru.homelab.controller.UserController;
import ru.homelab.controller.WaterColdController;
import ru.homelab.controller.WaterHotController;
import ru.homelab.facade.UserInputOutputConsole;
import ru.homelab.in.ExitMenu;
import ru.homelab.in.InputConsole;
import ru.homelab.out.Menu;
import ru.homelab.repository.*;
import ru.homelab.repository.impl.*;
import ru.homelab.security.Authorization;
import ru.homelab.service.*;
import ru.homelab.service.impl.*;
import ru.homelab.utils.PropertiesApp;
// todo многопоточность, чтобы много пользователей одновременно

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
        PropertiesApp propApp = new PropertiesApp();
        InitDbService initDbService = new InitDbService();
        initDbService.init(propApp.getUrl(), propApp.getUsername(), propApp.getPassword());

        // задержка для создания таблиц
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        Menu menu = new Menu();
        InputConsole inputConsole = new InputConsole();
        ExitMenu exitMenu = new ExitMenu();

        AuditRepository auditRepository = new AuditRepositoryImpl();
        AuditService auditService = new AuditServiceImpl(auditRepository);

        HeatMeterRepository heatMeterRepository = new HeatMeterRepositoryImpl(propApp);
        HeatMeterService heatMeterService = new HeatMeterServiceImpl(heatMeterRepository, auditService);
        HeatMeterController heatMeterController = new HeatMeterController(heatMeterService);

        WaterColdRepository waterColdRepository = new WaterColdRepositoryImpl(propApp);
        WaterColdService waterColdService = new WaterColdServiceImpl(waterColdRepository, auditService);
        WaterColdController waterColdController = new WaterColdController(waterColdService);

        WaterHotRepository waterHotRepository = new WaterHotRepositoryImpl(propApp);
        WaterHotService waterHotService = new WaterHotServiceImpl(waterHotRepository, auditService);
        WaterHotController waterHotController = new WaterHotController(waterHotService);

        UserRepository userRepository = new UserRepositoryImpl(propApp);
        UserService userService = new UserServiceImpl(userRepository, auditService);
        UserController userController = new UserController(userService);

        Authorization authorization = new Authorization(userController);

        UserInputOutputConsole userInputOutputConsoleService = new UserInputOutputConsole(menu, inputConsole, exitMenu,
                heatMeterController, waterColdController, waterHotController, userController, authorization);

        userInputOutputConsoleService.start();
    }
}