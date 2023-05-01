package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ConsultationFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.ConsultationRequest;

import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    void create(Consultation consultation);

    List<Consultation> findAll(ConsultationFilter filter);

    Optional<Consultation> findById(Long id);

    void update(Long id, ConsultationRequest request);

    void deleteById(Long id);

}
