package com.anva.assignment.service;

import com.anva.assignment.model.WordFrequency;
import com.anva.assignment.model.WordFrequencyImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for analyzing word frequencies in a text.
 */
@Service
public class WordFrequencyAnalyzerService implements WordFrequencyAnalyzer {

    /**
     * Calculates the highest word frequency in the given text.
     *
     * @param text the text to analyze
     * @return the highest frequency
     */
    @Override
    public int calculateHighestFrequency(String text) {
        return buildFrequencyMap(text).values().stream()
                .max(Integer::compareTo)
                .orElse(0);
    }

    /**
     * Calculates the frequency of a specific word in the given text.
     *
     * @param text the text to analyze
     * @param word the word to look for
     * @return the frequency of the word
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        return buildFrequencyMap(text).getOrDefault(word.toLowerCase(), 0);
    }

    /**
     * Returns the n most frequent words in the given text.
     *
     * @param text the text to analyze
     * @param n    the number of words to return
     * @return a list of the most frequent words
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        return buildFrequencyMap(text).entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .map(entry -> new WordFrequencyImpl(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Builds a map of word frequencies from the given text.
     *
     * @param text the text to analyze
     * @return a map of words and their frequencies
     */
    private Map<String, Integer> buildFrequencyMap(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyMap();
        }
        String[] words = text.toLowerCase().split("[^a-zA-Z]+");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!word.isBlank()) {
                map.merge(word, 1, Integer::sum);
            }
        }
        return map;
    }

}
