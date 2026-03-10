package com.anva.assignment.controller;


import com.anva.assignment.model.FrequencyForWordRequest;
import com.anva.assignment.model.MostFrequentWordsRequest;
import com.anva.assignment.model.TextRequest;
import com.anva.assignment.model.WordFrequency;
import com.anva.assignment.service.WordFrequencyAnalyzer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for word frequency analysis.
 */
@RestController
@RequestMapping("${application.basepath}/word-frequency-analyzer")
@RequiredArgsConstructor
@Validated
public class WordFrequencyAnalyzerController {
    private final WordFrequencyAnalyzer wordFrequencyAnalyzerService;


    /**
     * Calculates the highest word frequency in the provided text.
     *
     * @param request the request containing the text
     * @return the highest frequency
     */
    @PostMapping("/highest-frequency")
    public ResponseEntity<Integer> highestFrequency(@Valid @RequestBody TextRequest request) {
        return ResponseEntity.ok(wordFrequencyAnalyzerService.calculateHighestFrequency(request.getText()));
    }

    /**
     * Calculates the frequency of a specific word in the provided text.
     *
     * @param request the request containing the text and the word
     * @return the frequency of the word
     */
    @PostMapping("/frequency-for-word")
    public ResponseEntity<Integer> frequencyForWord(@Valid @RequestBody FrequencyForWordRequest request) {
        return ResponseEntity.ok(
                wordFrequencyAnalyzerService.calculateFrequencyForWord(request.getText(), request.getWord())
        );
    }

    /**
     * Retrieves the n most frequent words in the provided text.
     *
     * @param request the request containing the text and the number of words
     * @return a list of the most frequent words
     */
    @PostMapping("/most-frequent-words")
    public ResponseEntity<List<WordFrequency>> mostFrequentWords(@Valid @RequestBody MostFrequentWordsRequest request) {
        return ResponseEntity.ok(
                wordFrequencyAnalyzerService.calculateMostFrequentNWords(request.getText(), request.getN())
        );
    }



}
