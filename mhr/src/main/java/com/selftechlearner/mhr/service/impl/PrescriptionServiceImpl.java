package com.selftechlearner.mhr.service.impl;

import com.selftechlearner.mhr.dto.PrescriptionRequestDto;
import com.selftechlearner.mhr.entity.Prescription;
import com.selftechlearner.mhr.exception.ExceptionMessage;
import com.selftechlearner.mhr.exception.RecordNotFoundException;
import com.selftechlearner.mhr.mapper.PrescriptionMapper;
import com.selftechlearner.mhr.model.ApiResponse;
import com.selftechlearner.mhr.model.PrescriptionResponse;
import com.selftechlearner.mhr.repository.PrescriptionRepository;
import com.selftechlearner.mhr.service.PrescriptionService;
import com.selftechlearner.mhr.util.MedicalHealthRecordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    @Override
    public ApiResponse addPrescription(PrescriptionRequestDto requestDto) {
        Prescription prescription = prescriptionMapper.toEntity(requestDto);
        prescription.setPrescriptionId(UUID.randomUUID().toString());
        PrescriptionResponse response = prescriptionMapper.toResponse(prescriptionRepository.save(prescription));
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(response, MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findBySoftDeletedFalseOrderByCreatedAtDesc();
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(prescriptionMapper.toResponse(prescriptions), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse getPrescriptionByPrescriptionId(String prescriptionId) {
        Prescription prescription = prescriptionRepository.findByPrescriptionIdAndSoftDeletedFalse(prescriptionId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.PRESCRIPTION_NOT_FOUND));
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(prescriptionMapper.toResponse(prescription), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse updatePrescription(String prescriptionId, PrescriptionRequestDto requestDto) {
        Prescription prescription = prescriptionRepository.findByPrescriptionIdAndSoftDeletedFalse(prescriptionId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.PRESCRIPTION_NOT_FOUND));
        prescriptionMapper.updateEntityFromDto(requestDto, prescription);
        prescription = prescriptionRepository.save(prescription);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse(prescriptionMapper.toResponse(prescription), MedicalHealthRecordUtil.SUCCESS);
    }

    @Override
    public ApiResponse deletePrescription(String prescriptionId) {
        Prescription prescription = prescriptionRepository.findByPrescriptionIdAndSoftDeletedFalse(prescriptionId)
                .orElseThrow(() -> new RecordNotFoundException(ExceptionMessage.PRESCRIPTION_NOT_FOUND));
        prescription.setSoftDeleted(true);
        prescriptionRepository.save(prescription);
        return MedicalHealthRecordUtil.createSuccessMedicalHealthResponse("Prescription deleted successfully", MedicalHealthRecordUtil.SUCCESS);
    }
}
