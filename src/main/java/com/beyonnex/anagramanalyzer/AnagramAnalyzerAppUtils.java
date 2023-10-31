package com.beyonnex.anagramanalyzer;

import com.beyonnex.anagramanalyzer.manager.AnagramAnalyzerManager;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class AnagramAnalyzerAppUtils {

    private static final AnagramAnalyzerManager manager = new AnagramAnalyzerManager();

    //messages for the CLI
    static final String WELCOME_CLI_TEXT =
            "   _   _   _   _   _   _   _     _   _     _   _   _     _   _   _   _   _   _   _     _   _   _   _   _   _   _   _  \n" +
                    "  / \\ / \\ / \\ / \\ / \\ / \\ / \\   / \\ / \\   / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ \n" +
                    " ( w | e | l | c | o | m | e ) ( t | o ) ( t | h | e ) ( a | n | a | g | r | a | m ) ( a | n | a | l | y | z | e | r )\n" +
                    "  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/   \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \n\n";

    static final String GOODBYE_CLI_TEXT =
            "\n   _   _   _   _   _   _     _   _   _     _   _   _   _   _     _   _   _   _   _   _   _   _   _   _     _   _   _   _   _   _   _   _  \n" +
                    "  / \\ / \\ / \\ / \\ / \\ / \\   / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\   / \\ / \\ / \\ / \\ / \\ / \\ / \\ / \\ \n" +
                    " ( T | h | a | n | k | s ) ( f | o | r ) ( u | s | i | n | g ) ( t | h | e | a | n | a | g | r | a | m ) ( a | n | a | l | y | z | e | r )\n" +
                    "  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/   \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/ \\_/  \n";

    //Clear CLI constant value
    static final String CLEAR_SCREEN_CONSTANT = "\033\143";

    //windows constant
    static final String WINDOWS = "WINDOWS";

    /*
        This method is the one that basically shows in the CLI
        The options that the user can execute
     */
    public static void showOptions() {
        System.out.println("1: Analyze if two words are an anagram");
        System.out.println("2: Historical anagrams - Retrieve all the previous insterted anagrams by word");
        System.out.println("3: Clear the screen");
        System.out.println("4: Exit from the program");

        try {
            Scanner scanner = new Scanner(System.in);
            var choosedOption = scanner.nextInt();
            switch (choosedOption) {
                case 1:
                    insertWordsOption(scanner);
                    showOptions();
                    break;
                case 2:
                    insertWordOptionForHistoricalWordsAsAnagramAndShowResult(scanner);
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
        var word1 = scanner.next();
        printWordChooseMessage("second");
        var word2 = scanner.next();
        if (StringUtils.isEmpty(word1) || StringUtils.isEmpty(word2)) {
            System.out.println(String.format("Please insert two not empty words : word1: %s word2: %s", word1, word2));
            showOptions();
        } else {
            //calculate the result
            getResultAndShowInCLI(word1, word2);
        }
    }

    /*
       This method receive in input a word,
       and retrieve from the memory the list of anagrams based on this word
     */
    private static void insertWordOptionForHistoricalWordsAsAnagramAndShowResult(Scanner scanner) {
        printWordChooseMessage("choosed");
        var word = scanner.next();
        if (StringUtils.isEmpty(word)) {
            System.out.println(String.format("Please insert a word"));
            showOptions();
        } else {
            var result = manager.getHistoricalAnagramsByWord(word);
            System.out.println(String.format("*************************************" +
                            " \n The list of the historical Anagrams for the word %s is %s\n************************************",
                    word, StringUtils.join(result)));
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

        var isAnagram = manager.checkIfWordsAreAnagramAndStoreResultInMemory(word1, word2);

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
