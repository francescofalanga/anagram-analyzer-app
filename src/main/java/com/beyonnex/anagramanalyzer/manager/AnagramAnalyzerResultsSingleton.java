package com.beyonnex.anagramanalyzer.manager;

import java.util.*;
import java.util.stream.Collectors;

class AnagramAnalyzerResultsSingleton {

    private static AnagramAnalyzerResultsSingleton instance;
    private static Map<String, Set<String>> results;

    private AnagramAnalyzerResultsSingleton(Map<String, Set<String>> results) {
        this.results = results;
    }

    static synchronized AnagramAnalyzerResultsSingleton getInstance() {
        if (instance == null) {
            instance = new AnagramAnalyzerResultsSingleton(new HashMap<>());
        }
        return instance;
    }

    // save the words in memory if they are an anagram
    static void setAnagramWordsInMemory(String word1, String word2){
        var key = AnagramAnalizerUtils.filterAndSortWord(word1);
        if(results.keySet().contains(key.toUpperCase())){
            results.get(key.toUpperCase()).addAll(List.of(word1.toUpperCase(),word2.toUpperCase()));
        }else{
            results.put(key.toUpperCase(), new HashSet<>(List.of(word1.toUpperCase(), word2.toUpperCase())));
        }
    }

    // return the list of the historical anagrams saved in memory based on a word
    static List<String> getHistoricalAnagramsWordsByWord(String word){
        var key = AnagramAnalizerUtils.filterAndSortWord(word);
        return results.keySet().contains(key.toUpperCase())
                ? new ArrayList<>(results.get(key.toUpperCase())).stream()
                .filter(w -> !w.equalsIgnoreCase(word))
                .collect(Collectors.toList())
                    : Collections.emptyList();
    }

    // clear the memory of the historicals anagrams
    public void clearHistoricalAnagrams(){
        results = new HashMap<>();
    }
}
