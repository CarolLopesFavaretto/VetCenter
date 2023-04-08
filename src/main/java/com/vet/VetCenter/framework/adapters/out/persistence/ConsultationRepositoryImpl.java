package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.domain.entity.Consultation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultationRepositoryImpl implements ConsultationRepository {


    @Override
    public Consultation save(Consultation consultation) {
        return null;
    }

    @Override
    public List<Consultation> findAll() {
        return null;
    }

    @Override
    public Optional<Consultation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Consultation update(Consultation consultation, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
