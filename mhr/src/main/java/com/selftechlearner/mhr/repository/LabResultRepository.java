package com.selftechlearner.mhr.repository;

import com.selftechlearner.mhr.entity.LabResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabResultRepository extends MongoRepository<LabResult, String> {
    Optional<LabResult> findByLabResultIdAndSoftDeletedFalse(String labResultId);

    List<LabResult> findBySoftDeletedFalseOrderByCreatedAtDesc();
}
