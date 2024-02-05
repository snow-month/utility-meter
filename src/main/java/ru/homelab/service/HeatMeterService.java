package ru.homelab.service;

import ru.homelab.entity.HeatMeter;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

/**
 * Интерфейс для работы с ресурсами.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public interface HeatMeterService {
    /**
     * Метод возвращает текущее значение.
     *
     * @return the integer
     * @throws NoValueException the no value exception
     */
    Integer currentValue() throws NoValueException;

    /**
     * Метод для добавления значения.
     *
     * @param value добавляемое значение
     * @return the boolean
     * @throws ValueAlreadyExistsException the value already exists exception
     */
    boolean addValue(int value) throws ValueAlreadyExistsException;

    /**
     * Метод возвращает значение по номеру месяца.
     *
     * @param year   the year
     * @param month  номер месяца
     * @param userId the user id
     * @return the integer
     * @throws NoValueException the exception
     */
    Integer valueForMonth(int year, int month, long userId) throws NoValueException;

    /**
     * Метод возвращает все значения.
     *
     * @return the map
     */
    List<HeatMeter> allValuesUser();

    /**
     * All value list.
     *
     * @return the list
     */
    List<HeatMeter> allValue();
}
