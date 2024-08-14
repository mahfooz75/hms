package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.dto.PatientResponseDto;
import com.selftechlearner.patient_service.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AddressMapper.class, EmergencyContactMapper.class, ContactInformationMapper.class})
public interface PatientMapper {

    // Ignore ID in the mapping if it's not set in the request DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    Patient toEntity(PatientRequestDto dto);

    PatientResponseDto toResponseDto(Patient entity);

    List<PatientResponseDto> toResponseDtoList(List<Patient> entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(PatientRequestDto dto, @MappingTarget Patient entity);
}
