package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    void save(Prescription prescription);

    List<Prescription> findAll();

    Optional<Prescription> findById(Long id);

    void update(Prescription prescription);

    void deleteById (Long id);
}
