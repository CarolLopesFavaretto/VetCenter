package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.*;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import com.vet.VetCenter.framework.adapters.in.dtos.filter.ReportPrescriptionFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class ReportPrescriptionsRepositoryTest extends PostgreSQLContainerTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ReportPrescriptionRepository repository;

    @Before
    public void init() {
        guardianRepository.save(VetCenterData.getGuardian());
        animalRepository.save(VetCenterData.getAnimal());
        consultationRepository.save(VetCenterData.getConsultation());
        prescriptionRepository.save(VetCenterData.getPrescription());
    }

    @Test
    public void runTestFindAllReportsPrescriptions() {

        ReportPrescription reportPrescription = VetCenterData.getReportPrescriptions();

        List<ReportPrescription> prescriptions = repository.findAll(new ReportPrescriptionFilter());
        Assert.assertEquals(1, prescriptions.size());
        ReportPrescription reportDb = prescriptions.get(0);
        Assert.assertEquals(reportDb.getNameGuardian(), reportPrescription.getNameGuardian());
        Assert.assertEquals(reportDb.getDate(), reportPrescription.getDate());
        Assert.assertEquals(reportDb.getNameAnimal(), reportPrescription.getNameAnimal());
        Assert.assertEquals(reportDb.getNameVeterinary(), reportPrescription.getNameVeterinary());
        Assert.assertEquals(reportDb.getMedication(), reportPrescription.getMedication());
    }
}
