package com.beyonnex.anagramanalyzer.manager;

import java.util.List;

public class AnagramAnalyzerManager {


    /**
     * This method return a boolean value,
     * based on the word1 and word2 inputs.
     *
     * Return true in case the alphanumeric elements of the two words are the same

     * @param word1 first word to perform the comparison
     * @param word2 second word to perform the comparison
     * @return boolean value that indicated if the two words are an anagram
     */
    public static boolean wordsAreAnagram(String word1, String word2) {
        return AnagramAnalyzerImpl.wordsAreAnagram(word1, word2);
    }

    /**
     * This method return a boolean value,
     * based on the word1 and word2 inputs.
     *
     * Return true in case the alphanumeric elements of the two words are the same
     *
     * <b>
     *  If the words are anagrams, than the values are saved
     *  in memory for the historical anagrams functionality,
     *  where you can retrieve all the anagrams inserted previously
     *  refer to @getHistoricalAnagramsByWord methods
     * </b>
     *
     * @param word1 first word to perform the comparison
     * @param word2 second word to perform the comparison
     * @return boolean value that indicated if the two words are an anagram
     */
    public static boolean checkIfWordsAreAnagramAndStoreResultInMemory(String word1, String word2) {
        var result = AnagramAnalyzerImpl.wordsAreAnagram(word1, word2);
        if (result) {
            AnagramAnalyzerResultsSingleton.getInstance().
                    setAnagramWordsInMemory(word1, word2);
        }
        return result;
    }

    /**
     * This method return a list of all the anagrams previously
     * inserted in the app based on the parameter word
     *
     * @param word first word to perform the comparison
     * @return a List of all the anagrams previously inserted in the app based on the parameter word
     *
     */

    public static List<String> getHistoricalAnagramsByWord(String word) {
        return AnagramAnalyzerResultsSingleton.getInstance()
                .getHistoricalAnagramsWordsByWord(word);
    }

    /**
     * This method delete all the historical anagrams from the app memory
     *
     * @return void
     *
     */
    public static void clearHistoricalAnagrams() {
        AnagramAnalyzerResultsSingleton.getInstance()
                .clearHistoricalAnagrams();
    }


}
