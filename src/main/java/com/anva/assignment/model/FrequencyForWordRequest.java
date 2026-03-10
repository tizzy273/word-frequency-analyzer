package com.anva.assignment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FrequencyForWordRequest {
    @NotBlank
    private String text;
    @NotBlank
    private String word;
}
