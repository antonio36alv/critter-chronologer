package com.udacity.jdnd.course3.critter.user;

import java.util.List;

public interface CustomerService {

    Customer findById(Long id);

    List<Customer> findAll();

    Customer save(Customer customer);

}
