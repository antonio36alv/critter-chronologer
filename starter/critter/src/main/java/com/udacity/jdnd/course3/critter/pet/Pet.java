package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

//@NoArgsConstructor
//@RequiredArgsConstructor
@Entity
@Data
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

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate birthDate;

    @Column(length = 500)
    private String notes;

    @ManyToMany(mappedBy = "pet")
    private List<Schedule> schedule;

}
