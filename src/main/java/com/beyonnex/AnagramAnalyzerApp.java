package com.beyonnex;

import com.beyonnex.utils.AnagramAnalyzerUtils;

import static com.beyonnex.constants.AnagramAnalyzerConstants.*;

public class AnagramAnalyzerApp {

    public static void main(String[] args) {
        System.out.println(WELCOME_CLI_TEXT);
        AnagramAnalyzerUtils.showOptions();
    }
}
