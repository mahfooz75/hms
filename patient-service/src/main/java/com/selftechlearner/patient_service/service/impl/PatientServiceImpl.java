package com.selftechlearner.patient_service.service.impl;

import com.selftechlearner.patient_service.adapter.DoctorAdapter;
import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;
import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.model.patient.PatientResponse;
import com.selftechlearner.patient_service.entity.Patient;
import com.selftechlearner.patient_service.exception.ExceptionMessage;
import com.selftechlearner.patient_service.exception.PatientNotFoundException;
import com.selftechlearner.patient_service.mapper.PatientMapper;
import com.selftechlearner.patient_service.repository.PatientRepository;
import com.selftechlearner.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final DoctorAdapter doctorAdapter;

    @Override
    public PatientResponse createPatient(PatientRequestDto patientRequestDto) {
        Patient patient = patientMapper.toEntity(patientRequestDto);
        patient.setPatientId(UUID.randomUUID().toString());
        return patientMapper.toResponseDto(patientRepository.save(patient));
    }

    @Override
    public List<PatientResponse> createPatient(List<PatientRequestDto> patientRequestDtos) {
        List<Patient> patients = patientRequestDtos.stream().map(x -> {
            Patient patient = patientMapper.toEntity(x);
            patient.setPatientId(UUID.randomUUID().toString());
            return patient;
        }).toList();
        return patientMapper.toResponseDtoList(patientRepository.saveAll(patients));
    }

    @Override
    @Cacheable(value = "patients")
    public List<PatientResponse> getAllPatients() {
        return patientMapper.toResponseDtoList(patientRepository.findBySoftDeletedFalseOrderByCreatedAtDesc());
    }

    @Override
    public PatientResponse getPatientById(String patientId) {
        Patient patient = patientRepository.findByPatientIdAndSoftDeletedFalse(patientId).orElseThrow(() -> new PatientNotFoundException(ExceptionMessage.PATIENT_NOT_FOUND));
        return patientMapper.toResponseDto(patient);
    }

    @Override
    @CacheEvict(value = "patients", allEntries = true)
    public PatientResponse updatePatient(String patientId, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findByPatientIdAndSoftDeletedFalse(patientId).orElseThrow(() -> new PatientNotFoundException(ExceptionMessage.PATIENT_NOT_FOUND));
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
    @Cacheable(value = "doctorsInfo")
    public Map<String, List<DoctorDetailsResponse>> getAppointment(String patientId) {
        return doctorAdapter.getDoctorInfo();
    }
}
