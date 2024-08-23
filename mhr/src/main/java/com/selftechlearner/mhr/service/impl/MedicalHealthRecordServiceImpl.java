package com.selftechlearner.mhr.service.impl;

import com.selftechlearner.mhr.dto.MedicalRecordRequestDto;
import com.selftechlearner.mhr.entity.LabResult;
import com.selftechlearner.mhr.entity.MedicalRecord;
import com.selftechlearner.mhr.entity.Prescription;
import com.selftechlearner.mhr.exception.ExceptionMessage;
import com.selftechlearner.mhr.exception.RecordNotFoundException;
import com.selftechlearner.mhr.mapper.LabResultMapper;
import com.selftechlearner.mhr.mapper.MedicalRecordMapper;
import com.selftechlearner.mhr.mapper.PrescriptionMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.LabResultRequest;
import com.selftechlearner.mhr.model.MedicalRecordResponse;
import com.selftechlearner.mhr.model.PrescriptionRequest;
import com.selftechlearner.mhr.repository.LabResultRepository;
import com.selftechlearner.mhr.repository.MedicalHealthRecordRepository;
import com.selftechlearner.mhr.repository.PrescriptionRepository;
import com.selftechlearner.mhr.service.MedicalHealthRecordService;
import com.selftechlearner.mhr.util.MedicalHealthRecordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicalHealthRecordServiceImpl implements MedicalHealthRecordService {
    private final MedicalHealthRecordRepository medicalHealthRecordRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final LabResultRepository labResultRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final PrescriptionMapper prescriptionMapper;
    private final LabResultMapper labResultMapper;

    @Override
    @Transactional
    public ApiResponse addMedicalRecord(MedicalRecordRequestDto medicalRecordRequestDto) {
        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordRequestDto);
        medicalRecord.setMedicalRecordId(UUID.randomUUID().toString());
        medicalRecord = medicalHealthRecordRepository.save(medicalRecord);

        MedicalRecordResponse medicalRecordResponse = medicalRecordMapper.toResponse(medicalRecord);

        PrescriptionRequest prescriptionRequest = PrescriptionRequest.builder()
                .medicalRecordId(medicalRecordResponse.getMedicalRecordId())
                .build();
        Prescription prescription = prescriptionMapper.toEntity(prescriptionRequest);
        prescription.setPrescriptionId(UUID.randomUUID().toString());
        prescriptionRepository.save(prescription);

        LabResultRequest labResultRequest = LabResultRequest.builder()
                .medicalRecordId(medicalRecordResponse.getMedicalRecordId())
                .build();
        LabResult labResult = labResultMapper.toEntity(labResultRequest);
        labResult.setLabResultId(UUID.randomUUID().toString());
        labResultRepository.save(labResult);

        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(medicalRecordResponse, MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getMedicalRecord(String recordId) {
        MedicalRecord medicalRecord = medicalHealthRecordRepository.findByMedicalRecordIdAndSoftDeletedFalse(recordId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.MEDICAL_RECORD_NOT_FOUND + recordId));
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(medicalRecordMapper.toResponse(medicalRecord), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getMedicalRecordByPatientId(String patientId) {
        List<MedicalRecord> medicalRecords = medicalHealthRecordRepository.findByPatientIdAndSoftDeletedFalseOrderByCreatedAtDesc(patientId);
        if (medicalRecords == null || medicalRecords.isEmpty()) {
            return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(medicalRecordMapper.toResponse(medicalRecords), "No record available for patient " + patientId);
        }
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(medicalRecordMapper.toResponse(medicalRecords), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse updateMedicalRecord(String recordId, MedicalRecordRequestDto recordRequestDto) {
        MedicalRecord medicalRecord = medicalHealthRecordRepository.findByMedicalRecordIdAndSoftDeletedFalse(recordId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.MEDICAL_RECORD_NOT_FOUND + recordId));
        medicalRecordMapper.updateEntityFromDto(recordRequestDto, medicalRecord);
        medicalRecord = medicalHealthRecordRepository.save(medicalRecord);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(medicalRecordMapper.toResponse(medicalRecord), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse deleteMedicalRecord(String recordId) {
        MedicalRecord medicalRecord = medicalHealthRecordRepository.findByMedicalRecordIdAndSoftDeletedFalse(recordId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.MEDICAL_RECORD_NOT_FOUND + recordId));
        medicalRecord.setSoftDeleted(true);
        medicalHealthRecordRepository.save(medicalRecord);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse("Record deleted", MedicalHealthRecordUtil.SUCCESS);
    }

}
