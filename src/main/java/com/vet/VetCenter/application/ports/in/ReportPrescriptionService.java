package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;

import java.util.List;

public interface ReportPrescriptionService {

    List<ReportPrescription> findAll(ReportPrescriptionFilter filter);
}
