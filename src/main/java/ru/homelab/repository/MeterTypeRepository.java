package ru.homelab.repository;

import ru.homelab.entity.MeterType;
import ru.homelab.entity.MeterTypeName;

import java.util.List;

public interface MeterTypeRepository {
    void save(MeterTypeName meterTypeName);

    List<MeterType> all();
}
