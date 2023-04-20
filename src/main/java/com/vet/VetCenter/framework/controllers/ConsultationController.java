package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.domain.entity.Consultation;
import com.vet.VetCenter.framework.adapters.in.dtos.request.ConsultationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService service;


    @GetMapping
    public List<Consultation> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Consultation> byId = service.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(byId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsultation(@RequestBody Consultation consultation) {
        service.create(consultation);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsultation(@PathVariable Long id, @RequestBody ConsultationRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsultation(@PathVariable Long id) {
        service.deleteById(id);
    }
}
