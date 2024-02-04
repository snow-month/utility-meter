package ru.homelab.repository;

import ru.homelab.entity.WaterCold;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

/**
 * Интерфейс для работы с ресурсами.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public interface WaterColdRepository {
    /**
     * Метод возвращает текущее значение.
     *
     * @return the integer
     */
    Integer currentValue(long userId) throws NoValueException;

    /**
     * Метод для добавления значения.
     *
     * @param value добавляемое значение
     * @return the boolean
     */
    boolean addValue(int value, int year, int month, long user_id) throws ValueAlreadyExistsException;

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
    List<WaterCold> allValuesUser(Long userId);

    // todo только admin, проверяем роль в контроллере?
    List<WaterCold> allValue();
}
