package com.example.demo.controllers;

import com.example.demo.entities.AdmissionRecordEntity;
import com.example.demo.services.AdmissionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admissionRecords")
public class AdmissionRecordController {

    private final AdmissionService admissionService;

    public AdmissionRecordController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @GetMapping(path = "/{admissionRecordId}")
    public AdmissionRecordEntity getAdmissionRecordById(@PathVariable Long admissionRecordId){
        return admissionService.getAdmissionRecordById(admissionRecordId);
    }

    @PostMapping
    public AdmissionRecordEntity createNewAdmissionRecord(@RequestBody AdmissionRecordEntity admissionRecordEntity){
        return admissionService.createNewAdmissionRecord(admissionRecordEntity);
    }

    @PutMapping(path = "/{admissionRecordId}/student/{studentId}")
    public AdmissionRecordEntity assignAdmissionRecordOfStudent(@PathVariable Long admissionRecordId,
                                                                @PathVariable Long studentId){
        return admissionService.assignAdmissionRecordOfStudent(admissionRecordId, studentId);
    }
}
