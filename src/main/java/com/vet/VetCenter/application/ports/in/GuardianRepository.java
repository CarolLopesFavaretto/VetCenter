package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Guardian;

import java.util.List;
import java.util.Optional;

public interface GuardianRepository {

    Guardian save(Guardian guardian);

    List<Guardian> findAll();

    Optional<Guardian> findById(Long id);

    Guardian update(Guardian guardian, Long id);

    void deleteById(Long id);
}
