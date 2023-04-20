package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.mappers.GuardianMapper;
import com.vet.VetCenter.framework.adapters.in.dtos.request.GuardianRequest;
import com.vet.VetCenter.framework.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    private GuardianRepository repository;

    @Autowired
    private GuardianMapper mapper;

    @Override
    public void create(Guardian request) {
        mapper.toModel(request);
        repository.save(request);
    }

    @Override
    public List<Guardian> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Guardian> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Long id, GuardianRequest request) {
        repository.findById(id).map(guardian -> {
            guardian.setName(request.getName());
            guardian.setCpf(request.getCpf());
            guardian.setTelephone(request.getTelephone());
            repository.update(guardian);
            return Optional.of(mapper.toModel(guardian));

        }).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
