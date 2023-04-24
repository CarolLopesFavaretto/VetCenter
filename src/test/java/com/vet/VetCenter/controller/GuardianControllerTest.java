package com.vet.VetCenter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Guardian;
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
public class GuardianControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private GuardianService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldCreatedGuardian() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders.post("/guardian")
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldFindGuardianById() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders
                        .get("/guardian/{id}", guardian.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Guardian resp = objectMapper.readValue(contentAsString, Guardian.class);
                    assertThat(resp.getName()).isEqualTo(guardian.getName());
                    assertThat(resp.getCpf()).isEqualTo(guardian.getCpf());
                    assertThat(resp.getTelephone()).isEqualTo(guardian.getTelephone());
                });
    }

    @Test
    public void shouldFindAllGuardian() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders
                        .get("/guardian")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<Guardian> resp = objectMapper.readValue(contentAsString, new TypeReference<List<Guardian>>() {
                    });
                    for (Guardian guardianList : resp) {

                        assertThat(resp.get(0));
                        assertThat(guardianList.getName()).isEqualTo(guardian.getName());
                        assertThat(guardianList.getCpf()).isEqualTo(guardian.getCpf());
                        assertThat(guardianList.getTelephone()).isEqualTo(guardian.getTelephone());
                    }
                });
    }

    @Test
    public void shouldUpdateGuardian() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders.put("/guardian/{id}", guardian.getId().toString())
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldFindGuardianByIdNotFound() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders
                        .get("/guardian/{id}", guardian.getId().toString())
                        .content(objectMapper.writeValueAsString(guardian))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteGuardian() throws Exception {
        Guardian guardian = VetCenterData.getGuardian();
        service.create(guardian);

        mvc.perform(MockMvcRequestBuilders.delete("/guardian/{id}", guardian.getId().toString())
                        .content(objectMapper.writeValueAsString(guardian))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
