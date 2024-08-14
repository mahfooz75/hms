package com.selftechlearner.patient_service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record MedicalHistoryDto(String condition, LocalDate diagnosedDate) implements Serializable {
}
