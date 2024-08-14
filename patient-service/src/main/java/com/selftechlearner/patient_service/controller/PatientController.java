package com.selftechlearner.patient_service.controller;

import com.selftechlearner.patient_service.dto.PatientRequestDto;
import com.selftechlearner.patient_service.dto.PatientResponseDto;
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

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequestDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> createMultiplePatient(@RequestBody List<PatientRequestDto> patientRequestDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createMultiplePatient(patientRequestDtos));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable("patientId") String patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientById(patientId));
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable("patientId") String patientId, @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatient(patientId, patientRequestDto));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> deletePatient(@PathVariable("patientId") String patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(PatientResponseDto.builder().build());
    }

}
