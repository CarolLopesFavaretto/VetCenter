package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.ReportPrescriptionRepository;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReportPrescriptionRepositoryImpl implements ReportPrescriptionRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<ReportPrescription> findAll(ReportPrescriptionFilter filter) {
        Map<String, Object> mapSqlParameterSource = new HashMap<>();
        mapSqlParameterSource.put("name_animal", filter.getNameAnimal());
        mapSqlParameterSource.put("name_veterinary", filter.getNameVeterinary());
        mapSqlParameterSource.put("medication", filter.getMedication());

        String sql = "select g.name name_guardian, cpf, telephone, a.name  name_animal, " +
                " c.name_veterinary, c.cause, c.observations, p.medication, p.date " +
                " from guardian g inner join animal a on g.id = a.guardian_id " +
                "                inner join consultation c on a.id = c.animal_id " +
                "                inner join prescription p on c.id = p.consultation_id " +
                "                where ( cast(:name_animal as varchar) is null or a.name = :name_animal) " +
                "                and ( cast(:name_veterinary as varchar) " +
                "                is null or c.name_veterinary  = :name_veterinary) " +
                "                and ( cast(:medication as varchar) " +
                "                is null or p.medication  = :medication) ";

        return namedParameterJdbcTemplate.query(sql, mapSqlParameterSource,
                (rs, rowNum) ->
                        new ReportPrescription(
                                rs.getString("name_guardian"),
                                rs.getString("cpf"),
                                rs.getLong("telephone"),
                                rs.getString("name_animal"),
                                rs.getString("name_veterinary"),
                                rs.getString("cause"),
                                rs.getString("observations"),
                                rs.getString("medication"),
                                rs.getDate("date").toLocalDate()
                        ));
    }
}
