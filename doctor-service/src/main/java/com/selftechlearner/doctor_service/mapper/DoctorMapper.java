package com.selftechlearner.doctor_service.mapper;

import com.selftechlearner.doctor_service.dto.DoctorRequestDto;
import com.selftechlearner.doctor_service.dto.DoctorResponseDto;
import com.selftechlearner.doctor_service.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DoctorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    Doctor toEntity(DoctorRequestDto requestDto);

    DoctorResponseDto toResponseDto(Doctor doctor);

    List<DoctorResponseDto> toResponseDto(List<Doctor> doctor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "doctorId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(DoctorRequestDto dto, @MappingTarget Doctor entity);
}
