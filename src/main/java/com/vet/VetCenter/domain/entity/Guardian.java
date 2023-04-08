package com.vet.VetCenter.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guardian {

    private Long id;
    private String name;
    private String cpf;
    private Long telephone;

}
