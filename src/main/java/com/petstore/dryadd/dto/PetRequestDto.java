package com.petstore.dryadd.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PetRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
