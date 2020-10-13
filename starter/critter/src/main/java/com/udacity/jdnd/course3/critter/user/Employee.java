package com.udacity.jdnd.course3.critter.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
//@NoArgsConstructor
public class Employee extends User {

    @ElementCollection(targetClass = String.class)
    private List<String> skills;

}
