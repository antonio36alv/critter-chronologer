package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO save(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> findAll();

}
