package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeServiceImpl;
import com.udacity.jdnd.course3.critter.user.UserTransformerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleTransformerUtility scheduleTransformer;
    @Autowired
    private UserTransformerUtility userTransformer;

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleTransformer.scheduleDTOtoScheduleEntity(scheduleDTO);
        // go through getEmployeeIds
        // for each we will id we will set an employee using employee service
        scheduleDTO.getEmployeeIds()
                            .forEach(employeeId -> schedule
                                                    .getEmployees()
                                                    .add(employeeService.findEntityById(employeeId)));
        return scheduleTransformer.scheduleEntityToScheduleDTO(scheduleRepository.save(schedule));
    }

    public List<ScheduleDTO> findAll() {
        return scheduleTransformer.convertToScheduleDTOList(scheduleRepository.findAll());
    }

}
