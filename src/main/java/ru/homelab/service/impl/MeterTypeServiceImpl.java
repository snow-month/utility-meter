package ru.homelab.service.impl;

import ru.homelab.entity.MeterType;
import ru.homelab.repository.MeterTypeRepository;
import ru.homelab.service.MeterTypeService;

public class MeterTypeServiceImpl implements MeterTypeService {
    private final MeterTypeRepository repository;

    public MeterTypeServiceImpl(MeterTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(MeterType meterType) {

    }
}
