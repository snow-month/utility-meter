package ru.homelab.utils.log;

/**
 * Enum действия пользователя.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public enum Audit {
    /**
     * Authorization audit.
     */
    AUTHORIZATION,
    /**
     * Completion of work audit.
     */
    COMPLETION_OF_WORK,
    /**
     * Giving evidence audit.
     */
    GIVING_EVIDENCE,
    /**
     * Getting history of giving testimony audit.
     */
    GETTING_HISTORY_OF_GIVING_TESTIMONY,
    /**
     * View current readings audit.
     */
    VIEW_CURRENT_READINGS,
    /**
     * Viewing readings for the month audit.
     */
    VIEWING_READINGS_FOR_THE_MONTH,
    /**
     * Create new user audit.
     */
    CREATE_NEW_USER
}
