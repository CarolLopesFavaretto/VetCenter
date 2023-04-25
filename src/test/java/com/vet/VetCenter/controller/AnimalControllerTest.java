package com.vet.VetCenter.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.VetCenter.application.ports.in.AnimalService;
import com.vet.VetCenter.application.ports.in.GuardianService;
import com.vet.VetCenter.config.PostgreSQLContainerTest;
import com.vet.VetCenter.data.VetCenterData;
import com.vet.VetCenter.domain.entity.Animal;
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
public class AnimalControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private AnimalService service;

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Before
    public void init() {
        guardianService.create(VetCenterData.getGuardian());
    }

    @Test
    public void shouldCreatedAnimal() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders.post("/animal")
                        .content(objectMapper.writeValueAsString(animal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindAnimalById() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders
                        .get("/animal/{id}", animal.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Animal resp = objectMapper.readValue(contentAsString, Animal.class);
                    assertThat(resp.getName()).isEqualTo(animal.getName());
                    assertThat(resp.getAge()).isEqualTo(animal.getAge());
                    assertThat(resp.getRace()).isEqualTo(animal.getRace());
                    assertThat(resp.getType()).isEqualTo(animal.getType());
                    assertThat(resp.getGuardianId()).isEqualTo(animal.getGuardianId());
                });
    }

    @Test
    public void shouldFindAllAnimal() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);
        animal.setName("Marie");
        service.create(animal);
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders
                        .get("/animal")
                        .param("name", "Marie")
                        .param("guardianId", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    List<Animal> resp = objectMapper.readValue(contentAsString, new TypeReference<List<Animal>>() {
                    });
                    assertThat(resp.size()).isEqualTo(2);

                    for (Animal item : resp) {
                        assertThat(item.getName()).isEqualTo(animal.getName());
                        assertThat(item.getAge()).isEqualTo(animal.getAge());
                        assertThat(item.getRace()).isEqualTo(animal.getRace());
                        assertThat(item.getType()).isEqualTo(animal.getType());
                        assertThat(item.getGuardianId()).isEqualTo(animal.getGuardianId());
                    }
                });

    }

    @Test
    public void shouldUpdateAnimal() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders.put("/animal/{id}", animal.getId().toString())
                        .content(objectMapper.writeValueAsString(animal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldFindAnimalByIdNotFound() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders
                        .get("/animal/{id}", 5)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteAnimal() throws Exception {
        Animal animal = VetCenterData.getAnimal();
        service.create(animal);

        mvc.perform(MockMvcRequestBuilders.delete("/animal/{id}", animal.getId().toString())
                        .content(objectMapper.writeValueAsString(animal))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
