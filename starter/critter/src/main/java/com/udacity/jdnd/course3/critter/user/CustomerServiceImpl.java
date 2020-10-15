package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserTransformerUtility userTransformer;

    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new));
        return userTransformer.customerEntityToCustomerDTO(customer.get());
    }

    @Override
    public List<CustomerDTO> findAll() {

        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> dtoList = userTransformer.convertToCustomerDTOList(customerList);

        for(int x = 0; x < dtoList.size(); x++) {
            List<Long> petIds = new ArrayList<>();
            for(Pet pet : customerList.get(x).getPets()) {
                petIds.add(pet.getId());
            }
            dtoList.get(x).setPetIds(petIds);
        }
        return dtoList;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = userTransformer.customerDTOtoCustomerEntity(customerDTO);
        // within customer we have a pet list
        // so we should
//        customer.getPet().forEach(pet -> pet.setCustomer(customer));
//        return customerRepository.save(customer);
        return userTransformer
                    .customerEntityToCustomerDTO(customerRepository.save(customer));
    }
}
