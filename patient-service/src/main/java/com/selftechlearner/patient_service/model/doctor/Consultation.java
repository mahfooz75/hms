package com.selftechlearner.patient_service.model.doctor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public record Consultation(String consultationId, LocalDate date, LocalTime startTime,
                           LocalTime endTime, String procedureDetails) implements Serializable {
}
