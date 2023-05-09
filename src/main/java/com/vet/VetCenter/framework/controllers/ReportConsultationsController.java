package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.ReportConsultationsService;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportConsultationsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report/consultations")
public class ReportConsultationsController {

    @Autowired
    private ReportConsultationsService service;

    @GetMapping
    public List<ReportConsultations> reportConsultationsList(ReportConsultationsFilter filter) {
        return service.findAll(filter);
    }
}
