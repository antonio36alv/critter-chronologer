package com.udacity.jdnd.course3.critter.user;

public interface CustomerService {

    Customer findById(Long id);

    Customer save(Customer customer);

}
