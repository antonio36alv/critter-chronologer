package com.udacity.jdnd.course3.critter.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

//    private List<Long> empl
}
