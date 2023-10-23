package com.beyonnex;

import com.beyonnex.analyzer.AnagramAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramAnalyzerTest {


    private AnagramAnalyzer tested = new AnagramAnalyzer();

    //OK TESTS

    @Test
    public void anagramAnalyzerOKForRealAnagram() {
        Assertions.assertTrue(tested.wordsAreAnagram("god", "dog"));
    }

    @Test
    public void anagramAnalyzerOKForRealAnagramsWithSpaces() {
        Assertions.assertTrue(tested.wordsAreAnagram("g o d", "d o g"));
    }

    @Test
    public void anagramAnalyzerOKForRealAnagramsWithSpacesAndDifferentCase() {
        Assertions.assertTrue(tested.wordsAreAnagram("G O D", "d o g"));
    }

    @Test
    public void anagramAnalyzerOKForRealAnagramsWithSpacesAndDifferentCaseAndNoAlfanumericValues() {
        Assertions.assertTrue(tested.wordsAreAnagram("G O D!", "!d o g"));
    }

    // KO TESTS
    @Test
    public void anagramAnalyzerKOForNotAnagramWords() {
        Assertions.assertFalse(tested.wordsAreAnagram("god", "dig"));
    }

    @Test
    public void anagramAnalyzerKOForWordsDifferentSize() {
        Assertions.assertFalse(tested.wordsAreAnagram("dog", "smog"));
    }


}
