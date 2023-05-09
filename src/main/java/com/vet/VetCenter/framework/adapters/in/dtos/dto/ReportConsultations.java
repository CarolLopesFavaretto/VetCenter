package com.vet.VetCenter.framework.adapters.in.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportConsultations {

    private String nameGuardian;
    private String cpf;
    private Long telephone;
    private String nameAnimal;
    private Integer age;
    private String type;
    private String race;
    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private LocalDate date;
    private Boolean regress;


}
