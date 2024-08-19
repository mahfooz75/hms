package com.selftechlearner.mhr.mapper;

import com.selftechlearner.mhr.dto.PrescriptionRequestDto;
import com.selftechlearner.mhr.entity.Prescription;
import com.selftechlearner.mhr.model.PrescriptionRequest;
import com.selftechlearner.mhr.model.PrescriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrescriptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescriptionId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    Prescription toEntity(PrescriptionRequest prescriptionRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescriptionId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    Prescription toEntity(PrescriptionRequestDto prescriptionRequestDto);

    PrescriptionRequestDto toDto(PrescriptionRequest prescriptionRequest);

    PrescriptionResponse toResponse(Prescription prescription);
    List<PrescriptionResponse> toResponse(List<Prescription> prescriptions);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prescriptionId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(PrescriptionRequestDto dto, @MappingTarget Prescription prescription);
}
