package com.vet.VetCenter.framework.adapters.in.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalResponse {

    private Long id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private Long guardianId;
}
