package ru.homelab.model;

import ru.homelab.utils.log.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    public static final Map<String, Meter> HEATING = new HashMap<>();
    public static final Map<String, Meter> WATER_HOT = new HashMap<>();
    public static final Map<String, Meter> WATER_COLD = new HashMap<>();
    public static final Map<String, List<Message>> LOGGER = new HashMap<>();
    /**
     * таблица user
     */
    public static final Map<String, User> USERS = new HashMap<>();
}
