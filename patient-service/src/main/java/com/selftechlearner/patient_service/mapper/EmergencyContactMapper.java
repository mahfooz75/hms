package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.dto.ContactInformationDto;
import com.selftechlearner.patient_service.dto.EmergencyContactDto;
import com.selftechlearner.patient_service.entity.ContactInformation;
import com.selftechlearner.patient_service.entity.EmergencyContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmergencyContactMapper {
    EmergencyContact toEntity(EmergencyContactDto dto);
    EmergencyContactDto toDto(EmergencyContact entity);
}
