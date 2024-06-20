package com.petstore.dryadd.integeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petstore.dryadd.domain.Pet;
import com.petstore.dryadd.dto.PetRequestDto;
import com.petstore.dryadd.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        petRepository.deleteAll();
    }

    @Test
    void testCreatePet() throws Exception {
        PetRequestDto petRequestDto = new PetRequestDto();
        petRequestDto.setName("Buddy");

        mockMvc.perform(post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testGetPet() throws Exception {
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setCreatedAt(LocalDateTime.now());
        pet = petRepository.save(pet);

        mockMvc.perform(get("/pets/" + pet.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testGetAllPets() throws Exception {
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setCreatedAt(LocalDateTime.now());
        petRepository.saveAll(Collections.singletonList(pet));

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Buddy"));
    }

    @Test
    void testUpdatePet() throws Exception {
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setCreatedAt(LocalDateTime.now());
        pet = petRepository.save(pet);

        PetRequestDto updatedPetRequestDto = new PetRequestDto();
        updatedPetRequestDto.setName("BuddyUpdated");

        mockMvc.perform(put("/pets/" + pet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPetRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("BuddyUpdated"));
    }

    @Test
    void testDeletePet() throws Exception {
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setCreatedAt(LocalDateTime.now());
        pet = petRepository.save(pet);

        mockMvc.perform(delete("/pets/" + pet.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/pets/" + pet.getId()))
                .andExpect(status().isNotFound());
    }

}