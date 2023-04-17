package com.vet.VetCenter.application.ports.in;

import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.request.GuardianRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GuardianService {

    void create(Guardian guardian);

    List<Guardian> findAll();

    Optional<Guardian> findById(Long id);

    void update(Long id, GuardianRequest guardian);

    void deleteById(Long id);
}
