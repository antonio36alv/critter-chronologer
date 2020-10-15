package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class UserTransformerUtility {

    public Customer customerDTOtoCustomerEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    public CustomerDTO customerEntityToCustomerDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        // to set dto petIds I need a list of Long
        // we will do this if there has been any pets set
        return dto;
    }

    public List<CustomerDTO> convertToCustomerDTOList(List<Customer> customerList) {
        List<CustomerDTO> dtoList = new ArrayList<>();
        for(Customer customer : customerList) {
            dtoList.add(customerEntityToCustomerDTO(customer));
        }
        return dtoList;
    }

    public Employee employeeDTOtoEmployeeEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }

    public EmployeeDTO employeeEntityToEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    public List<EmployeeDTO> convertToEmployeeDTOList(List<Employee> employeeList) {
        List<EmployeeDTO> dtoList = new ArrayList<>();
        for(Employee employee : employeeList) {
            dtoList.add(employeeEntityToEmployeeDTO(employee));
        }
        return dtoList;
    }
}
