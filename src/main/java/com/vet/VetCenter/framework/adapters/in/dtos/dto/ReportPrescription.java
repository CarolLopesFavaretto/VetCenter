package com.vet.VetCenter.framework.adapters.in.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportPrescription {

    private String nameGuardian;
    private String cpf;
    private Long telephone;
    private String nameAnimal;
    private String nameVeterinary;
    private String cause;
    private String observations;
    private String medication;
    private LocalDate date;
}
