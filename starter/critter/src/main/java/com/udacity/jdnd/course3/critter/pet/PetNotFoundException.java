package com.udacity.jdnd.course3.critter.pet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException() {
    }

    public PetNotFoundException(String message) {
        super(message);
    }

}
