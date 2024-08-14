package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.dto.ContactInformationDto;
import com.selftechlearner.patient_service.entity.ContactInformation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactInformationMapper {
    ContactInformation toEntity(ContactInformationDto dto);
    ContactInformationDto toDto(ContactInformation entity);
}
