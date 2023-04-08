package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AnimalRepository {

    int save(Animal animal);

    List<Animal> findAll();

    Optional<Animal> findById( Long id);

    int update(Animal animal);

    int deleteById(Long id);


}
