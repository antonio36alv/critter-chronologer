package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class UserTransformerUtility {

    private Customer convertCustomerDTO(CustomerDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        return customer;
    }

    private CustomerDTO convertCustomer(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        // to set dto petIds I need a list of Long
        // we will do this if there has been any pets set
        return dto;
    }

    private List<CustomerDTO> convertToCustomerDTOList(List<Customer> customerList) {
        List<CustomerDTO> dtoList = new ArrayList<>();
        for(Customer customer : customerList) {
            dtoList.add(convertCustomer(customer));
        }
        return dtoList;
    }

    private Employee convertEmployeeDTO(EmployeeDTO dto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }

    private EmployeeDTO convertEmployee(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    private List<EmployeeDTO> convertToEmployeeList(List<Employee> employeeList) {
        List<EmployeeDTO> dtoList = new ArrayList<>();
        for(Employee employee : employeeList) {
            dtoList.add(convertEmployee(employee));
        }
        return dtoList;
    }
}
