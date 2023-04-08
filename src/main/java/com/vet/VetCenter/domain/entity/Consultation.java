package com.vet.VetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {

    private Long id;
    private String nameVeterinary;
    private BigDecimal value;
    private String cause;
    private String observations;
    private LocalDateTime date;
    private Boolean regress;

}
