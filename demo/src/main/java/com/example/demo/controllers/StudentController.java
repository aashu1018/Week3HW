package com.example.demo.controllers;

import com.example.demo.entities.StudentEntity;
import com.example.demo.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "/{studentId}")
    public StudentEntity getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public StudentEntity createNewStudent(@RequestBody StudentEntity studentEntity){
        return studentService.createNewStudent(studentEntity);
    }

    @PutMapping(path = "/{studentId}/subject/{subjectId}")
    public StudentEntity assignStudentToSubject(@PathVariable Long studentId,
                                                @PathVariable Long subjectId){
        return studentService.assignStudentToSubject(studentId, subjectId);
    }
}
