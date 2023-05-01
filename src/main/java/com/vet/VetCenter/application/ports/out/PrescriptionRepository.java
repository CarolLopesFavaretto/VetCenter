package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.PrescriptionFilter;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    void save(Prescription prescription);

    List<Prescription> findAll(PrescriptionFilter filter);

    Optional<Prescription> findById(Long id);

    void update(Prescription prescription);

    void deleteById (Long id);
}
