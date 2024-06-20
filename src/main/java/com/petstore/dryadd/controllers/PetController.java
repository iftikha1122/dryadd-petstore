package com.petstore.dryadd.controllers;

import com.petstore.dryadd.domain.Pet;
import com.petstore.dryadd.dto.PetRequestDto;
import com.petstore.dryadd.dto.PetResponseDto;
import com.petstore.dryadd.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    @Autowired
    private final PetService petService;

    @PostMapping
    public ResponseEntity<PetResponseDto> createPet(@Valid @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto createdPet = petService.createPet(petRequestDto);
        return ResponseEntity.ok(createdPet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDto> getPet(@PathVariable Long id) {
        return petService.getPet(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PetResponseDto> getAllPets() {
        return petService.getAllPets();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponseDto> updatePet(@PathVariable Long id, @Valid @RequestBody PetRequestDto petRequestDto) {
        PetResponseDto updatedPet = petService.updatePet(id, petRequestDto);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}