package com.udacity.jdnd.course3.critter.pet;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PetNotFoundException extends RuntimeException {
    /*
    Default constructor handled by lombok
     */
    public PetNotFoundException(String message) {
        super(message);
    }
}
