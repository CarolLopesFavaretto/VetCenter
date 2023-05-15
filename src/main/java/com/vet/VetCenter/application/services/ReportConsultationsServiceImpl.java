package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.ReportConsultationsService;
import com.vet.VetCenter.application.ports.out.ReportConsultationsRepository;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportConsultationsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportConsultationsServiceImpl implements ReportConsultationsService {

    @Autowired
    private ReportConsultationsRepository repository;

    @Override
    public List<ReportConsultations> findAll(ReportConsultationsFilter filter) {
        return repository.findAll(filter);
    }
}
