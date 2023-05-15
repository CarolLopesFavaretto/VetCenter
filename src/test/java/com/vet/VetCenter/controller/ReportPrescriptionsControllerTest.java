package com.vet.VetCenter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.application.ports.out.PrescriptionRepository;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportPrescription;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ReportPrescriptionsControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    @Before
    public void init() {
        guardianService.create(VetCenterData.getGuardian());
        animalService.create(VetCenterData.getAnimal());
        consultationService.create(VetCenterData.getConsultation());
        prescriptionRepository.save(VetCenterData.getPrescription());
    }

    @Test
    public void shouldFindAllReportsConsultations() throws Exception {
        ReportPrescription reportPrescription = VetCenterData.getReportPrescriptions();

        mvc.perform(MockMvcRequestBuilders
                        .get("/report/prescriptions")
                        .param("medication", "Dipirona")
                        .param("nameAnimal", "Maria")
                        .param("nameVeterinary", "Maria")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<ReportPrescription> resp = objectMapper.readValue(contentAsString, new TypeReference<List<ReportPrescription>>() {
                    });
                    assertThat(resp.size()).isEqualTo(1);
                    for (ReportPrescription prescription : resp) {

                        assertThat(prescription.getNameAnimal()).isEqualTo(reportPrescription.getNameAnimal());
                        assertThat(prescription.getDate()).isEqualTo(reportPrescription.getDate());
                        assertThat(prescription.getNameVeterinary()).isEqualTo(reportPrescription.getNameVeterinary());
                        assertThat(prescription.getMedication()).isEqualTo(reportPrescription.getMedication());
                    }
                });
    }
}
