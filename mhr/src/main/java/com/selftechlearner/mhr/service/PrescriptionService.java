package com.selftechlearner.mhr.service;

import com.selftechlearner.mhr.dto.PrescriptionRequestDto;
import com.selftechlearner.mhr.model.ApiResponse;

public interface PrescriptionService {
    ApiResponse addPrescription(PrescriptionRequestDto requestDto);

    ApiResponse getPrescriptions();

    ApiResponse getPrescriptionByPrescriptionId(String prescriptionId);

    ApiResponse updatePrescription(String prescriptionId, PrescriptionRequestDto requestDto);

    ApiResponse deletePrescription(String prescriptionId);
}
