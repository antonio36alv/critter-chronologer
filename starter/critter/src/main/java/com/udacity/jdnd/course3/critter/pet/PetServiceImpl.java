package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetTransformerUtility petTransformer;

    @Override
    public PetDTO findById(long id) {
        Optional<Pet> petOptional = Optional.ofNullable(petRepository.findById(id).orElseThrow(PetNotFoundException::new));
        return petTransformer.petEntityToPetDTO(findEntityById(id));
    }

    public Pet findEntityById(long id) {
        Optional<Pet> petOptional = Optional.ofNullable(petRepository.findById(id).orElseThrow(PetNotFoundException::new));
        return petOptional.get();
    }

    @Override
    public List<PetDTO> findAll() {
        return petTransformer.convertToPetDTOList(petRepository.findAll());
    }

    @Override
    public List<PetDTO> findAllByCustomerId(long customerId) {
        return petTransformer.convertToPetDTOList(petRepository.findAllByCustomerId(customerId));
    }

    @Override
    public PetDTO save(PetDTO petDTO) {
        // convert dto into a pet
        Pet pet = petTransformer.petDTOtoPetEntity(petDTO);
        // find customer that the pet belongs to (using owner id from dto)
        Customer customer = customerService.findEntityById(petDTO.getOwnerId());
        // then set the pets customer field to the customer returned previously
        pet.setCustomer(customer);
        // save pet and store it as a new pet, refrenced by savedPet
        Pet savedPet = petRepository.save(pet);
        // if our customer.getPets is not null we simply add savedPet to existing pets
        // otherwise create a new petList, add pet to that list, set customers petList
        // to the new petList, this seems unnecessary but it's the only way the tests
        // pass
        if(customer.getPets() != null) {
            customer.getPets().add(savedPet);
        } else {
            List<Pet> pets = new ArrayList<>();
            pets.add(savedPet);
            customer.setPets(pets);
        }
        savedPet.setSchedule(new ArrayList<>());
        // convert saved pet entity to a petDTO and return
        return petTransformer.petEntityToPetDTO(savedPet);
    }

}
