package com.example.demo.services;

import com.example.demo.entities.ProfessorEntity;
import com.example.demo.entities.StudentEntity;
import com.example.demo.entities.SubjectEntity;
import com.example.demo.repositories.ProfessorRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;

    public ProfessorService(StudentRepository studentRepository, SubjectRepository subjectRepository, ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.professorRepository = professorRepository;
    }

    public ProfessorEntity getProfessorById(Long professorId) {
        return professorRepository.findById(professorId).orElse(null);
    }

    public ProfessorEntity createNewProfessor(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

    public ProfessorEntity assignProfessorToSubject(Long professorId, Long subjectId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<SubjectEntity> subjectEntity = subjectRepository.findById(subjectId);

        return professorEntity.flatMap(professor ->
                subjectEntity.map(subject -> {
                    subject.setProfessor(professor);
                    subjectRepository.save(subject);

                    professor.getSubjects().add(subject);
                    return professor;
                })).orElse(null);
    }

    public ProfessorEntity assignProfessorToStudent(Long professorId, Long studentId) {
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);

        return professorEntity.flatMap(professor ->
                studentEntity.map(student -> {
                    student.getProfessors().add(professor);

                    professor.getStudents().add(student);
                    professorRepository.save(professor);
                    return professor;
                })).orElse(null);
    }
}
