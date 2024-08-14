package com.selftechlearner.patient_service.repository;

import com.selftechlearner.patient_service.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByPatientIdAndSoftDeletedFalse(String patientId);
    List<Patient> findBySoftDeletedFalseOrderByCreatedAtDesc();
}
