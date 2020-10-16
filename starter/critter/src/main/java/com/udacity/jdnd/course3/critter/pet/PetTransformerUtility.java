package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
final class PetTransformerUtility {

    /**
     Pet DTO -> Pet Entity
     */
    public Pet petDTOtoPetEntity(PetDTO dto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto, pet);
        return pet;
    }
    /**
     Pet Entity -> Pet DTO
     */
    public PetDTO petEntityToPetDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);
        // use customer id from pet to find customer, then set owner id
        // with that customer id
        dto.setOwnerId(pet.getCustomer().getId());
        return dto;
    }
    /**
     *
     List of Pets -> List of Pet DTOs
     Takes a list of pets and converts it to a list of petDTOs
     with the help of the petEntityToPetDTO method above
     */
    public List<PetDTO> convertToPetDTOList(List<Pet> petList) {
        List<PetDTO> dtoList = new ArrayList<>();
        for(Pet pet : petList) {
            dtoList.add(petEntityToPetDTO(pet));
        }
        return dtoList;
    }

}
