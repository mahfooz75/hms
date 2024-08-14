package com.selftechlearner.doctor_service.repository;

import com.selftechlearner.doctor_service.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String>, CustomDoctorRepository {

    /*@Query(value = "{ 'softDeleted': false }", sort = "{ 'createdAt': -1 }")
    List<Doctor> findAllNotSoftDeletedSortedByCreatedAt();*/

    List<Doctor> findBySoftDeletedFalseOrderByCreatedAtDesc();

    Optional<Doctor> findByDoctorIdAndSoftDeletedFalse(String doctorId);

    /*@Query(value = "{'softDeleted':false}", fields = "{'_id':0,'specialty': 1}")
        //value = "{}": This selects all documents in the collection.
        //fields = "{'specialty': 1}": This projects only the specialty field.
    List<String> findSpecialty();*/
}
