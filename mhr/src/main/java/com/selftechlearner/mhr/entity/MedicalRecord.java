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
@Document(collection = MedicalHealthRecordConstant.MEDICAL_RECORDS_COLLECTION)
public class MedicalRecord extends Auditable<String> implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String recordId;
    private String patientId;
    private String doctorId;
    private LocalDate dateOfVisit;
    private String diagnosis;
    private String treatment;
    private String notes;
    private boolean softDeleted;
}
