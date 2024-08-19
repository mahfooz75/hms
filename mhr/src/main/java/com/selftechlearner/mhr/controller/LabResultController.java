package com.selftechlearner.mhr.controller;

import com.selftechlearner.mhr.dto.LabResultRequestDto;
import com.selftechlearner.mhr.mapper.LabResultMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.LabResultRequest;
import com.selftechlearner.mhr.service.LabResultService;
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
@RequestMapping("/lab")
@RequiredArgsConstructor
public class LabResultController {
    private final LabResultService labResultService;
    private final LabResultMapper labResultMapper;

    @PostMapping
    public ResponseEntity<ApiResponse> createLabResult(@RequestBody LabResultRequest labResultRequest) {
        LabResultRequestDto requestDto = labResultMapper.toDto(labResultRequest);
        ApiResponse response = labResultService.addLabResult(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getLabResults() {
        ApiResponse response = labResultService.getLabResults();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{labResultId}")
    public ResponseEntity<ApiResponse> getLabResult(@PathVariable String labResultId) {
        ApiResponse response = labResultService.getLabResultByLabId(labResultId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{labResultId}")
    public ResponseEntity<ApiResponse> getPrescription(@PathVariable String labResultId, @RequestBody LabResultRequest labResultRequest) {
        LabResultRequestDto requestDto = labResultMapper.toDto(labResultRequest);
        ApiResponse response = labResultService.updateLabResult(labResultId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{labResultId}")
    public ResponseEntity<ApiResponse> deletePrescription(@PathVariable String labResultId) {
        ApiResponse response = labResultService.deleteLabResult(labResultId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
