package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.UserTransformerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetTransformerUtility petTransformer;
    @Autowired
    private UserTransformerUtility userTransformer;

    @Override
    public PetDTO findById(long id) {
        Optional<Pet> petOptional = Optional.ofNullable(petRepository.findById(id).orElseThrow(PetNotFoundException::new));
        return petTransformer.petEntityToPetDTO(petOptional.get());
    }

    @Override
    public List<PetDTO> findAll() {
//        return petRepository.findAll();
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
        // and set the pets customer field to the returned customer
//        pet.setCustomer(customerService.findById(petDTO.getOwnerId()));
//        Customer customer = customerService.findById(petDTO.getOwnerId());
        Customer customer = userTransformer.customerDTOtoCustomerEntity(customerService.findById(petDTO.getOwnerId()));
//        pet.setCustomer(customerRepository.findById(petDTO.getOwnerId()));
        pet.setCustomer(customer);
        // save our pet entity then
        // convert back to dto for response
        return petTransformer.petEntityToPetDTO(petRepository.save(pet));
    }

}
