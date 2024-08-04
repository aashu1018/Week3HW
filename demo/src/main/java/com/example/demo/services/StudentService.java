package com.example.demo.services;

import com.example.demo.entities.StudentEntity;
import com.example.demo.entities.SubjectEntity;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public StudentEntity getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public StudentEntity createNewStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public StudentEntity assignStudentToSubject(Long studentId, Long subjectId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        return studentEntity.flatMap(student ->
                subjectEntity.map(subject -> {
                    subject.getStudents().add(student);

                    student.getSubjects().add(subject);
                    studentRepository.save(student);
                    return student;
                })).orElse(null);
    }
}
