package com.selftechlearner.patient_service.service.impl;

import com.selftechlearner.patient_service.dto.MedicalHistoryDto;
import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.dto.PatientResponseDto;
import com.selftechlearner.patient_service.entity.Patient;
import com.selftechlearner.patient_service.exception.PatientNotFoundException;
import com.selftechlearner.patient_service.mapper.MedicalHistoryMapper;
import com.selftechlearner.patient_service.mapper.PatientMapper;
import com.selftechlearner.patient_service.repository.PatientRepository;
import com.selftechlearner.patient_service.service.PatientService;
import com.selftechlearner.patient_service.exception.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final MedicalHistoryMapper medicalHistoryMapper;

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        Patient patient = patientMapper.toEntity(patientRequestDto);
        patient.setPatientId(UUID.randomUUID().toString());
        return patientMapper.toResponseDto(patientRepository.save(patient));
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        return patientMapper.toResponseDtoList(patientRepository.findBySoftDeletedFalseOrderByCreatedAtDesc());
    }

    @Override
    public PatientResponseDto getPatientById(String patientId) {
        Patient patient = patientRepository.findByPatientIdAndSoftDeletedFalse(patientId).orElseThrow(() -> new PatientNotFoundException(ExceptionMessage.PATIENT_NOT_FOUND));
        return patientMapper.toResponseDto(patient);
    }

    @Override
    public PatientResponseDto updatePatient(String patientId, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findByPatientIdAndSoftDeletedFalse(patientId).orElseThrow(() -> new PatientNotFoundException(ExceptionMessage.PATIENT_NOT_FOUND));
        Set<MedicalHistoryDto> existingMedicalHistoryDtos = medicalHistoryMapper.toDto(patient.getMedicalHistory());
        if (!existingMedicalHistoryDtos.isEmpty() && !existingMedicalHistoryDtos.containsAll(patientRequestDto.getMedicalHistory())) {
            patientRequestDto.getMedicalHistory().removeAll(existingMedicalHistoryDtos);
            patientRequestDto.getMedicalHistory().addAll(existingMedicalHistoryDtos);
        }
        patientMapper.updateEntityFromDto(patientRequestDto, patient);
        patient = patientRepository.save(patient);
        return patientMapper.toResponseDto(patient);
    }

    @Override
    public void deletePatient(String patientId) {
        Patient patient = patientRepository.findByPatientIdAndSoftDeletedFalse(patientId)
                .orElseThrow(() -> new PatientNotFoundException(ExceptionMessage.PATIENT_NOT_FOUND));
        patient.setSoftDeleted(true);
        patientRepository.save(patient);
    }

    @Override
    public List<PatientResponseDto> createMultiplePatient(List<PatientRequestDto> patientRequestDtos) {
        List<Patient> patients = patientRequestDtos.stream().map(x -> {
            Patient patient = patientMapper.toEntity(x);
            patient.setPatientId(UUID.randomUUID().toString());
            return patient;
        }).toList();
        return patientMapper.toResponseDtoList(patientRepository.saveAll(patients));
    }
}
