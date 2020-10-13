package com.udacity.jdnd.course3.critter.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@Entity
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Customer extends User {

    @NotNull
    private String phoneNumber;

}
