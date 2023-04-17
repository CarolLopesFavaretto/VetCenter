package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.request.ConsultationRequest;

import java.util.List;
import java.util.Optional;

public interface ConsultationService {

    Consultation create(ConsultationRequest request);

    List<Consultation> findAll();

    Optional<Consultation> findById(Long id);

    Consultation update(Long id, ConsultationRequest request);

    void deleteById(Long id);

}
