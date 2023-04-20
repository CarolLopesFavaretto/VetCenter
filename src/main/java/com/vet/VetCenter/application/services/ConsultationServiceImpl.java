package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.mappers.ConsultationMapper;
import com.vet.VetCenter.framework.adapters.in.dtos.request.ConsultationRequest;
import com.vet.VetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    private ConsultationRepository repository;

    @Autowired
    private ConsultationMapper mapper;

    @Override
    public void create(Consultation consultation) {
        mapper.toModel(consultation);
        repository.save(consultation);
    }

    @Override
    public List<Consultation> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Long id, ConsultationRequest request) {
        repository.findById(id).map(consultation -> {
            consultation.setNameVeterinary(request.getNameVeterinary());
            consultation.setValue(request.getValue());
            consultation.setObservations(request.getObservations());
            consultation.setCause(request.getCause());
            consultation.setRegress(request.getRegress());
            consultation.setAnimalId(request.getAnimalId());
            consultation.getDate();
            repository.update(consultation);
            return Optional.of(mapper.toModel(consultation));
        }).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
