package com.selftechlearner.mhr.dto;

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
public class MedicalRecordRequestDto {
    private String patientId;
    private String doctorId;
    private LocalDate dateOfVisit;
    private String diagnosis;
    private String treatment;
    private String notes;
}
