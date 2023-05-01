package com.vet.VetCenter.framework.adapters.in.dtos.filter;

import lombok.Data;

@Data
public class PrescriptionFilter {

    private String medication;
    private Long consultationId;
}
