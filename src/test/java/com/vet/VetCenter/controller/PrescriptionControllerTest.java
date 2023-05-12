package com.vet.VetCenter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.in.ConsultationService;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.application.ports.in.PrescriptionService;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Prescription;
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
public class PrescriptionControllerTest extends PostgreSQLContainerTest {

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

    @Autowired
    private PrescriptionService service;


    @Before
    void init() {
        guardianService.create(VetCenterData.getGuardian());
        animalService.create(VetCenterData.getAnimal());
        consultationService.create(VetCenterData.getConsultation());
    }

    @Test
    public void shouldCreatedPrescription() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders.post("/prescription")
                        .content(objectMapper.writeValueAsString(prescription))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindPrescriptionById() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders
                        .get("/prescription/{id}", prescription.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Prescription resp = objectMapper.readValue(contentAsString, Prescription.class);
                    assertThat(resp.getMedication()).isEqualTo(prescription.getMedication());
                    assertThat(resp.getDate()).isEqualTo(prescription.getDate());
                    assertThat(resp.getConsultationId()).isEqualTo(prescription.getConsultationId());
                });
    }

    @Test
    public void shouldFindAllPrescription() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);
        prescription.setMedication("Prednisolona");
        prescription.setConsultationId(2L);
        service.create(prescription);
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders
                        .get("/prescription")
                        .param("medication", "Prednisolona")
                        .param("consultationId", String.valueOf(2L))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<Prescription> resp = objectMapper.readValue(contentAsString, new TypeReference<List<Prescription>>() {
                    });
                    assertThat(resp.size()).isEqualTo(2);
                    for (Prescription prescriptionList : resp) {

                        assertThat(prescriptionList.getMedication()).isEqualTo(prescription.getMedication());
                        assertThat(prescriptionList.getDate()).isEqualTo(prescription.getDate());
                        assertThat(prescriptionList.getConsultationId()).isEqualTo(prescription.getConsultationId());
                    }
                });
    }

    @Test
    public void shouldUpdatePrescription() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders.put("/prescription/{id}", 3)
                        .content(objectMapper.writeValueAsString(prescription))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldFindPrescriptionByIdNotFound() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders
                        .get("/prescription/{id}", 7)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeletePrescription() throws Exception {
        Prescription prescription = VetCenterData.getPrescription();
        service.create(prescription);

        mvc.perform(MockMvcRequestBuilders.delete("/prescription/{id}", prescription.getId().toString())
                        .content(objectMapper.writeValueAsString(prescription))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
