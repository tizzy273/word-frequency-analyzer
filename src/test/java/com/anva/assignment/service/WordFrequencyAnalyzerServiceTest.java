package com.anva.assignment.service;

import com.anva.assignment.model.WordFrequency;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordFrequencyAnalyzerServiceTest {

    private final WordFrequencyAnalyzerService wordFrequencyAnalyzerService = new WordFrequencyAnalyzerService();

    @Test
    void calculateHighestFrequencyReturnsMaxCountTest() {
        int result = wordFrequencyAnalyzerService.calculateHighestFrequency("the the and and and but");
        assertEquals(3, result);
    }

    @Test
    void calculateHighestFrequencyReturnsOneWhenAllWordsUniqueTest() {
        int result = wordFrequencyAnalyzerService.calculateHighestFrequency("one two three");
        assertEquals(1, result);
    }

    @Test
    void calculateFrequencyForWordCountsOccurrencesCaseInsensitiveExpectationTest() {
        int result = wordFrequencyAnalyzerService.calculateFrequencyForWord("Hello hello world", "hello");
        assertEquals(2, result);
    }

    @Test
    void calculateFrequencyForWordReturnsZeroWhenWordNotPresentTest() {
        int result = wordFrequencyAnalyzerService.calculateFrequencyForWord("one two three", "missing");
        assertEquals(0, result);
    }

    @Test
    void calculateMostFrequentNWordsOrdersByFrequencyDescTest() {
        List<WordFrequency> result = wordFrequencyAnalyzerService.calculateMostFrequentNWords("c c c b b a", 3);

        assertEquals(3, result.size());

        assertEquals("c", result.get(0).getWord());
        assertEquals(3, result.get(0).getFrequency());

        assertEquals("b", result.get(1).getWord());
        assertEquals(2, result.get(1).getFrequency());

        assertEquals("a", result.get(2).getWord());
        assertEquals(1, result.get(2).getFrequency());
    }

    @Test
    void calculateMostFrequentNWordsBreaksTiesAlphabeticallyTest() {
        List<WordFrequency> result = wordFrequencyAnalyzerService.calculateMostFrequentNWords("b b a a c", 2);

        assertEquals(2, result.size());

        assertEquals("a", result.get(0).getWord());
        assertEquals(2, result.get(0).getFrequency());

        assertEquals("b", result.get(1).getWord());
        assertEquals(2, result.get(1).getFrequency());
    }

    @Test
    void calculateMostFrequentNWordsLimitsToNTest() {
        List<WordFrequency> result = wordFrequencyAnalyzerService.calculateMostFrequentNWords("a a a b b c", 1);

        assertEquals(1, result.size());
        assertEquals("a", result.get(0).getWord());
        assertEquals(3, result.get(0).getFrequency());
    }

    @Test
    void calculateMostFrequentNWordsWhenNExceedsUniqueWordsReturnsAllTest() {
        List<WordFrequency> result = wordFrequencyAnalyzerService.calculateMostFrequentNWords("a a b c", 10);

        assertEquals(3, result.size());

        assertEquals("a", result.get(0).getWord());
        assertEquals(2, result.get(0).getFrequency());

        assertEquals("b", result.get(1).getWord());
        assertEquals(1, result.get(1).getFrequency());

        assertEquals("c", result.get(2).getWord());
        assertEquals(1, result.get(2).getFrequency());
    }

    @Test
    void calculateHighestFrequencyReturnsZeroForNullTextTest() {
        assertEquals(0, wordFrequencyAnalyzerService.calculateHighestFrequency(null));
    }

    @Test
    void calculateHighestFrequencyReturnsZeroForOnlySeparatorsTest() {
        assertEquals(0, wordFrequencyAnalyzerService.calculateHighestFrequency("!@# $%^ &*()"));
    }

    @Test
    void calculateMostFrequentNWordsReturnsEmptyListForEmptyTextTest() {
        assertTrue(wordFrequencyAnalyzerService.calculateMostFrequentNWords("", 5).isEmpty());
    }
}