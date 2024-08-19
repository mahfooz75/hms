package com.selftechlearner.mhr.mapper;

import com.selftechlearner.mhr.dto.LabResultRequestDto;
import com.selftechlearner.mhr.entity.LabResult;
import com.selftechlearner.mhr.model.LabResultRequest;
import com.selftechlearner.mhr.model.LabResultResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LabResultMapper {

    LabResultRequestDto toDto(LabResultRequest labResultRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "labResultId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    LabResult toEntity(LabResultRequest labResultRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "labResultId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    LabResult toEntity(LabResultRequestDto labResultRequestDto);

    LabResultResponse toResponse(LabResult labResult);
    List<LabResultResponse> toResponse(List<LabResult> labResult);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "labResultId", ignore = true)
    @Mapping(target = "softDeleted", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromDto(LabResultRequestDto labResultRequestDto, @MappingTarget LabResult labResult);
}
