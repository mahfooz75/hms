package com.selftechlearner.doctor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    private String phoneNumber;
    private String department;
}
