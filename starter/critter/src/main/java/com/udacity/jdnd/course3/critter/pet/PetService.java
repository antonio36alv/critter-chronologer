package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

public interface PetService {

    PetDTO findById(long id);

    List<PetDTO> findAll();

    List<PetDTO> findAllByCustomerId(long customerId);

    PetDTO save(PetDTO petDTO);
}
