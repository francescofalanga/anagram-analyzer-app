package com.beyonnex.anagramanalyzer.manager;

import java.util.Arrays;

import static com.beyonnex.anagramanalyzer.manager.AnagramAnalyzerConstants.ONLY_LETTERS_AND_NUMBERS_PATTERN;

class AnagramAnalizerUtils {

    // Function that sum removeNonAlphanumericValues and sortCharArray methods
    static String filterAndSortWord(String word){
        var filteredWord = removeNonAlphanumericValues(word).toUpperCase();
        return new String(sortCharArray(filteredWord.toCharArray()));
    }

    // Function to remove non-alphanumeric
    // characters from string
    static String removeNonAlphanumericValues(String str) {
        // replace the given string
        // with empty string except the pattern "[^a-zA-Z0-9]"
        return  str.replaceAll(ONLY_LETTERS_AND_NUMBERS_PATTERN, "");
    }

    // Method to
    // sort the char array
    static char[] sortCharArray( char[] charArray) {
        Arrays.sort(charArray);
        return charArray;
    }
}
