package com.selftechlearner.doctor_service.entity;

import com.selftechlearner.doctor_service.audit.Auditable;
import com.selftechlearner.doctor_service.util.Availability;
import com.selftechlearner.doctor_service.util.DoctorConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = DoctorConstant.DOCTOR_COLLECTION)
public class Doctor extends Auditable<String> implements Serializable {
    @Id
    private String id;
    @Indexed(unique = true)
    private String doctorId;
    private String firstName;
    private String lastName;
    private String specialty;
    private String qualifications;
    private Integer experience;
    private String email;
    private String phoneNumber;
    private String department;
    private Availability availability; // Enum to represent availability status
    private List<Shift> shifts; // List of shifts for the doctor
    private List<Consultation> consultations;
    private boolean softDeleted;
}
