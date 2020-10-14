package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {

    Pet findById(long id);

    List<Pet> findAll();

    List<Pet> findAllByCustomerId(long customerId);

    Pet save(Pet pet);
}
