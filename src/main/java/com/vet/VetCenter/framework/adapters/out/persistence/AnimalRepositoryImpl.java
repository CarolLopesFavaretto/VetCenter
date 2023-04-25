package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.AnimalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void save(Animal animal) {
        namedParameterJdbcTemplate.update("insert into animal (name, age, type, race, guardian_id ) " +
                        "values (:name, :age, :type, :race, :guardianId)"
                , new BeanPropertySqlParameterSource(animal));
    }

    @Override
    public List<Animal> findAll(AnimalFilter filter) {
        Map<String, Object> mapSqlParameterSource = new HashMap<>();
        mapSqlParameterSource.put("name", filter.getName());
        mapSqlParameterSource.put("guardian_id", filter.getGuardianId());

        return namedParameterJdbcTemplate.query("select * " +
                        "from animal where (:name is null or name = :name) "
                        + "and  (:guardian_id is null or guardian_id = :guardian_id) "
                , mapSqlParameterSource,
                ((rs, rowNum) ->
                        new Animal(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("type"),
                                rs.getString("race"),
                                rs.getLong("guardian_id")
                        )));
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return namedParameterJdbcTemplate.query("select * from animal where id = :id",
                        new MapSqlParameterSource("id", id),
                        ((rs, rowNum) -> new Animal(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("type"),
                                rs.getString("race"),
                                rs.getLong("guardian_id")
                        ))).stream()
                .findFirst();
    }

    @Override
    public void update(Animal animal) {
        namedParameterJdbcTemplate.update("update animal set age = :age, name = :name, race = " +
                        ":race, type = :type, guardian_id = :guardianId where id = :id",
                new BeanPropertySqlParameterSource(animal));
    }

    @Override
    public void deleteById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        namedParameterJdbcTemplate.update("delete from animal where id = :id",
                mapSqlParameterSource);
    }
}
