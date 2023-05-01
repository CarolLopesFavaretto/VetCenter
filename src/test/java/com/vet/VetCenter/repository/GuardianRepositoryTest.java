package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Guardian;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.GuardianFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class GuardianRepositoryTest extends PostgreSQLContainerTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private GuardianRepository repository;

    @Test
    public void runTestGuardianCrud() {

//      Inserção de dados

        Guardian guardian = VetCenterData.getGuardian();
        repository.save(guardian);

//      Buscando registros inseridos

        List<Guardian> guardianList = repository.findAll(new GuardianFilter());
        Assert.assertEquals(1, guardianList.size());
        Guardian guardianDb = guardianList.get(0);
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());

//      Buscando por id

        Optional<Guardian> guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), true);
        guardianDb = guardianOpt.get();
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());


//      Atualização de telephone e validação

        guardian.setName("Lucas");
        guardian.setCpf("400.587.369-08");
        guardian.setTelephone(11947759525L);
        repository.update(guardian);
        guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), true);
        guardianDb = guardianOpt.get();
        Assert.assertEquals(guardianDb.getTelephone(), guardian.getTelephone());
        Assert.assertEquals(guardianDb.getName(), guardian.getName());
        Assert.assertEquals(guardianDb.getCpf(), guardian.getCpf());

//      Deletando e validando

        repository.deleteById(guardianDb.getId());
        guardianOpt = repository.findById(guardian.getId());
        Assert.assertEquals(guardianOpt.isPresent(), false);

    }


}
