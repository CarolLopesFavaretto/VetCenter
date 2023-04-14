package com.vet.VetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    private Long id;
    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private LocalDate date;
    private Boolean regress;
    private Long animalId;
}
