package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.PrescriptionRepository;
import com.vet.VetCenter.domain.entity.Prescription;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {


    @Override
    public Prescription save(Prescription prescription) {
        return null;
    }

    @Override
    public List<Prescription> findAll() {
        return null;
    }

    @Override
    public Optional<Prescription> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Prescription update(Prescription prescription, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
