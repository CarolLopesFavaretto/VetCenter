package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.domain.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
        return namedParameterJdbcTemplate.query("select id, name, age, type, race, guardian_id " +
                        "from animal" ,
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
    public int update(Animal animal) {
        return namedParameterJdbcTemplate.update("update animal set age = :age where id = :id",
                new BeanPropertySqlParameterSource(animal));
    }

    @Override
    public int deleteById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        return namedParameterJdbcTemplate.update("delete from animal where id = :id",
                mapSqlParameterSource);
    }

    @Override
    public int deleteAll() {
        return namedParameterJdbcTemplate.update("delete from animal",
                new MapSqlParameterSource());
    }
}
