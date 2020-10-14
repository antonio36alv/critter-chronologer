package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet findById(Long id) {
        // TODO create not found exception
        Optional<Pet> petOptional = Optional.ofNullable(petRepository.findById(id).orElseThrow(PetNotFoundException::new));
        return petOptional.get();
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

}
