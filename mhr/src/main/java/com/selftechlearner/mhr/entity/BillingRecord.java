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
@Document(collection = MedicalHealthRecordConstant.BILLING_RECORD_COLLECTION)
public class BillingRecord extends Auditable<String> implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String billingId;
    private String medicalRecordId; // Reference to the associated MedicalRecord
    private String patientId;
    private String doctorId;
    private String serviceDescription; // e.g., Consultation, Lab Test
    private double amount;
    private LocalDate billingDate;
    private boolean softDeleted;
}
