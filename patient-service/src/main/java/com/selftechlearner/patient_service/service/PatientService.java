package com.selftechlearner.patient_service.service;

import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);

    List<PatientResponseDto> getAllPatients();

    PatientResponseDto getPatientById(String patientId);

    PatientResponseDto updatePatient(String patientId, PatientRequestDto patientRequestDto);

    void deletePatient(String patientId);

    List<PatientResponseDto> createMultiplePatient(List<PatientRequestDto> patientRequestDtos);
}
