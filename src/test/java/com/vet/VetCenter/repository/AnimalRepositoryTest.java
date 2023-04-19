package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Animal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class AnimalRepositoryTest extends PostgreSQLContainerTest {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private AnimalRepository repository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Before
    public void init() {
        guardianRepository.save(VetCenterData.getGuardian());
    }

    @Test
    public void runTestAnimalCrud() {

//      testando inserção no banco

        Animal animal = VetCenterData.getAnimal();
        repository.save(animal);

//      buscando registros inseridos

        List<Animal> animalList = repository.findAll();
        Assert.assertEquals(1, animalList.size());
        Animal animalDb = animalList.get(0);
        Assert.assertEquals(animalDb.getName(), animal.getName());
        Assert.assertEquals(animalDb.getRace(), animal.getRace());
        Assert.assertEquals(animalDb.getAge(), animal.getAge());
        Assert.assertEquals(animalDb.getType(), animal.getType());
        Assert.assertEquals(animalDb.getGuardianId(), animal.getGuardianId());

//      buscando por id

        Optional<Animal> animalOpt = repository.findById(animal.getId());
        Assert.assertEquals(animalOpt.isPresent(), true);
        animalDb = animalOpt.get();
        Assert.assertEquals(animalDb.getName(), animal.getName());
        Assert.assertEquals(animalDb.getRace(), animal.getRace());
        Assert.assertEquals(animalDb.getAge(), animal.getAge());
        Assert.assertEquals(animalDb.getType(), animal.getType());
        Assert.assertEquals(animalDb.getGuardianId(), animal.getGuardianId());

//      atualizando age e validando

        animal.setAge(12);
        repository.update(animal);
        animalOpt = repository.findById(animal.getId());
        Assert.assertEquals(animalOpt.isPresent(), true);
        animalDb = animalOpt.get();
        Assert.assertEquals(animalDb.getAge(), animal.getAge());

//        deletando e validando

        repository.deleteById(animalDb.getId());
        animalOpt = repository.findById(animal.getId());
        Assert.assertEquals(animalOpt.isPresent(), false);
    }
}
