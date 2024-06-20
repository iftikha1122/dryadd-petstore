package com.petstore.dryadd.service;

import com.petstore.dryadd.domain.Pet;
import com.petstore.dryadd.dto.PetRequestDto;
import com.petstore.dryadd.dto.PetResponseDto;
import com.petstore.dryadd.mappers.PetMapper;
import com.petstore.dryadd.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    @Autowired
    private final PetRepository petRepository;

    @Autowired
    private final PetMapper petMapper;

    public PetResponseDto createPet(PetRequestDto petRequestDto) {
        Pet pet = petMapper.toEntity(petRequestDto);
        pet.setCreatedAt(java.time.LocalDateTime.now());
        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    public Optional<PetResponseDto> getPet(Long id) {
        return petRepository.findById(id).map(petMapper::toDto);
    }

    public List<PetResponseDto> getAllPets() {
        return petRepository.findAll().stream().map(petMapper::toDto).collect(Collectors.toList());
    }

    public PetResponseDto updatePet(Long id, PetRequestDto petRequestDto) {
        Pet pet = petRepository.findById(id).orElseThrow();
        pet.setName(petRequestDto.getName());
        Pet updatedPet = petRepository.save(pet);
        return petMapper.toDto(updatedPet);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}