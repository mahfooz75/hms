package com.selftechlearner.mhr.repository;

import com.selftechlearner.mhr.entity.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalHealthRecordRepository extends MongoRepository<MedicalRecord, String> {

    List<MedicalRecord> findByPatientIdAndSoftDeletedFalseOrderByCreatedAtDesc(String patientId);

    Optional<MedicalRecord> findByMedicalRecordIdAndSoftDeletedFalse(String medicalRecordId);
}
