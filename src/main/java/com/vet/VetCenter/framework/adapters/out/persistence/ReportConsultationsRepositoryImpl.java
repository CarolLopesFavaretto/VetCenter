package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.ReportConsultationsRepository;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportConsultationsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportConsultationsRepositoryImpl implements ReportConsultationsRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<ReportConsultations> findAll(ReportConsultationsFilter filter) {

        Map<String, Object> mapSqlParameterSource = new HashMap<>();
        mapSqlParameterSource.put("name_animal", filter.getNameAnimal());
        mapSqlParameterSource.put("name_veterinary", filter.getNameVeterinary());
        mapSqlParameterSource.put("name_guardian", filter.getNameGuardian());

        String sql = "select g.name name_guardian, cpf, telephone, a.name  name_animal, a.age, a.type," +
                " a.race, c.name_veterinary, c.value, c.cause, c.observations, c.date, c.regress " +
                " from guardian g inner join animal a on g.id = a.guardian_id " +
                "                inner join consultation c on a.id = c.animal_id " +
                "                where ( cast(:name_animal as varchar) is null or a.name = :name_animal) " +
                "                and ( cast(:name_veterinary as varchar) is null or c.name_veterinary  = :name_veterinary) ";

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource,
                (rs, rowNum) ->
                        new ReportConsultations(
                                rs.getString("name_guardian"),
                                rs.getString("cpf"),
                                rs.getLong("telephone"),
                                rs.getString("name_animal"),
                                rs.getInt("age"),
                                rs.getString("type"),
                                rs.getString("race"),
                                rs.getString("name_veterinary"),
                                rs.getDouble("value"),
                                rs.getString("cause"),
                                rs.getString("observations"),
                                rs.getDate("date").toLocalDate(),
                                rs.getBoolean("regress")
                        ));
    }
}
