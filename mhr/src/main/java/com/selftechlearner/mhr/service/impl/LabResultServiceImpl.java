package com.selftechlearner.mhr.service.impl;

import com.selftechlearner.mhr.dto.LabResultRequestDto;
import com.selftechlearner.mhr.entity.LabResult;
import com.selftechlearner.mhr.exception.ExceptionMessage;
import com.selftechlearner.mhr.exception.RecordNotFoundException;
import com.selftechlearner.mhr.mapper.LabResultMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.LabResultResponse;
import com.selftechlearner.mhr.repository.LabResultRepository;
import com.selftechlearner.mhr.service.LabResultService;
import com.selftechlearner.mhr.util.MedicalHealthRecordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LabResultServiceImpl implements LabResultService {
    private final LabResultRepository labResultRepository;
    private final LabResultMapper labResultMapper;

    @Override
    public ApiResponse addLabResult(LabResultRequestDto requestDto) {
        LabResult labResult = labResultMapper.toEntity(requestDto);
        labResult.setLabResultId(UUID.randomUUID().toString());
        LabResultResponse response = labResultMapper.toResponse(labResultRepository.save(labResult));
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(response, MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getLabResults() {
        List<LabResult> labResults = labResultRepository.findBySoftDeletedFalseOrderByCreatedAtDesc();
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(labResultMapper.toResponse(labResults), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getLabResultByLabId(String labResultId) {
        LabResult labResult = labResultRepository.findByLabResultIdAndSoftDeletedFalse(labResultId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.LAB_RECORD_NOT_FOUND));
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(labResultMapper.toResponse(labResult), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse updateLabResult(String labResultId, LabResultRequestDto requestDto) {
        LabResult labResult = labResultRepository.findByLabResultIdAndSoftDeletedFalse(labResultId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.LAB_RECORD_NOT_FOUND));
        labResultMapper.updateEntityFromDto(requestDto, labResult);
        labResult = labResultRepository.save(labResult);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(labResultMapper.toResponse(labResult), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse deleteLabResult(String labResultId) {
        LabResult labResult = labResultRepository.findByLabResultIdAndSoftDeletedFalse(labResultId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.LAB_RECORD_NOT_FOUND));
        labResult.setSoftDeleted(true);
        labResultRepository.save(labResult);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse("Lab record deleted successfully", MedicalHealthRecordUtil.SUCCESS);
    }
}
