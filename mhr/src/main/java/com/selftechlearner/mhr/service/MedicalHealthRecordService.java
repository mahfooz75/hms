package com.selftechlearner.mhr.service;

import com.selftechlearner.mhr.dto.MedicalRecordRequestDto;
import com.selftechlearner.mhr.model.ApiResponse;

public interface MedicalHealthRecordService {

    ApiResponse addMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto);

    ApiResponse getMedicalRecord(String recordId);

    ApiResponse getMedicalRecordByPatientId(String patientId);

    ApiResponse updateMedicalRecord(String recordId, MedicalRecordRequestDto recordRequestDto);

    ApiResponse deleteMedicalRecord(String recordId);

}
