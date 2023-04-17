package com.vet.VetCenter.framework.adapters.in.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalRequest {

    private String name;
    private Integer age;
    private String type;
    private String race;

}
