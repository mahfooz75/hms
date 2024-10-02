package com.selftechlearner.mhr.util;

import com.selftechlearner.mhr.model.ApiResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicalHealthRecordUtil {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";
    public ApiResponse createSuccessMedicalHealthResponse(Object data, String message) {
        return ApiResponse.builder()
                .status(SUCCESS)
                .medicalHealthRecord(data)
                .message(message)
                .build();
    }

    public ApiResponse createFailureMedicalHealthResponse(Object data, String message) {
        return ApiResponse.builder()
                .status(FAIL)
                .medicalHealthRecord(data)
                .message(message)
                .build();
    }
}
