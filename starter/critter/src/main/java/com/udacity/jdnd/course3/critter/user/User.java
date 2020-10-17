package com.udacity.jdnd.course3.critter.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

/*
As of now I see no reason to support polymorphic queries,
will change as I see fit
 */
@MappedSuperclass
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    @Column(length = 50)
    private String name;

}
