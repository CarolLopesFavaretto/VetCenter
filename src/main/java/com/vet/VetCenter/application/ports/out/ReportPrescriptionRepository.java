package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;

import java.util.List;

public interface ReportPrescriptionRepository {

    List<ReportPrescription> findAll(ReportPrescriptionFilter filter);
}
