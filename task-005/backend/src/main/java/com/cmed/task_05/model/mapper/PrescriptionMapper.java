package com.cmed.task_05.model.mapper;

import com.cmed.task_05.model.dtos.PrescriptionDto;
import com.cmed.task_05.model.entity.Prescription;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);
    Prescription dtoToPrescription(PrescriptionDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePrescriptionFromDto(PrescriptionDto dto, @MappingTarget Prescription prescription);

}
