package com.vet.VetCenter.application.services;

import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.request.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;


    @Override
    public Animal create(AnimalRequest request) {
        return null;
    }

    @Override
    public List<Animal> findAll() {
        return null;
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Animal update(Long id, AnimalRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
