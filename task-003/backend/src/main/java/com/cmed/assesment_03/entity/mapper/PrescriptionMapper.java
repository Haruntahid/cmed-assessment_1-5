package com.cmed.assesment_03.entity.mapper;

import com.cmed.assesment_03.entity.dtos.PrescriptionDto;
import com.cmed.assesment_03.entity.model.Prescription;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {
    PrescriptionMapper INSTANCE = Mappers.getMapper(PrescriptionMapper.class);

    Prescription dtoToPrescription(PrescriptionDto prescriptionDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePrescriptionFromDto(PrescriptionDto prescriptionDto, @MappingTarget Prescription prescription);
}
