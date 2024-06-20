package com.petstore.dryadd.services;

import com.petstore.dryadd.domain.Pet;
import com.petstore.dryadd.dto.PetRequestDto;
import com.petstore.dryadd.dto.PetResponseDto;
import com.petstore.dryadd.mappers.PetMapper;
import com.petstore.dryadd.repositories.PetRepository;
import com.petstore.dryadd.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class PetServiceTest {
    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePet() {
        PetRequestDto petRequestDto = new PetRequestDto();
        petRequestDto.setName("Buddy");

        Pet pet = new Pet();
        pet.setName("Buddy");

        Pet savedPet = new Pet();
        savedPet.setName("Buddy");

        PetResponseDto petResponseDto = new PetResponseDto();
        petResponseDto.setName("Buddy");

        when(petMapper.toEntity(petRequestDto)).thenReturn(pet);
        when(petRepository.save(pet)).thenReturn(savedPet);
        when(petMapper.toDto(savedPet)).thenReturn(petResponseDto);

        PetResponseDto createdPet = petService.createPet(petRequestDto);

        assertEquals("Buddy", createdPet.getName());
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void testGetPet() {
        Pet pet = new Pet();
        pet.setName("Buddy");

        PetResponseDto petResponseDto = new PetResponseDto();
        petResponseDto.setName("Buddy");

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petMapper.toDto(pet)).thenReturn(petResponseDto);

        Optional<PetResponseDto> foundPet = petService.getPet(1L);

        assertTrue(foundPet.isPresent());
        assertEquals("Buddy", foundPet.get().getName());
        verify(petRepository, times(1)).findById(1L);
    }
}