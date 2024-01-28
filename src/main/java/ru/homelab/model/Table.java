package ru.homelab.model;

import ru.homelab.utils.log.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Table.
 */
public class Table {
    /**
     * The constant HEATING.
     */
    public static final Map<String, Meter> HEATING = new HashMap<>();
    /**
     * The constant WATER_HOT.
     */
    public static final Map<String, Meter> WATER_HOT = new HashMap<>();
    /**
     * The constant WATER_COLD.
     */
    public static final Map<String, Meter> WATER_COLD = new HashMap<>();
    /**
     * The constant LOGGER.
     */
    public static final Map<String, List<Message>> LOGGER = new HashMap<>();
    /**
     * таблица user
     */
    public static final Map<String, User> USERS = new HashMap<>();
}
