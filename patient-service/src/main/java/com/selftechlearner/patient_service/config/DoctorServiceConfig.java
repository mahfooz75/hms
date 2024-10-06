package com.selftechlearner.patient_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "doctor.service")
@Data
public class DoctorServiceConfig {
    private String baseUrl;
}
