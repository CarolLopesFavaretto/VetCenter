package com.vet.VetCenter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.framework.adapters.in.dtos.dto.ReportConsultations;
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
public class ReportConsultationsControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private AnimalService animalService;

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;


    @Before
    public void init() {
        guardianService.create(VetCenterData.getGuardian());
        animalService.create(VetCenterData.getAnimal());
        consultationService.create(VetCenterData.getConsultation());
    }

    @Test
    public void shouldFindAllReportsConsultations() throws Exception {
        ReportConsultations reportConsultations = VetCenterData.getReport();

        mvc.perform(MockMvcRequestBuilders
                        .get("/report/consultations")
                        .param("nameGuardian", "Caroline")
                        .param("nameAnimal", "Maria")
                        .param("nameVeterinary", "Maria")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<ReportConsultations> resp = objectMapper.readValue(contentAsString, new TypeReference<List<ReportConsultations>>() {
                    });
                    assertThat(resp.size()).isEqualTo(1);
                    for (ReportConsultations consultations : resp) {

                        assertThat(consultations.getNameAnimal()).isEqualTo(reportConsultations.getNameAnimal());
                        assertThat(consultations.getDate()).isEqualTo(reportConsultations.getDate());
                        assertThat(consultations.getNameVeterinary()).isEqualTo(reportConsultations.getNameVeterinary());
                    }
                });
    }
}
