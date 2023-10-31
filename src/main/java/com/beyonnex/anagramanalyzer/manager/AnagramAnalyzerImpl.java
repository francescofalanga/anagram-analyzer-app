package com.beyonnex.anagramanalyzer.manager;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

class AnagramAnalyzerImpl {

    /*
     * This method return a boolean value,
     * based on the word1 and word2 inputs.
     *
     * Return true in case the alphanumeric values of the two words are the same
     *
     */
    static boolean wordsAreAnagram(String word1, String word2) {
        //first thing to do check if at least one of the words
        //is null or empty than return false
        if(StringUtils.isEmpty(word1) || StringUtils.isEmpty(word2))
            return false;

        //remove any space or special characters from
        //the words we have introduced so let's use the regular expression to
        //keep only words and numbers
        //and uppercase for the comparison
        var word1Filtered = AnagramAnalizerUtils.removeNonAlphanumericValues(word1).toUpperCase();
        var word2Filtered = AnagramAnalizerUtils.removeNonAlphanumericValues(word2).toUpperCase();

        //first check to avoid any unuseful comparison
        //if the length of the two string is not the same,
        //for sure will not be an anagram
        if (word1Filtered.length() != word2Filtered.length())
            return false;

        // if sorted char arrays are same
        // then the string is anagram
        return Arrays.equals(AnagramAnalizerUtils.sortCharArray(word1Filtered.toCharArray()),
                AnagramAnalizerUtils.sortCharArray(word2Filtered.toCharArray()));

    }
}
