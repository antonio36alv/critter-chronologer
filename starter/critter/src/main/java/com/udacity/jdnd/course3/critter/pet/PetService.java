package com.udacity.jdnd.course3.critter.pet;

public interface PetService {

    Pet findById(Long id) throws PetNotFoundException;

    Pet save(Pet pet);
}
