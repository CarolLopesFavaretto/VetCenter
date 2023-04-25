package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.AnimalFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.AnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping
    public List<Animal> listAll(AnimalFilter filter) {
        return service.findAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Animal> byId = service.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(byId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAnimal(@RequestBody Animal animal) {
        service.create(animal);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnimal(@PathVariable Long id) {
        service.deleteById(id);
    }
}
