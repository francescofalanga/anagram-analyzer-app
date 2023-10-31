package com.beyonnex;

import com.beyonnex.anagramanalyzer.manager.AnagramAnalyzerManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AnagramAnalyzerManagerTest {

    private static String word_A = "lamb";
    private static String word_B = "blam";
    private static String word_C = "happy";
    private static String word_D = "balm";

    //class to test
    AnagramAnalyzerManager tested = new AnagramAnalyzerManager();


//Test part
// 1. Simple Functionality to check if two words are an anagram
    //OK TESTS
    @Test
    public void anagramAnalyzerManagerOKForRealAnagram() {
        Assertions.assertTrue(tested.wordsAreAnagram("dog", "god"));
    }
    @Test
    public void anagramAnalyzerManagerOKForRealAnagramsWithSpaces() {
        Assertions.assertTrue(tested.wordsAreAnagram("g o d", "d o g"));
    }
    @Test
    public void anagramAnalyzerManagerOKForRealAnagramsWithSpacesAndDifferentCase() {
        Assertions.assertTrue(tested.wordsAreAnagram("G O D", "d o g"));
    }
    @Test
    public void anagramAnalyzerManagerOKForRealAnagramsWithSpacesAndDifferentCaseAndNoAlfanumericValues() {
        Assertions.assertTrue(tested.wordsAreAnagram("G O D!", "!d o g"));
    }
    // KO TESTS
    @Test
    public void anagramAnalyzerManagerKOForNotAnagramWords() {
        Assertions.assertFalse(tested.wordsAreAnagram("dog", "dig"));
    }
    @Test
    public void anagramAnalyzerManagerKOForWordsDifferentSize() {
        Assertions.assertFalse(tested.wordsAreAnagram("dog", "smog"));
    }
    @Test
    public void anagramAnalyzerManagerKOForWordsOneNull() {
        Assertions.assertFalse(tested.wordsAreAnagram("dog",null));
    }

// 2. Test the historical anagram functionality
    @Test
    public void anagramAnalyzerManagerOKHistoricalAnagrams() {
        /*
        For feature #2:
        - Given these hypothetical invocations of the feature#1 function -> f1(A, B), f1(A, C), f1(A, D)
        - *IF* A, B and D are anagrams
        - f2(A) should return [B, D]
        - f2(B) should return [A, D]
        - f2(C) should return []
         */
        //clean the memory
        tested.clearHistoricalAnagrams();
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_B);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_C);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_D);

        var historical_A = tested.getHistoricalAnagramsByWord(word_A);
        Assertions.assertEquals(historical_A.size(), 2);
        Assertions.assertTrue(!historical_A.contains(word_A));

        var historical_B = tested.getHistoricalAnagramsByWord(word_B);
        Assertions.assertEquals(historical_B.size(), 2);
        Assertions.assertTrue(!historical_B.contains(word_B));

        Assertions.assertEquals(tested.getHistoricalAnagramsByWord(word_C).size(), 0);

        var historical_D = tested.getHistoricalAnagramsByWord(word_D);
        Assertions.assertEquals(historical_D.size(), 2);
        Assertions.assertTrue(!historical_D.contains(word_D));    }

    @Test
    public void anagramAnalyzerManagerKOHistoricalAnagrams() {
        //clean the memory
        tested.clearHistoricalAnagrams();
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_B);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_C);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_D);

        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_A).size(), 1);
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_B).size(), 0);
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_C).size(), 2);
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_D).size(), 1);
    }

// 3. Test the clear memory function
    @Test
    public void anagramAnalyzerManagerOKClearHistory() {
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_B);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_C);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_D);
        tested.clearHistoricalAnagrams();
        Assertions.assertEquals(tested.getHistoricalAnagramsByWord(word_A).size(), 0);
        Assertions.assertEquals(tested.getHistoricalAnagramsByWord(word_B).size(), 0);
        Assertions.assertEquals(tested.getHistoricalAnagramsByWord(word_D).size(), 0);
    }

    @Test
    public void anagramAnalyzerManagerKOClearHistory() {
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_B);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_C);
        tested.checkIfWordsAreAnagramAndStoreResultInMemory(word_A,word_D);
        tested.clearHistoricalAnagrams();
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_A).size(), 2);
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_B).size(), 2);
        Assertions.assertNotEquals(tested.getHistoricalAnagramsByWord(word_D).size(), 2);
    }
}
