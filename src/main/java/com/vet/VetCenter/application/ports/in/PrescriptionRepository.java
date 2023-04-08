package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    Prescription save(Prescription prescription);

    List<Prescription> findAll();

    Optional<Prescription> findById(Long id);

    Prescription update(Prescription prescription, Long id);

    void deleteById (Long id);
}
