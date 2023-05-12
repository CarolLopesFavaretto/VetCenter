package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.application.ports.out.ReportConsultationsRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Prescription;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportConsultationsFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class ReportConsultationsRepositoryTest extends PostgreSQLContainerTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private ReportConsultationsRepository repository;

    @Before
    public void init() {
        guardianRepository.save(VetCenterData.getGuardian());
        animalRepository.save(VetCenterData.getAnimal());
        consultationRepository.save(VetCenterData.getConsultation());
    }

    @Test
    public void runTestFindAllReportsConsultations() {

        ReportConsultations reportConsultations = VetCenterData.getReport();

        List<ReportConsultations> reportConsultationsList = repository.findAll(new ReportConsultationsFilter());
        Assert.assertEquals(1, reportConsultationsList.size());
        ReportConsultations  reportDb = reportConsultationsList.get(0);
        Assert.assertEquals(reportDb.getNameGuardian(), reportConsultations.getNameGuardian());
        Assert.assertEquals(reportDb.getDate(), reportConsultations.getDate());
        Assert.assertEquals(reportDb.getNameAnimal(), reportConsultations.getNameAnimal());
        Assert.assertEquals(reportDb.getNameVeterinary(), reportConsultations.getNameVeterinary());

    }
}
