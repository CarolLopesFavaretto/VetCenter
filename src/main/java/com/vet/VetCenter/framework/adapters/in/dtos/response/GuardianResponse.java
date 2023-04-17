package com.vet.VetCenter.framework.adapters.in.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardianResponse {

    private Long id;
    private String name;
    private String cpf;
    private Long telephone;
}
