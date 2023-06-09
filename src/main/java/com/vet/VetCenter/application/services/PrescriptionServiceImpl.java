package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.PrescriptionService;
import com.vet.VetCenter.application.ports.out.PrescriptionRepository;
import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.PrescriptionFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.mappers.PrescriptionMapper;
import com.vet.VetCenter.framework.adapters.in.dtos.request.PrescriptionRequest;
import com.vet.VetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository repository;

    @Autowired
    private PrescriptionMapper mapper;

    @Override
    public void create(Prescription prescription) {
        mapper.toModel(prescription);
        repository.save(prescription);
    }

    @Override
    public List<Prescription> findAll(PrescriptionFilter filter) {
        return repository.findAll(filter);
    }

    @Override
    public Optional<Prescription> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Long id, PrescriptionRequest request) {
        repository.findById(id).map(prescription -> {
            prescription.setMedication(request.getMedication());
            prescription.setDate(request.getDate());
            prescription.setConsultationId(request.getConsultationId());
            repository.update(prescription);
            return Optional.of(mapper.toModel(prescription));
        }).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
