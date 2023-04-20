package com.vet.VetCenter.framework.adapters.in.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionRequest {

    private String medication;
    private LocalDate date;
    private Long consultationId;
}
