package com.selftechlearner.patient_service.controller;

import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.exception.PatientException;
import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;
import com.selftechlearner.patient_service.model.patient.PatientResponse;
import com.selftechlearner.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequestDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<PatientResponse>> createMultiplePatient(@RequestBody List<PatientRequestDto> patientRequestDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createMultiplePatient(patientRequestDtos));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getPatients() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
        } catch (Exception e) {
            throw new PatientException(e.getMessage());
        }
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable("patientId") String patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(patientId));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable("patientId") String patientId, @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(patientId, patientRequestDto));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PatientResponse> deletePatient(@PathVariable("patientId") String patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(PatientResponse.builder().build());
    }

    @GetMapping("/appointment/{patientId}")
    public ResponseEntity<Map<String, List<DoctorDetailsResponse>>> getAppointments(@PathVariable("patientId") String patientId) {
        Map<String, List<DoctorDetailsResponse>> responseDto = patientService.getAppointment(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
