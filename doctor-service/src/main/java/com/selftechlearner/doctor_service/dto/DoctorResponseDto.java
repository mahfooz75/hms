package com.selftechlearner.doctor_service.dto;

import com.selftechlearner.doctor_service.entity.Consultation;
import com.selftechlearner.doctor_service.entity.Shift;
import com.selftechlearner.doctor_service.util.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    private String doctorId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String qualifications;
    private Integer experience;
    private String email;
    private String phoneNumber;
    private String department;
    private Availability availability;
    private List<Shift> shifts;
    private List<Consultation> consultations;
}
