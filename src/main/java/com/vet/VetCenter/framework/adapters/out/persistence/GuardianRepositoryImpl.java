package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.domain.entity.Guardian;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GuardianRepositoryImpl implements GuardianRepository {


    @Override
    public Guardian save(Guardian guardian) {
        return null;
    }

    @Override
    public List<Guardian> findAll() {
        return null;
    }

    @Override
    public Optional<Guardian> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Guardian update(Guardian guardian, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
