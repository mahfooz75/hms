package com.selftechlearner.patient_service.dto;

import java.io.Serializable;

public record ContactInformationDto(String email, String phone) implements Serializable {
}