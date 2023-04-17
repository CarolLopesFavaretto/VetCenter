package com.vet.VetCenter.framework.adapters.in.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultationRequest {

    private String nameVeterinary;
    private Double value;
    private String cause;
    private String observations;
    private Boolean regress;
}
