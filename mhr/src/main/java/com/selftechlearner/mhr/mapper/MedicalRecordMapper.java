package com.selftechlearner.mhr.mapper;

import com.selftechlearner.mhr.dto.MedicalRecordRequestDto;
import com.selftechlearner.mhr.entity.MedicalRecord;
import com.selftechlearner.mhr.model.MedicalRecordRequest;
import com.selftechlearner.mhr.model.MedicalRecordResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicalRecordMapper {

    MedicalRecordRequestDto toDto(MedicalRecordRequest medicalRecordRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalRecordId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    MedicalRecord toEntity(MedicalRecordRequestDto medicalRecordRequestDto);


    MedicalRecordResponse toResponse(MedicalRecord medicalRecord);
    List<MedicalRecordResponse> toResponse(List<MedicalRecord> medicalRecord);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalRecordId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(MedicalRecordRequestDto dto, @MappingTarget MedicalRecord entity);
}
