package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
        return customer.get();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
