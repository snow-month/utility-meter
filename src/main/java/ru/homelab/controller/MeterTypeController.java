package ru.homelab.controller;

import ru.homelab.entity.MeterType;
import ru.homelab.service.MeterTypeService;

public class MeterTypeController {
    private final MeterTypeService service;

    public MeterTypeController(MeterTypeService service) {
        this.service = service;
    }

    public void save(MeterType meterType) {
        service.save(meterType);
    }
}
