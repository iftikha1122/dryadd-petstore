package com.petstore.dryadd.services;

import com.petstore.dryadd.dto.PetRequestDto;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetRequestDtoValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {

        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory();) {
            validator = factory.getValidator();
        }catch (ValidationException ignored){

        }
    }

    @Test
    void testValidPetRequestDto() {
        PetRequestDto petRequestDto = new PetRequestDto();
        petRequestDto.setName("Buddy");

        Set<ConstraintViolation<PetRequestDto>> violations = validator.validate(petRequestDto);

        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidPetRequestDto() {
        PetRequestDto petRequestDto = new PetRequestDto();
        petRequestDto.setName("");

        Set<ConstraintViolation<PetRequestDto>> violations = validator.validate(petRequestDto);

        assertEquals(1, violations.size());
        assertEquals("Name is mandatory", violations.iterator().next().getMessage());
    }
}
