package com.vet.VetCenter.application.ports.out;

import com.vet.VetCenter.application.ports.in.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public int save(Animal animal) {
        return namedParameterJdbcTemplate.update("insert into animal (name, age, type, race) values (:name, :age, :type, :race)"
                , new BeanPropertySqlParameterSource(animal));
    }

    @Override
    public List<Animal> findAll() {
        return null;
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Animal update(Animal animal, Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
