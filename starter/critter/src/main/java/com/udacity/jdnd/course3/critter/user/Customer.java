package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Customer extends User {

    @NotNull
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pet;

}
