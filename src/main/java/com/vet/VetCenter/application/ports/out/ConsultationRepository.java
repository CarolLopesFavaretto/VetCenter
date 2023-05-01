package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ConsultationFilter;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {

    void save(Consultation consultation);

    List<Consultation> findAll(ConsultationFilter filter);

    Optional<Consultation> findById(Long id);

    void update(Consultation consultation);

    void deleteById(Long id);
}
