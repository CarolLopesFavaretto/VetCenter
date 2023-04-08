package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Consultation;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {

    Consultation save(Consultation consultation);

    List<Consultation> findAll();

    Optional<Consultation> findById(Long id);

    Consultation update(Consultation consultation, Long id);

    void deleteById(Long id);
}
