package com.mobtail.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractIncidentRequest {

    @NotEmpty(message = "Line is required")
    protected String line;

    @NotEmpty(message = "User is required")
    protected String user;

}
