package com.anva.assignment.service;


import com.anva.assignment.model.WordFrequency;

import java.util.List;

/**
 * Service for analyzing word frequencies in a text.
 */
public interface WordFrequencyAnalyzer {

    /**
     * Calculates the highest word frequency in the given text.
     *
     * @param text the text to analyze
     * @return the highest frequency
     */
    int calculateHighestFrequency(String text);

    /**
     * Calculates the frequency of a specific word in the given text.
     *
     * @param text the text to analyze
     * @param word the word to look for
     * @return the frequency of the word
     */
    int calculateFrequencyForWord(String text, String word);

    /**
     * Returns the n most frequent words in the given text.
     *
     * @param text the text to analyze
     * @param n    the number of words to return
     * @return a list of the most frequent words
     */
    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}
