package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.domain.entity.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConsultationRepositoryImpl implements ConsultationRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void save(Consultation consultation) {

        namedParameterJdbcTemplate.update("insert into consultation (name_veterinary, value, cause, " +
                        "observations, date, regress, animal_id) values (:nameVeterinary, " +
                        ":value, :cause, :observations, :date, :regress, :animalId)"
                , new BeanPropertySqlParameterSource(consultation));
    }

    @Override
    public List<Consultation> findAll() {

        return namedParameterJdbcTemplate.query("select id, name_veterinary, value, cause, observations, date, " +
                        "regress, animal_id from consultation",
                ((rs, rowNum) ->
                        new Consultation(
                                rs.getLong("id"),
                                rs.getString("name_veterinary"),
                                rs.getDouble("value"),
                                rs.getString("cause"),
                                rs.getString("observations"),
                                rs.getDate("date").toLocalDate(),
                                rs.getBoolean("regress"),
                                rs.getLong("animal_id")
                        )));
    }

    @Override
    public Optional<Consultation> findById(Long id) {

        return namedParameterJdbcTemplate.query("select * from consultation where id = :id",
                        new MapSqlParameterSource("id", id),
                        ((rs, rowNum) -> new Consultation(
                                rs.getLong("id"),
                                rs.getString("name_veterinary"),
                                rs.getDouble("value"),
                                rs.getString("cause"),
                                rs.getString("observations"),
                                rs.getDate("date").toLocalDate(),
                                rs.getBoolean("regress"),
                                rs.getLong("animal_id")
                        ))).stream()
                .findFirst();
    }

    @Override
    public void update(Consultation consultation) {
        namedParameterJdbcTemplate.update("update consultation set regress = :regress, name_veterinary = :nameVeterinary, value = " +
                        ":value, cause = :cause, observations = :observations, animal_id = :animalId  where id = :id",
                new BeanPropertySqlParameterSource(consultation));
    }

    @Override
    public void deleteById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        namedParameterJdbcTemplate.update("delete from consultation where id = :id",
                mapSqlParameterSource);
    }
}
