package com.beyonnex.utils;

import com.beyonnex.analyzer.AnagramAnalyzer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.beyonnex.constants.AnagramAnalyzerConstants.*;

public class AnagramAnalyzerUtils {

    private static final AnagramAnalyzer anagramAnalyzer = new AnagramAnalyzer();

    /*
        This method is the one that basically shows in the CLI
        The options that the user can execute
     */
    public static void showOptions() {
        System.out.println("1: Analyze if two words are an anagram");
        System.out.println("2: Historical anagrams");
        System.out.println("3: Clear the screen");
        System.out.println("4: Exit from the program");

        try {
            Scanner scanner = new Scanner(System.in);
            int choosedOption = scanner.nextInt();
            switch (choosedOption) {
                case 1:
                    insertWordsOption(scanner);
                    showOptions();
                    break;
                case 2:
                    showOptions();
                    break;
                case 3:
                    clearConsole();
                    showOptions();
                    break;
                case 4:
                    System.out.println(GOODBYE_CLI_TEXT);
                    System.exit(0);
                    break;
                default:
                    System.out.println(String.format("The selected option %s is not available, please try again", choosedOption));
                    showOptions();

            }
        } catch (InputMismatchException ex) {
            System.out.println("Please choose a valid integer option");
            showOptions();
        }
    }

    /*
        This method is the one that basically shows in the CLI
        the options to insert the two words
     */
    private static void insertWordsOption(Scanner scanner) {
        printWordChooseMessage("first");
        String word1 = scanner.next();
        printWordChooseMessage("second");
        String word2 = scanner.next();
        if (StringUtils.isEmpty(word1) || StringUtils.isEmpty(word2)) {
            System.out.println(String.format("Please insert two not empty words : word1: %s word2: %s", word1, word2));
            showOptions();
        } else {
            //calculate the result
            getResultAndShowInCLI(word1, word2);
        }
    }

    /*
      Method to print in the CLI the word insertion
     */
    private static void printWordChooseMessage(String wordPosition) {
        System.out.println("\n****************************");
        System.out.println(String.format("Please select the %s word", wordPosition));
        System.out.println("****************************\n");
    }

    /*
      This method call the class to check if the two words are an anagram
      in case of anagram
        1. Print the result to true
        2. Save the words in memory

      Otherwise, print that the two words are not an anagram
     */


    private static void getResultAndShowInCLI(String word1, String word2) {
        boolean isAnagram = anagramAnalyzer.wordsAreAnagram(word1, word2);
        if (isAnagram) {
            //TODO SAVE the words in memory
        }

        System.out.println(String.format("*************************************" +
                        " \n RESULT : %s IS %s ANAGRAM OF %s \n************************************",
                word1, (isAnagram ? "AN" : "NOT AN"), word2));
    }

    /*
      Method to clear the CLI
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").toUpperCase().contains(WINDOWS)) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print(CLEAR_SCREEN_CONSTANT);
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
}
