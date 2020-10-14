package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {

    Pet findById(Long id) throws PetNotFoundException;

    List<Pet> findAll();

    Pet save(Pet pet);
}
