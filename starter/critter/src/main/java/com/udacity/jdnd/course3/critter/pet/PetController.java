package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetServiceImpl petService;

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        // convert dto to a pet
        Pet pet = convertDTO(petDTO);
        // get the customer by the ownerId passed in through petDto
        Customer customer = customerService.findById(petDTO.getOwnerId());
        // set the pets customer as the pet found
        pet.setCustomer(customer);
//        pet.setCustomer(customerService.findById(petDTO.getOwnerId()));

        // save the pet using pet service
        // convert the returned pet into dto for response
        return convertPet(petService.save(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        // send pet id to findById method within petService
        // then convert the pet into a pet dto
        return convertPet(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return convertToDTOList(petService.findAll());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return convertToDTOList(petService.findAllByCustomerId(ownerId));
    }
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
