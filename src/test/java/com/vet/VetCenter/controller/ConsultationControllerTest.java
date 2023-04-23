package com.vet.VetCenter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Consultation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ConsultationControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private ConsultationService service;

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
    }

    @Test
    public void shouldCreatedConsultation() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders.post("/consultation")
                        .content(objectMapper.writeValueAsString(consultation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindConsultationById() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders
                        .get("/consultation/{id}", consultation.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Consultation resp = objectMapper.readValue(contentAsString, Consultation.class);
                    assertThat(resp.getNameVeterinary()).isEqualTo(consultation.getNameVeterinary());
                    assertThat(resp.getCause()).isEqualTo(consultation.getCause());
                    assertThat(resp.getValue()).isEqualTo(consultation.getValue());
                    assertThat(resp.getObservations()).isEqualTo(consultation.getObservations());
                    assertThat(resp.getDate()).isEqualTo(consultation.getDate());
                    assertThat(resp.getRegress()).isEqualTo(consultation.getRegress());
                    assertThat(resp.getAnimalId()).isEqualTo(consultation.getAnimalId());
                });
    }

    @Test
    public void shouldFindAllConsultation() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders
                        .get("/consultation")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateConsultation() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders.put("/consultation/{id}", consultation.getId().toString())
                        .content(objectMapper.writeValueAsString(consultation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindConsultationByIdNotFound() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders
                        .get("/consultation/{id}", 5)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteConsultation() throws Exception {
        Consultation consultation = VetCenterData.getConsultation();
        service.create(consultation);

        mvc.perform(MockMvcRequestBuilders.delete("/consultation/{id}", consultation.getId().toString())
                        .content(objectMapper.writeValueAsString(consultation))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
