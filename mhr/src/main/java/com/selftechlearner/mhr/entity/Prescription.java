package com.selftechlearner.mhr.entity;

import com.selftechlearner.mhr.audit.Auditable;
import com.selftechlearner.mhr.constant.MedicalHealthRecordConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = MedicalHealthRecordConstant.PRESCRIPTION_COLLECTION)
public class Prescription extends Auditable<String> implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String prescriptionId;
    private String medicalRecordId; // Reference to the associated MedicalRecord
    private String medicationName;
    private String dosage;
    private String frequency;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean softDeleted;
}
