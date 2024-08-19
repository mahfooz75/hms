package com.selftechlearner.mhr.repository;

import com.selftechlearner.mhr.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {
    Optional<Prescription> findByPrescriptionIdAndSoftDeletedFalse(String prescriptionId);
    List<Prescription> findBySoftDeletedFalseOrderByCreatedAtDesc();
}
