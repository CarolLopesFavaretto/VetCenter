package com.vet.VetCenter.framework.adapters.in.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationResponse {

    private Long id;
    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private LocalDate date;
    private Boolean regress;
    private Long animalId;
}
