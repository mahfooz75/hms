package com.selftechlearner.mhr.controller;

import com.selftechlearner.mhr.dto.MedicalRecordRequestDto;
import com.selftechlearner.mhr.mapper.MedicalRecordMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.MedicalRecordRequest;
import com.selftechlearner.mhr.service.MedicalHealthRecordService;
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

@RestController
@RequestMapping("/mhr")
@RequiredArgsConstructor
public class MedicalHealthRecordController {
    private final MedicalHealthRecordService medicalHealthRecordService;
    private final MedicalRecordMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponse> createMedicalRecord(@RequestBody MedicalRecordRequest medicalRecordRequest) {
        MedicalRecordRequestDto recordRequestDto = mapper.toDto(medicalRecordRequest);
        ApiResponse apiResponse = medicalHealthRecordService.addMedicalRecord(recordRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<ApiResponse> getMedicalRecord(@PathVariable String recordId) {
        ApiResponse apiResponse = medicalHealthRecordService.getMedicalRecord(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<ApiResponse> getMedicalRecordByPatientId(@PathVariable String patientId) {
        ApiResponse apiResponse = medicalHealthRecordService.getMedicalRecordByPatientId(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<ApiResponse> updateMedicalRecord(@PathVariable String recordId, @RequestBody MedicalRecordRequest medicalRecordRequest) {
        MedicalRecordRequestDto recordRequestDto = mapper.toDto(medicalRecordRequest);
        ApiResponse apiResponse = medicalHealthRecordService.updateMedicalRecord(recordId, recordRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<ApiResponse> deleteMedicalRecord(@PathVariable String recordId) {
        ApiResponse apiResponse = medicalHealthRecordService.deleteMedicalRecord(recordId);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
