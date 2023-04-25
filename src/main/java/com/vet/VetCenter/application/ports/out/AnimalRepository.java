package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.AnimalFilter;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository {

    void save(Animal animal);

    List<Animal> findAll(AnimalFilter filter);

    Optional<Animal> findById(Long id);

    void update(Animal animal);

    void deleteById(Long id);
}
