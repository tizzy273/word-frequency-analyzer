package com.anva.assignment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MostFrequentWordsRequest {

    @NotBlank
    private String text;

    @NotNull
    @Min(0)
    private Integer n;



}
