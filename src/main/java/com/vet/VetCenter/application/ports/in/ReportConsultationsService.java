package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportConsultationsFilter;

import java.util.List;

public interface ReportConsultationsService {

    List<ReportConsultations> findAll(ReportConsultationsFilter filter);
}
