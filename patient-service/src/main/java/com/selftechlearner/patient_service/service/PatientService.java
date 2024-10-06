package com.selftechlearner.patient_service.service;

import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;
import com.selftechlearner.patient_service.model.patient.PatientResponse;

import java.util.List;
import java.util.Map;

public interface PatientService {

    PatientResponse createPatient(PatientRequestDto patientRequestDto);

    List<PatientResponse> createPatient(List<PatientRequestDto> patientRequestDtos);

    List<PatientResponse> getAllPatients();

    PatientResponse getPatientById(String patientId);

    PatientResponse updatePatient(String patientId, PatientRequestDto patientRequestDto);

    void deletePatient(String patientId);

    Map<String, List<DoctorDetailsResponse>> getAppointment(String patientId);
}
