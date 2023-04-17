package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.domain.entity.Guardian;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuardianRepository {

    void save(Guardian guardian);

    List<Guardian> findAll();

    Optional<Guardian> findById(Long id);

    void update(Guardian guardian);

    void deleteById(Long id);
}
