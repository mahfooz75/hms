package com.selftechlearner.mhr.service;

import com.selftechlearner.mhr.dto.LabResultRequestDto;
import com.selftechlearner.mhr.model.ApiResponse;

public interface LabResultService {
    ApiResponse addLabResult(LabResultRequestDto requestDto);

    ApiResponse getLabResults();

    ApiResponse getLabResultByLabId(String labResultId);

    ApiResponse updateLabResult(String labResultId, LabResultRequestDto requestDto);

    ApiResponse deleteLabResult(String labResultId);
}
