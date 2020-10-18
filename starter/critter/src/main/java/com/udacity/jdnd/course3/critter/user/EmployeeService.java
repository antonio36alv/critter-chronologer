package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

    EmployeeDTO save(EmployeeDTO employeeDTO);

    EmployeeDTO findById(long employeeId);

    Employee findEntityById(long employeeId);

    void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId);

    List<EmployeeDTO> findAllBySkillsIn(EmployeeRequestDTO employeeDTO);
    
}
