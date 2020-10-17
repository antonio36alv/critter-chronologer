package com.udacity.jdnd.course3.critter.user;

import java.util.List;

public interface CustomerService {

    CustomerDTO findById(Long id);

    Customer findEntityById(Long id);

    List<CustomerDTO> findAll();

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO findByPetsId(long petId);

}
