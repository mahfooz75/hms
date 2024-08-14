package com.selftechlearner.doctor_service.controller;

import com.selftechlearner.doctor_service.dto.DoctorRequestDto;
import com.selftechlearner.doctor_service.dto.DoctorResponseDto;
import com.selftechlearner.doctor_service.service.DoctorService;
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
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctorRequestDto));
    }

    @PostMapping("/all")
    public ResponseEntity<List<DoctorResponseDto>> createMultiPleDoctor(@RequestBody List<DoctorRequestDto> doctorRequestDtos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createMultipleDoctors(doctorRequestDtos));
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDto> getDoctorByDoctorId(@PathVariable("doctorId") String doctorId) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findDoctorByDoctorId(doctorId));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllDoctors());
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable("doctorId") String doctorId, @RequestBody DoctorRequestDto doctorRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctor(doctorId, doctorRequestDto));
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<DoctorResponseDto> getAllDoctors(@PathVariable("doctorId") String doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(DoctorResponseDto.builder().build());
    }

    @GetMapping("/speciality")
    public ResponseEntity<List<String>> getAllSpeciality() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getAllSpeciality());
    }

    @GetMapping("/specialityAndDoctor")
    public ResponseEntity<Map<String, List<DoctorResponseDto>>> getListOfDoctorsForSpeciality() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getListOfDoctorsForSpeciality());
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getDoctorNames() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctorsName());
    }
}
