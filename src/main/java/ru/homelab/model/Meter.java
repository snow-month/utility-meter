package ru.homelab.model;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для работы с ресурсами.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public interface Meter {
    /**
     * Метод возвращает текущее значение.
     *
     * @return the integer
     */
    Integer currentValue();

    /**
     * Метод для добавления значения.
     *
     * @param value добавляемое значение
     * @return the boolean
     */
    boolean addValue(int value);

    /**
     * Метод возвращает значение по номеру месяца.
     *
     * @param month номер месяца
     * @return the integer
     * @throws Exception the exception
     */
    Integer valueForMonth(int month) throws Exception;

    /**
     * Метод возвращает все значения.
     *
     * @return the map
     */
    Map<Integer, List<Integer>> allValues();
}
