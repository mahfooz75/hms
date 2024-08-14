package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.dto.MedicalHistoryDto;
import com.selftechlearner.patient_service.entity.MedicalHistory;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface MedicalHistoryMapper {
    MedicalHistory toEntity(MedicalHistoryDto medicalHistoryDto);

    MedicalHistoryDto toDto(MedicalHistory medicalHistory);

    Set<MedicalHistory> toEntity(Set<MedicalHistoryDto> medicalHistoryDtoLst);

    Set<MedicalHistoryDto> toDto(Set<MedicalHistory> medicalHistoryLst);
}
