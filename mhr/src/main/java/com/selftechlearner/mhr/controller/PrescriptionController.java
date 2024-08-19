package com.selftechlearner.mhr.controller;

import com.selftechlearner.mhr.dto.PrescriptionRequestDto;
import com.selftechlearner.mhr.mapper.PrescriptionMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.PrescriptionRequest;
import com.selftechlearner.mhr.service.PrescriptionService;
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
@RequestMapping("/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PrescriptionMapper prescriptionMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionRequestDto requestDto = prescriptionMapper.toDto(prescriptionRequest);
        ApiResponse response = prescriptionService.addPrescription(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPrescriptions() {
        ApiResponse response = prescriptionService.getPrescriptions();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<ApiResponse> getPrescription(@PathVariable String prescriptionId) {
        ApiResponse response = prescriptionService.getPrescriptionByPrescriptionId(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{prescriptionId}")
    public ResponseEntity<ApiResponse> getPrescription(@PathVariable String prescriptionId, @RequestBody PrescriptionRequest prescriptionRequest) {
        PrescriptionRequestDto requestDto = prescriptionMapper.toDto(prescriptionRequest);
        ApiResponse response = prescriptionService.updatePrescription(prescriptionId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable String prescriptionId) {
        ApiResponse response = prescriptionService.deletePrescription(prescriptionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

