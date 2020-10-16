package com.udacity.jdnd.course3.critter.schedule;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class ScheduleTransformerUtility {

    /**
    Schedule DTO -> Schedule Entity
     */
    public Schedule scheduleDTOtoScheduleEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(dto, schedule);
        return schedule;
    }

    /**
     Schedule Entity -> Schedule DTO
     */
    public ScheduleDTO scheduleEntityToScheduleDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, dto);
        return dto;
    }
    /**
     List of Schedules -> List of Schedule DTOs
     Takes a list of schedules and converts it to a list of scheduleDTOs
     with the help of the scheduleEntityToScheduleDTO method above
     */
    public List<ScheduleDTO> convertToScheduleDTOList(List<Schedule> scheduleList) {
        List<ScheduleDTO> dtoList = new ArrayList<>();
        for(Schedule schedule : scheduleList) {
            dtoList.add(scheduleEntityToScheduleDTO(schedule));
        }
        return dtoList;
    }

}
