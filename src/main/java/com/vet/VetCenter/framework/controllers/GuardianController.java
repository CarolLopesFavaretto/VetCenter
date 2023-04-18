package com.vet.VetCenter.framework.controllers;

import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.mappers.GuardianMapper;
import com.vet.VetCenter.framework.adapters.in.dtos.request.GuardianRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/guardian")
public class GuardianController {

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private GuardianMapper mapper;

    @GetMapping
    public List<Guardian> listAll() {
        return guardianService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<Guardian> byId = guardianService.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(byId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity createGuardian(@RequestBody Guardian request) {
        guardianService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateGuardian(@PathVariable Long id, @RequestBody GuardianRequest request) {
        guardianService.update(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteById(id);
    }
}
