package com.vet.VetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private Long id;
    private String name;
    private Integer age;
    private String type;
    private String race;
    private Long guardianId;

}
