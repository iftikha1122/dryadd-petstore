package com.petstore.dryadd.repositories;

import com.petstore.dryadd.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long> {
}
