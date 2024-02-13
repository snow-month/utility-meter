package ru.homelab.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.homelab.dto.MeterValueDto;
import ru.homelab.entity.MeterValue;

@Mapper
public interface MeterValueMapper {
    MeterValueMapper INSTANCE = Mappers.getMapper(MeterValueMapper.class);

    MeterValueDto materValueToMeterValueDto(MeterValue meterValue);
}
