package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    @NotNull
    private String type;

    @Nationalized
    @Column(length = 50)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;
    // many to one with owner referenced by owner id

    @NotNull
    private LocalDateTime birthDate;

    @Column(length = 500)
    private String notes;
}
