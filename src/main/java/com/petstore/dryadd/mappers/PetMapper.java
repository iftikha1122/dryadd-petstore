package com.petstore.dryadd.mappers;

import com.petstore.dryadd.domain.Pet;
import com.petstore.dryadd.dto.PetRequestDto;
import com.petstore.dryadd.dto.PetResponseDto;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
@Component
public class PetMapper {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Pet toEntity(PetRequestDto dto) {
        Pet pet = new Pet();
        pet.setName(dto.getName());
        return pet;
    }

    public PetResponseDto toDto(Pet pet) {
        PetResponseDto dto = new PetResponseDto();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setCreatedAt((pet.getCreatedAt().format(FORMATTER)));
        return dto;
    }
}
