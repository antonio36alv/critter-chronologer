package com.udacity.jdnd.course3.critter.user;

public interface EmployeeService {

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(long employeeId);

}
