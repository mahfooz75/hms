package com.selftechlearner.doctor_service.service.impl;

import com.selftechlearner.doctor_service.dto.DoctorRequestDto;
import com.selftechlearner.doctor_service.dto.DoctorResponseDto;
import com.selftechlearner.doctor_service.entity.Doctor;
import com.selftechlearner.doctor_service.exception.DoctorNotFoundException;
import com.selftechlearner.doctor_service.exception.ExceptionMessage;
import com.selftechlearner.doctor_service.mapper.DoctorMapper;
import com.selftechlearner.doctor_service.repository.DoctorRepository;
import com.selftechlearner.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final ApplicationContext applicationContext;

    @Override
    public DoctorResponseDto findDoctorByDoctorId(String doctorId) {
        log.info("Getting doctor by doctor id {}", doctorId);
        Doctor doctor = doctorRepository.findByDoctorIdAndSoftDeletedFalse(doctorId).orElseThrow(() -> new DoctorNotFoundException(ExceptionMessage.DOCTOR_NOT_FOUND));
        return doctorMapper.toResponseDto(doctor);
    }

    @Override
    @Cacheable("doctors")
    public List<DoctorResponseDto> getAllDoctors() {
        log.info("Getting all doctor");
        log.info("Creating cache");
        return doctorMapper.toResponseDto(doctorRepository.findBySoftDeletedFalseOrderByCreatedAtDesc());
    }

    @Override
    @CacheEvict(value = "doctors", allEntries = true)
    public DoctorResponseDto createDoctor(DoctorRequestDto doctorRequestDto) {
        log.info("Creating doctor");
        Doctor doctor = doctorMapper.toEntity(doctorRequestDto);
        doctor.setDoctorId(UUID.randomUUID().toString());
        return doctorMapper.toResponseDto(doctorRepository.save(doctor));
    }

    @Override
    public List<DoctorResponseDto> createMultipleDoctors(List<DoctorRequestDto> doctorRequestDto) {
        log.info("Getting multiple doctors");
        List<Doctor> doctors = doctorRequestDto.stream()
                .map(doctorDto -> {
                    Doctor doctor = doctorMapper.toEntity(doctorDto);
                    doctor.setDoctorId(UUID.randomUUID().toString());
                    return doctor;
                }).toList();
        return doctorMapper.toResponseDto(doctorRepository.saveAll(doctors));
    }

    @Override
    @CacheEvict(value = "doctors", allEntries = true)
    public DoctorResponseDto updateDoctor(String doctorId, DoctorRequestDto doctorRequestDto) {
        log.info("Updating doctor by doctor id {}", doctorId);
        Doctor doctor = doctorRepository.findByDoctorIdAndSoftDeletedFalse(doctorId).orElseThrow(() -> new DoctorNotFoundException(ExceptionMessage.DOCTOR_NOT_FOUND));
        doctorMapper.updateEntityFromDto(doctorRequestDto, doctor);
        return doctorMapper.toResponseDto(doctorRepository.save(doctor));
    }

    @Override
    @CacheEvict(value = "doctors", allEntries = true)
    public void deleteDoctor(String doctorId) {
        log.info("Deleting doctor by doctor id {}", doctorId);
        Doctor doctor = doctorRepository.findByDoctorIdAndSoftDeletedFalse(doctorId).orElseThrow(() -> new DoctorNotFoundException(ExceptionMessage.DOCTOR_NOT_FOUND));
        doctor.setSoftDeleted(true);
        doctorRepository.save(doctor);
    }

    @Override
    public List<String> getAllSpeciality() {
        log.info("Getting all speciality");
        return applicationContext.getBean(DoctorService.class).getAllDoctors()
                .stream()
                .map(DoctorResponseDto::getSpecialty)
                .collect(Collectors.toUnmodifiableSet())
                .stream().sorted(String::compareToIgnoreCase)
                .toList();
    }

    @Override
    public Map<String, List<DoctorResponseDto>> getListOfDoctorsForSpeciality() {
        log.info("Map by speciality and doctor");
        return applicationContext.getBean(DoctorService.class).getAllDoctors()
                .stream()
                .collect(Collectors.groupingBy(DoctorResponseDto::getSpecialty));
    }

    @Override
    public List<String> getDoctorsName() {
        log.info("Doctor names");
        return applicationContext.getBean(DoctorService.class).getAllDoctors().stream()
                .sorted((o1, o2) -> o1.getFirstName().compareToIgnoreCase(o2.getFirstName()))
                .map(doctor -> doctor.getFirstName() + " " + doctor.getLastName())
                .toList();
    }
}
