package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.mappers.AnimalMapper;
import com.vet.VetCenter.framework.adapters.in.dtos.request.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private AnimalMapper mapper;


    @Override
    public void create(Animal animal) {
        mapper.toModel(animal);
        repository.save(animal);
    }

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Long id, AnimalRequest request) {
        repository.findById(id).map(animal -> {
            animal.setName(request.getName());
            animal.setAge(request.getAge());
            animal.setRace(request.getRace());
            animal.setType(request.getType());
            animal.setGuardianId(request.getGuardianId());
            repository.update(animal);
            return Optional.of(mapper.toModel(animal));
        }).orElse(Optional.empty());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
