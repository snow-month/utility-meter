package ru.homelab.model;

import ru.homelab.utils.log.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Таблицы для хранения значений счётчиков.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class Table {
    /**
     * Таблица для данных отопления.
     */
    public static final Map<String, Meter> HEATING = new HashMap<>();
    /**
     * Таблица для данных горячей воды.
     */
    public static final Map<String, Meter> WATER_HOT = new HashMap<>();
    /**
     * Таблица для данных холодной воды.
     */
    public static final Map<String, Meter> WATER_COLD = new HashMap<>();
    /**
     * Таблица для данных логирования.
     */
    public static final Map<String, List<Message>> LOGGER = new HashMap<>();
    /**
     * Таблица для данных о пользователях.
     */
    public static final Map<String, User> USERS = new HashMap<>();
}
