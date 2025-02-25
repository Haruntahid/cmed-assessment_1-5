package com.health.cmed_task_01.entity.mapper;

import com.health.cmed_task_01.entity.dtos.PrescriptionDto;
import com.health.cmed_task_01.entity.model.Prescription;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    Prescription toEntity(PrescriptionDto prescriptionDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePrescriptionFromDto(PrescriptionDto prescriptionDto, @MappingTarget Prescription prescription);

}
