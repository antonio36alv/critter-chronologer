package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return convertCustomer(customerService.save(convertCustomerDTO(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerService.findAll();
        List<CustomerDTO> dtoList = convertToCustomerDTOList(customerList);

            for(int x = 0; x < dtoList.size(); x++) {
                List<Long> petIds = new ArrayList<>();
                for(Pet pet : customerList.get(x).getPets()) {
                    petIds.add(pet.getId());
                }
                dtoList.get(x).setPetIds(petIds);
            }

        return dtoList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

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
