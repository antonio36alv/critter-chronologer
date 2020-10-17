package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO save(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> findAll();

    List<ScheduleDTO> findAllByPetsId(long petId);

    List<ScheduleDTO> findAllByEmployeesId(long employeeId);

    List<ScheduleDTO> findAllByCustomerId(long customerId);
    
}
