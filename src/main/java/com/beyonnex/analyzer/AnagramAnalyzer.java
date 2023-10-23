package com.beyonnex.analyzer;

import lombok.NoArgsConstructor;
import java.util.Arrays;

import static com.beyonnex.constants.AnagramAnalyzerConstants.ONLY_LETTERS_AND_NUMBERS_PATTERN;

@NoArgsConstructor
public class AnagramAnalyzer{

    /**
     * This method return a boolean value,
     * based on the word1 and word2 inputs.
     *
     * Return true in case the alphanumeric elements of the two words are the same
     *
     * @param word1 first word to perform the comparison
     * @param word2 second word to perform the comparison
     * @return boolena value that indicated if the two words are anagram
     */


    public boolean wordsAreAnagram(String word1, String word2) {

        //first thing to do is to remove any space or special characters from
        //the words we have introduced so let's use the regular expression to
        //keep only words and numbers
        //and uppercase for the comparison
        String word1Filtered = removeNonAlphanumericValues(word1).toUpperCase();
        String word2Filtered = removeNonAlphanumericValues(word2).toUpperCase();

        //first check to avoid any unuseful comparison
        //if the length of the two string is not the same,
        //for sure will not be an anagram
        if (word1Filtered.length() != word2Filtered.length())
            return false;

        // if sorted char arrays are same
        // then the string is anagram
        return Arrays.equals(sortCharArray(word1Filtered.toCharArray()),
                sortCharArray(word2Filtered.toCharArray()));

    }

    // Function to remove non-alphanumeric
    // characters from string
    private static String  removeNonAlphanumericValues(String str) {
        // replace the given string
        // with empty string except the pattern "[^a-zA-Z0-9]"
        return  str.replaceAll(ONLY_LETTERS_AND_NUMBERS_PATTERN, "");
    }

    // Method to
    // sort the char array
    private char[] sortCharArray( char[] charArray) {
       Arrays.sort(charArray);
       return charArray;
    }
}
