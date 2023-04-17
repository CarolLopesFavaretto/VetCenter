package com.vet.VetCenter.framework.adapters.in.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardianRequest {

    private String name;
    private String cpf;
    private Long telephone;
}
