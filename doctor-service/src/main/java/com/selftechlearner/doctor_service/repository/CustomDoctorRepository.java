package com.selftechlearner.doctor_service.repository;

import com.selftechlearner.doctor_service.dto.DoctorResponseDto;
import com.selftechlearner.doctor_service.entity.Doctor;

import java.util.List;
import java.util.Map;

public interface CustomDoctorRepository {
    List<String> findSpecialty();

    Map<String, List<DoctorResponseDto>> findDoctorsGroupedBySpecialtyWhereNotSoftDeleted();
}
