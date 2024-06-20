package com.petstore.dryadd.dto;

import lombok.Data;

@Data
public class PetResponseDto {

    private Long id;
    private String name;
    private String createdAt;

}
