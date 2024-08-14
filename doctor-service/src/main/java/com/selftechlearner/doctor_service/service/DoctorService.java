package com.selftechlearner.doctor_service.service;

import com.selftechlearner.doctor_service.dto.DoctorRequestDto;
import com.selftechlearner.doctor_service.dto.DoctorResponseDto;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    DoctorResponseDto findDoctorByDoctorId(String doctorId);

    List<DoctorResponseDto> getAllDoctors();

    DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto);

    List<DoctorResponseDto> createMultipleDoctors(List<DoctorRequestDto> doctorRequestDto);

    DoctorResponseDto updateDoctor(String doctorId, DoctorRequestDto doctorRequestDto);

    void deleteDoctor(String doctorId);

    List<String> getAllSpeciality();

    Map<String, List<DoctorResponseDto>> getListOfDoctorsForSpeciality();

    List<String> getDoctorsName();
}
