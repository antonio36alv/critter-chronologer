package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserTransformerUtility userTransformerUtility;

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.save(userTransformerUtility.employeeDTOtoEmployeeEntity(employeeDTO));
        return userTransformerUtility.employeeEntityToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO findById(long employeeId) {
        return userTransformerUtility.employeeEntityToEmployeeDTO(findEntityById(employeeId));
    }

    public Employee findEntityById(long employeeId) {
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.findById(employeeId)).orElseThrow(CustomerNotFoundException::new);
        return employeeOptional.get();
    }

}
