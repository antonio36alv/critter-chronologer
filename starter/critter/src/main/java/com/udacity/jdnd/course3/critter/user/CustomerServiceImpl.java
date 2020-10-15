package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        // within customer we have a pet list
        // so we should
//        customer.getPet().forEach(pet -> pet.setCustomer(customer));
        return customerRepository.save(customer);
    }
}
