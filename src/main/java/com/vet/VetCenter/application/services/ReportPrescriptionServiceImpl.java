package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.ReportPrescriptionService;
import com.vet.VetCenter.application.ports.out.ReportPrescriptionRepository;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportPrescriptionServiceImpl implements ReportPrescriptionService {

    @Autowired
    private ReportPrescriptionRepository repository;

    @Override
    public List<ReportPrescription> findAll(ReportPrescriptionFilter filter) {
        return repository.findAll(filter);
    }
}
