package ru.homelab.repository;

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
public interface HeatMeterRepository {
    /**
     * Метод возвращает текущее значение.
     *
     * @param userId the user id
     * @return the integer
     * @throws NoValueException the no value exception
     */
    Integer currentValue(long userId) throws NoValueException;

    /**
     * Метод для добавления значения.
     *
     * @param value   добавляемое значение
     * @param year    the year
     * @param month   the month
     * @param user_id the user id
     * @return the boolean
     * @throws ValueAlreadyExistsException the value already exists exception
     */
    boolean addValue(int value, int year, int month, long user_id) throws ValueAlreadyExistsException;

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
     * @param userId the user id
     * @return the map
     */
    List<HeatMeter> allValuesUser(Long userId);

    /**
     * All value list.
     *
     * @return the list
     */
// todo только admin, проверяем роль в контроллере?
    List<HeatMeter> allValue();
}
