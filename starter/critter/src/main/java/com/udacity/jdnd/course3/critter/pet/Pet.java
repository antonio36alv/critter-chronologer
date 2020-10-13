package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
//@NoArgsConstructor
//@RequiredArgsConstructor
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    @NotNull
    private PetType type;

    @Nationalized
    @Column(length = 50)
    @NotNull
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;
//    @NotNull
    // many to one with owner referenced by owner id

    @NotNull
    private LocalDate birthDate;

    @Column(length = 500)
    private String notes;

}
