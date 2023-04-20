package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.PrescriptionRepository;
import com.vet.VetCenter.domain.entity.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {


    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void save(Prescription prescription) {
        namedParameterJdbcTemplate.update("insert into prescription (medication, date, consultation_id) values " +
                "(:medication, :date, :consultationId)", new BeanPropertySqlParameterSource(prescription));
    }

    @Override
    public List<Prescription> findAll() {
        return namedParameterJdbcTemplate.query("select id, medication, date, consultation_id from prescription "
                , ((rs, rowNum) -> new Prescription(
                        rs.getLong("id"),
                        rs.getString("medication"),
                        rs.getDate("date").toLocalDate(),
                        rs.getLong("consultation_id")
                )));
    }

    @Override
    public Optional<Prescription> findById(Long id) {
        return namedParameterJdbcTemplate.query("select * from prescription where id = :id",
                        new MapSqlParameterSource("id", id),
                        ((rs, rowNum) -> new Prescription(
                                rs.getLong("id"),
                                rs.getString("medication"),
                                rs.getDate("date").toLocalDate(),
                                rs.getLong("consultation_id")
                        ))).stream()
                .findFirst();
    }

    @Override
    public void update(Prescription prescription) {
        namedParameterJdbcTemplate.update("update prescription set medication = :medication, " +
                        "consultation_id = :consultationId where id = :id",
                new BeanPropertySqlParameterSource(prescription));
    }

    @Override
    public void deleteById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        namedParameterJdbcTemplate.update("delete from prescription where id = :id",
                mapSqlParameterSource);
    }
}
