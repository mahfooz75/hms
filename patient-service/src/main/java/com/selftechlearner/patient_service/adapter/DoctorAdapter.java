package com.selftechlearner.patient_service.adapter;

import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;

import java.util.List;
import java.util.Map;

public interface DoctorAdapter {
    Map<String, List<DoctorDetailsResponse>> getDoctorInfo();
}
