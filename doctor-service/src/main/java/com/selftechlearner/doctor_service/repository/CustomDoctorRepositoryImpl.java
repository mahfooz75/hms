package com.selftechlearner.doctor_service.repository;

import com.selftechlearner.doctor_service.dto.DoctorResponseDto;
import com.selftechlearner.doctor_service.entity.Doctor;
import com.selftechlearner.doctor_service.mapper.DoctorMapper;
import com.selftechlearner.doctor_service.util.DoctorConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CustomDoctorRepositoryImpl implements CustomDoctorRepository {
    private final MongoTemplate mongoTemplate;
    private final DoctorMapper doctorMapper;

    @Override
    public List<String> findSpecialty() {
        Query query = new Query();
        query.addCriteria(Criteria.where("softDeleted").is(false));
        return mongoTemplate.findDistinct(query, "specialty", Doctor.class, String.class);
    }

    @Override
    public Map<String, List<DoctorResponseDto>> findDoctorsGroupedBySpecialtyWhereNotSoftDeleted() {
        // Match stage to filter documents where softDelete is false
        MatchOperation matchOperation = Aggregation.match(Criteria.where("softDeleted").is(false));
        // Group stage to group doctors by specialty
        GroupOperation groupOperation = Aggregation.group("specialty")
                .push("$$ROOT").as("doctors");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation);
        AggregationResults<GroupedResult> results = mongoTemplate.aggregate(aggregation, DoctorConstant.DOCTOR_COLLECTION, GroupedResult.class);
        Map<String, List<DoctorResponseDto>> doctorsBySpecialty = new HashMap<>();
        for (GroupedResult result : results.getMappedResults()) {
            doctorsBySpecialty.put(result.id(), doctorMapper.toResponseDto(result.doctors()));
        }
        return doctorsBySpecialty;
    }

    private record GroupedResult(String id, List<Doctor> doctors) {
    }
}
