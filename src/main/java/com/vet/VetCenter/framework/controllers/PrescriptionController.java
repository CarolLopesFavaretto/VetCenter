package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.PrescriptionService;
import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.PrescriptionFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.PrescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    @GetMapping
    public List<Prescription> findAll(PrescriptionFilter filter) {
        return service.findAll(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Prescription> byId = service.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(byId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPrescription(@RequestBody Prescription prescription) {
        service.create(prescription);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrescription(@PathVariable Long id, @RequestBody PrescriptionRequest request) throws Exception {
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrescription(@PathVariable Long id) {
        service.deleteById(id);
    }

}
