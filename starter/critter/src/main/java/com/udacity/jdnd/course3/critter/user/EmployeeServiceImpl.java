package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
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

    @Override
    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = findEntityById(employeeId);
        employee.setAvailability(daysAvailable);
    }

    @Override
    public List<EmployeeDTO> findAllBySkillsIn(EmployeeRequestDTO employeeDTO) {

        List<Employee> employeeList = employeeRepository
                                        .findAllByAvailability(employeeDTO.getDate().getDayOfWeek());

        List<Employee> coolEmployees = employeeList
                        .stream()
                        .filter(employee -> employee.getSkills() != null &&
                                            employee.getSkills()
                                                .containsAll(employeeDTO.getSkills()))
                                                .collect(Collectors.toList());

        return userTransformerUtility.convertToEmployeeDTOList(coolEmployees);
    }

}
