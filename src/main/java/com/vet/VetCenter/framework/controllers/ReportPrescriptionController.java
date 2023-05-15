package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.ReportPrescriptionService;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report/prescriptions")
public class ReportPrescriptionController {

    @Autowired
    private ReportPrescriptionService service;

    @GetMapping
    public List<ReportPrescription> reportConsultationsList(ReportPrescriptionFilter filter) {
        return service.findAll(filter);
    }
}
