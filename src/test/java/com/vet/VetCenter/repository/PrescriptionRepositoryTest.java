package com.vet.VetCenter.repository;

import com.vet.VetCenter.application.ports.out.AnimalRepository;
import com.vet.VetCenter.application.ports.out.ConsultationRepository;
import com.vet.VetCenter.application.ports.out.GuardianRepository;
import com.vet.VetCenter.application.ports.out.PrescriptionRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Prescription;
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
public class PrescriptionRepositoryTest extends PostgreSQLContainerTest {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PrescriptionRepository repository;

    @Before
    public void init() {
        guardianRepository.save(VetCenterData.getGuardian());
        animalRepository.save(VetCenterData.getAnimal());
        consultationRepository.save(VetCenterData.getConsultation());
    }

    @Test
    public void runTestPrescriptionCrud() {

        Prescription prescription = VetCenterData.getPrescription();
        repository.save(prescription);

//      buscando registros inseridos

        List<Prescription> prescriptions = repository.findAll();
        Assert.assertEquals(1, prescriptions.size());
        Prescription prescriptionDb = prescriptions.get(0);
        Assert.assertEquals(prescriptionDb.getMedication(), prescription.getMedication());
        Assert.assertEquals(prescriptionDb.getDate(), prescription.getDate());
        Assert.assertEquals(prescriptionDb.getConsultationId(), prescription.getConsultationId());

//      buscando por id

        Optional<Prescription> prescriptionOpt = repository.findById(prescription.getId());
        Assert.assertEquals(prescriptionOpt.isPresent(), true);
        prescriptionDb = prescriptions.get(0);
        Assert.assertEquals(prescriptionDb.getMedication(), prescription.getMedication());
        Assert.assertEquals(prescriptionDb.getDate(), prescription.getDate());
        Assert.assertEquals(prescriptionDb.getConsultationId(), prescription.getConsultationId());

//      atualizando age e validando

        prescription.setMedication("Tramal");
        repository.update(prescription);
        prescriptionOpt = repository.findById(prescription.getId());
        Assert.assertEquals(prescriptionOpt.isPresent(), true);
        prescriptionDb = prescriptionOpt.get();
        Assert.assertEquals(prescriptionDb.getMedication(), prescription.getMedication());
        Assert.assertEquals(prescriptionDb.getDate(), prescription.getDate());
        Assert.assertEquals(prescriptionDb.getConsultationId(), prescription.getConsultationId());

//      deletando e validando

        repository.deleteById(prescriptionDb.getId());
        prescriptionOpt = repository.findById(prescription.getId());
        Assert.assertEquals(prescriptionOpt.isPresent(), false);
    }

}
