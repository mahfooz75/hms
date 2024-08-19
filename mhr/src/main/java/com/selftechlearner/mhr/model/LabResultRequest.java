package com.selftechlearner.mhr.model;

import com.selftechlearner.mhr.constant.LabResultStatus;
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
public class LabResultRequest {
    private String medicalRecordId; // Reference to the associated MedicalRecord
    private String testName;
    private LocalDate testDate;
    private String result;
    private String referenceRange;
    private LabResultStatus status;
}
