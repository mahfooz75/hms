package com.selftechlearner.patient_service.mapper;

import com.selftechlearner.patient_service.dto.AddressDto;
import com.selftechlearner.patient_service.dto.EmergencyContactDto;
import com.selftechlearner.patient_service.entity.Address;
import com.selftechlearner.patient_service.entity.EmergencyContact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressDto dto);
    AddressDto toDto(Address entity);
}
