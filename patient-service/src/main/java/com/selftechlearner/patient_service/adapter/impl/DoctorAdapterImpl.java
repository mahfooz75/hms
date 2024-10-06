package com.selftechlearner.patient_service.adapter.impl;

import com.selftechlearner.patient_service.adapter.DoctorAdapter;
import com.selftechlearner.patient_service.config.DoctorServiceConfig;
import com.selftechlearner.patient_service.model.doctor.DoctorDetailsResponse;
import com.selftechlearner.patient_service.dto.DoctorResponseDto;
import com.selftechlearner.patient_service.mapper.PatientMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class DoctorAdapterImpl implements DoctorAdapter {
    private final RestClient.Builder restClientBuilder;
    private final PatientMapper patientMapper;
    private final DoctorServiceConfig doctorServiceConfig;
    private RestClient restClient;

    @PostConstruct
    public void init() {
        restClient = restClientBuilder.baseUrl(doctorServiceConfig.getBaseUrl())
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }


    @Override
    public Map<String, List<DoctorDetailsResponse>> getDoctorInfo() {
        log.info("Calling doctor service to get Doctor Information");
        Map<String, List<DoctorResponseDto>> response=restClient.get()
                .uri("/specialityAndDoctor")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return patientMapper.convert(response);
    }
}
