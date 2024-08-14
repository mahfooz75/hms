package com.selftechlearner.patient_service.entity;

import java.io.Serializable;
import java.time.LocalDate;

public record MedicalHistory(String condition, LocalDate diagnosedDate) implements Serializable {
}
