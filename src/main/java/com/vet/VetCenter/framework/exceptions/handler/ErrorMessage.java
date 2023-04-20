package com.vet.VetCenter.framework.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage implements Serializable {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
