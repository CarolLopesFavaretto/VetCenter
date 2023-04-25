package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.AnimalFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.AnimalRequest;

import java.util.List;
import java.util.Optional;


public interface AnimalService {

    void create(Animal request);

    List<Animal> findAll(AnimalFilter filter);

    Optional<Animal> findById(Long id);

    void update(Long id, AnimalRequest request);

    void deleteById(Long id);
}
