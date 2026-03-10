package com.anva.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Implementation of the WordFrequency interface.
 */
@Getter
@AllArgsConstructor
public class WordFrequencyImpl implements WordFrequency {

    /**
     * The word.
     */
    private String word;

    /**
     * The frequency of the word.
     */
    private int frequency;
}
