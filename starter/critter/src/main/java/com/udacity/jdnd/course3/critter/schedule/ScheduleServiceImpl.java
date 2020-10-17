package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetServiceImpl;
import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private PetServiceImpl petService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleTransformerUtility scheduleTransformer;

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleTransformer.scheduleDTOtoScheduleEntity(scheduleDTO);
        // go through getEmployeeIds
        // for each we will id we will set an employee using employee service
        Schedule savedSchedule = scheduleRepository.save(schedule);
        List<Employee> employeeList;
        if(scheduleDTO.getEmployeeIds() == null) {
            employeeList = new ArrayList<>();
        } else {
             employeeList = scheduleDTO.getEmployeeIds()
                                                .stream()
                                                .map(employeeId -> employeeService.findEntityById(employeeId))
                                                .collect(Collectors.toList());
        }

        List<Pet> petList;
        if(scheduleDTO.getPetIds() == null) {
            petList = new ArrayList<>();
        } else {
            petList = scheduleDTO.getPetIds()
                    .stream()
                    .map(petId -> {
                        Pet pet = petService.findEntityById(petId);
                        pet.getSchedule().add(savedSchedule);
                        return pet;
                    })
                    .collect(Collectors.toList());
        }
        schedule.setEmployees(employeeList);
        schedule.setPets(petList);
        return scheduleTransformer.scheduleEntityToScheduleDTO(savedSchedule);
    }

    public List<ScheduleDTO> findAll() {
        return scheduleTransformer.convertToScheduleDTOList(scheduleRepository.findAll());
    }

    @Override
    public List<ScheduleDTO> findAllByPetsId(long petId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByPetsId(petId);
        return scheduleTransformer.convertToScheduleDTOList(scheduleList);
    }

    @Override
    public List<ScheduleDTO> findAllByEmployeesId(long employeeId) {
        List<Schedule> scheduleList = scheduleRepository.findAllByEmployeesId(employeeId);
        return scheduleTransformer.convertToScheduleDTOList(scheduleList);
    }

    @Override
    public List<ScheduleDTO> findAllByCustomerId(long customerId) {
        Customer customer = customerService.findEntityById(customerId);
        List<Schedule> scheduleList = new ArrayList<>();
        for(Pet pet : customer.getPets()) {
            if(pet.getSchedule() != null) {
                scheduleList.addAll(pet.getSchedule()
                        .stream()
                        .collect(Collectors.toList()));
            }
        }
        return scheduleTransformer.convertToScheduleDTOList(scheduleList);
    }

}
