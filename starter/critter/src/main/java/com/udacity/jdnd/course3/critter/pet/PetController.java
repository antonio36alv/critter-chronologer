package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.CustomerServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        // save the pet using pet service
        // convert the returned pet into dto for response
        return convertPet(petService.save(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return convertPet(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }

    private Pet convertDTO(PetDTO dto) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto, pet);
        // use owner id from dto to find customer, then set that exact customer
        pet.setCustomer(customerService.findById(dto.getOwnerId()));
        return pet;
    }

    private PetDTO convertPet(Pet pet) {
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet, dto);
        dto.setOwnerId(pet.getCustomer().getId());
        return dto;
    }
}
