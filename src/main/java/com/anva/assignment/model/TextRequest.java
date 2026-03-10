package com.anva.assignment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TextRequest {

    @NotBlank
    private String text;
}
