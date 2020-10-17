package com.udacity.jdnd.course3.critter.schedule;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // when turning back into dto, go through employees and set ids in dto
        // same goes for pets
        List<Long> employeeIds;
        if(schedule.getEmployees() != null) {
            employeeIds = schedule.getEmployees()
                                            .stream()
                                            .map(Employee::getId)
                                            .collect(Collectors.toList());
        } else {
            employeeIds = new ArrayList<>();
        }
        List<Long> petIds;
        if(schedule.getPets() != null) {
             petIds = schedule.getPets()
                    .stream()
                    .map(Pet::getId)
                    .collect(Collectors.toList());
        } else {
            petIds = new ArrayList<>();
        }
        dto.setEmployeeIds(employeeIds);
        dto.setPetIds(petIds);
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
