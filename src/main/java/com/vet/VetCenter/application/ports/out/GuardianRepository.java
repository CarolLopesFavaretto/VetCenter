package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.GuardianFilter;

import java.util.List;
import java.util.Optional;


public interface GuardianRepository {

    void save(Guardian guardian);

    List<Guardian> findAll(GuardianFilter filter);

    Optional<Guardian> findById(Long id);

    void update(Guardian guardian);

    void deleteById(Long id);
}
