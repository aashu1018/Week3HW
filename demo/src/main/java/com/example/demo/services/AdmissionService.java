package com.example.demo.services;

import com.example.demo.entities.AdmissionRecordEntity;
import com.example.demo.entities.StudentEntity;
import com.example.demo.repositories.AdmissionRecordRepository;
import com.example.demo.repositories.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AdmissionService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final StudentRepository studentRepository;

    public AdmissionService(AdmissionRecordRepository admissionRecordRepository, StudentRepository studentRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentRepository = studentRepository;
    }

    public AdmissionRecordEntity getAdmissionRecordById(Long admissionRecordId) {
        return admissionRecordRepository.findById(admissionRecordId).orElse(null);
    }

    public AdmissionRecordEntity createNewAdmissionRecord(AdmissionRecordEntity admissionRecordEntity) {
        return admissionRecordRepository.save(admissionRecordEntity);
    }

    public AdmissionRecordEntity assignAdmissionRecordOfStudent(Long admissionRecordId, Long studentId) {
        Optional<AdmissionRecordEntity> admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return admissionRecordEntity.flatMap(admissionRecord->
                studentEntity.map(student -> {
                    admissionRecord.setStudents(student);
                    return admissionRecordRepository.save(admissionRecord);
                })).orElse(null);
    }
}
