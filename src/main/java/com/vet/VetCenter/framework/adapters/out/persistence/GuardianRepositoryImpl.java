package com.vet.VetCenter.framework.adapters.out.persistence;

import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.GuardianFilter;
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
public class GuardianRepositoryImpl implements GuardianRepository {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void save(Guardian guardian) {
        namedParameterJdbcTemplate.update("insert into guardian (name, cpf, telephone) " +
                "values (:name, :cpf, :telephone)", new BeanPropertySqlParameterSource(guardian));
    }

    @Override
    public List<Guardian> findAll(GuardianFilter filter) {
        Map<String, Object> mapSqlParameterSource = new HashMap<>();
        mapSqlParameterSource.put("name", filter.getName());
        mapSqlParameterSource.put("telephone", filter.getTelephone());
        return namedParameterJdbcTemplate.query("select * from " +
                        "guardian where ( cast(:name as varchar) is null or name = :name) and " +
                        "( cast(:telephone as numeric) is null or telephone = :telephone)", mapSqlParameterSource,
                ((rs, rowNum) ->
                        new Guardian(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("cpf"),
                                rs.getLong("telephone")
                        )));
    }

    @Override
    public Optional<Guardian> findById(Long id) {
        return namedParameterJdbcTemplate.query("select * from guardian where id = :id",
                        new MapSqlParameterSource("id", id),
                        ((rs, rowNum) -> new Guardian(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("cpf"),
                                rs.getLong("telephone")
                        ))).stream()
                .findFirst();
    }

    @Override
    public void update(Guardian guardian) {
        namedParameterJdbcTemplate.update("update guardian set name = :name, cpf = :cpf, telephone = :telephone where id = :id"
                , new BeanPropertySqlParameterSource(guardian));
    }

    @Override
    public void deleteById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        namedParameterJdbcTemplate.update("delete from guardian where id = :id"
                , mapSqlParameterSource);
    }
}
