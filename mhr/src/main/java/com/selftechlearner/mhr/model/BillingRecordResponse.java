package com.selftechlearner.mhr.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillingRecordResponse {
    private String billingId;
    private String medicalRecordId; // Reference to the associated MedicalRecord
    private String patientId;
    private String doctorId;
    private String serviceDescription; // e.g., Consultation, Lab Test
    private double amount;
    private LocalDate billingDate;
}
