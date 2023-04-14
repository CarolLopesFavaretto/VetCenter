package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Consultation;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepository {

    int save(Consultation consultation);

    List<Consultation> findAll();

    Optional<Consultation> findById(Long id);

    int update(Consultation consultation);

    int deleteById(Long id);
}
