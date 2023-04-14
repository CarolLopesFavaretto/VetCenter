package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    int save(Prescription prescription);

    List<Prescription> findAll();

    Optional<Prescription> findById(Long id);

    int update(Prescription prescription);

    int deleteById (Long id);
}
