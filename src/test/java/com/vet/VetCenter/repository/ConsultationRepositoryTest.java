package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Consultation;
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
public class ConsultationRepositoryTest extends PostgreSQLContainerTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ConsultationRepository repository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Before
    public void init() {
        guardianRepository.save(VetCenterData.getGuardian());
        animalRepository.save(VetCenterData.getAnimal());
    }

    @Test
    public void runTestConsultationCrud() {
        Consultation consultation = VetCenterData.getConsultation();
        repository.save(consultation);

//      buscando registros inseridos

        List<Consultation> consultationList = repository.findAll();
        Assert.assertEquals(1, consultationList.size());
        Consultation consultationDb = consultationList.get(0);
        Assert.assertEquals(consultationDb.getNameVeterinary(), consultation.getNameVeterinary());
        Assert.assertEquals(consultationDb.getDate(), consultation.getDate());
        Assert.assertEquals(consultationDb.getCause(), consultation.getCause());
        Assert.assertEquals(consultationDb.getObservations(), consultation.getObservations());
        Assert.assertEquals(consultationDb.getValue(), consultation.getValue());
        Assert.assertEquals(consultationDb.getRegress(), consultation.getRegress());
        Assert.assertEquals(consultationDb.getAnimalId(), consultation.getAnimalId());

//      buscando por id

        Optional<Consultation> consultationOpt = repository.findById(consultation.getId());
        Assert.assertEquals(consultationOpt.isPresent(), true);
        consultationDb = consultationOpt.get();
        Assert.assertEquals(consultationDb.getNameVeterinary(), consultation.getNameVeterinary());
        Assert.assertEquals(consultationDb.getDate(), consultation.getDate());
        Assert.assertEquals(consultationDb.getCause(), consultation.getCause());
        Assert.assertEquals(consultationDb.getObservations(), consultation.getObservations());
        Assert.assertEquals(consultationDb.getValue(), consultation.getValue());
        Assert.assertEquals(consultationDb.getRegress(), consultation.getRegress());
        Assert.assertEquals(consultationDb.getAnimalId(), consultation.getAnimalId());

//      atualizando age e validando

        consultation.setValue(50.00);
        consultation.setRegress(false);
        repository.update(consultation);
        consultationOpt = repository.findById(consultation.getId());
        Assert.assertEquals(consultationOpt.isPresent(), true);
        consultationDb = consultationOpt.get();
        Assert.assertEquals(consultationDb.getRegress(), consultation.getRegress());

//      deletando e validando

        repository.deleteById(consultationDb.getId());
        consultationOpt = repository.findById(consultation.getId());
        Assert.assertEquals(consultationOpt.isPresent(), false);
    }
}
