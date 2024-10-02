package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;
import com.selftechlearner.patient_service.dto.DoctorResponseDto;
import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.model.patient.PatientResponse;
import com.selftechlearner.patient_service.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {

    // Ignore ID in the mapping if it's not set in the request DTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    Patient toEntity(PatientRequestDto dto);

    PatientResponse toResponseDto(Patient entity);

    List<PatientResponse> toResponseDtoList(List<Patient> entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patientId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(PatientRequestDto dto, @MappingTarget Patient entity);

    List<DoctorDetailsResponse> toResponse(List<DoctorResponseDto> dtos);

    Map<String, List<DoctorDetailsResponse>> convert(Map<String, List<DoctorResponseDto>> map);
}
