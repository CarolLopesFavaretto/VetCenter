package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.PrescriptionFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.PrescriptionRequest;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {

    void create(Prescription prescription);

    List<Prescription> findAll(PrescriptionFilter filter);

    Optional<Prescription> findById(Long id);

    void update(Long id, PrescriptionRequest request) throws Exception;

    void deleteById(Long id);
}
