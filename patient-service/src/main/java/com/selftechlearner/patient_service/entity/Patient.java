package com.selftechlearner.patient_service.entity;

import com.selftechlearner.patient_service.audit.Auditable;
import com.selftechlearner.patient_service.util.GENDER;
import com.selftechlearner.patient_service.util.PatientConstant;
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
import java.util.Set;

@Document(collection = PatientConstant.PATIENT_COLLECTION)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends Auditable<String> implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private GENDER gender;
    private ContactInformation contactInformation;
    private Address address;
    private EmergencyContact emergencyContact;
    private Set<MedicalHistory> medicalHistory;
    private boolean softDeleted;
}
