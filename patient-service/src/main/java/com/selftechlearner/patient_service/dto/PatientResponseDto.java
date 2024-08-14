package com.selftechlearner.patient_service.dto;

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
public class PatientResponseDto {
    private String id;
    private String patientId;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private ContactInformationDto contactInformation;
    private AddressDto address;
    private EmergencyContactDto emergencyContact;
    private Set<MedicalHistoryDto> medicalHistory;
}
