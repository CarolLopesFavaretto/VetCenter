package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.GuardianFilter;
import com.vet.VetCenter.framework.adapters.in.dtos.request.GuardianRequest;

import java.util.List;
import java.util.Optional;


public interface GuardianService {

    void create(Guardian guardian);

    List<Guardian> findAll(GuardianFilter filter);

    Optional<Guardian> findById(Long id);

    void update(Long id, GuardianRequest guardian);

    void deleteById(Long id);
}
