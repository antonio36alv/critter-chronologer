package com.udacity.jdnd.course3.critter.pet;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class PetTransformerUtility {

    /**
     Pet DTO -> Pet Entity
     */
    private Pet convertDTO(PetDTO dto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto, pet);
        // use owner id from dto to find customer, then set customer id
        // with that owner id
        pet.setCustomer(customerService.findById(dto.getOwnerId()));
        return pet;
    }
    /**
     Pet Entity -> Pet DTO
     */
    private PetDTO convertPet(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);
        // use customer id from pet to find customer, then set owner id
        // with that customer id
        dto.setOwnerId(pet.getCustomer().getId());
        return dto;
    }
    /**
     Takes a list of pets and converts it to a list of petDTOs
     with the help of the convertPet method above
     */
    public List<PetDTO> convertToDTOList(List<Pet> petList) {
        List<PetDTO> petDTOList = new ArrayList<>();
        for(Pet pet : petList) {
            petDTOList.add(convertPet(pet));
        }
        return petDTOList;
    }

}
