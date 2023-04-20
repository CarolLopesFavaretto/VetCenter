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
public class PrescriptionResponse {

    private Long id;
    private String medication;
    private LocalDate date;
    private Long consultationId;
}
