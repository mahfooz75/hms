package com.selftechlearner.patient_service.dto;

import com.selftechlearner.patient_service.model.patient.Address;
import com.selftechlearner.patient_service.model.patient.ContactInformation;
import com.selftechlearner.patient_service.model.patient.EmergencyContact;
import com.selftechlearner.patient_service.model.patient.MedicalHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private ContactInformation contactInformation;
    private Address address;
    private EmergencyContact emergencyContact;
    private Set<MedicalHistory> medicalHistory;
}
