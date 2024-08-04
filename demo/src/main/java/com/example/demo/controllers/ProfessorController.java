package com.example.demo.controllers;

import com.example.demo.entities.ProfessorEntity;
import com.example.demo.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(path = "/{professorId}")
    public ProfessorEntity getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }

    @PostMapping
    public ProfessorEntity createNewProfessor(@RequestBody ProfessorEntity professorEntity){
        return professorService.createNewProfessor(professorEntity);
    }

    @PutMapping(path = "/{professorId}/subject/{subjectId}")
    public ProfessorEntity assignProfessorToSubject(@PathVariable Long professorId,
                                                    @PathVariable Long subjectId){
        return professorService.assignProfessorToSubject(professorId, subjectId);
    }

    @PutMapping(path = "/{professorId}/student/{studentId}")
    public ProfessorEntity assignProfessorToStudent(@PathVariable Long professorId,
                                                    @PathVariable Long studentId){
        return professorService.assignProfessorToStudent(professorId, studentId);
    }
}
