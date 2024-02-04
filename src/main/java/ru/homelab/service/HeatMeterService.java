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
     */
    Integer currentValue() throws NoValueException;

    /**
     * Метод для добавления значения.
     *
     * @param value добавляемое значение
     * @return the boolean
     */
    boolean addValue(int value) throws ValueAlreadyExistsException;

    /**
     * Метод возвращает значение по номеру месяца.
     *
     * @param month номер месяца
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

    List<HeatMeter> allValue();
}
