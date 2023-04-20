package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.request.PrescriptionRequest;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    void create(Prescription prescription);

    List<Prescription> findAll();

    Optional<Prescription> findById(Long id);

    void update(Long id, PrescriptionRequest request);

    void deleteById(Long id);
}
