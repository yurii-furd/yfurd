package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Assignment5Part3 extends TextProgram {

    private static final String PATH = "/home/yurii/Завантаження/en-dictionary.txt";

    public void run() {
        List<String> words = readWordsFromFile();
        while (true) {
            String inputLetters = readLine("Enter three letters: ");
            if (inputLetters.length() != 3) {
                println("Incorrect input");
            } else {
                List<String> list = findWord(inputLetters, words);
                println(list.isEmpty() ? "No words found!" : inputLetters + " -> " + list);
            }
        }
    }

    /**
     *  This method takes a string of three letters, checks for words that match the condition,
     *  and returns them if it finds.
     *
     * @param inputLetters a string of three letters entered by the user.
     * @return a list of words that can be composed of these letters.
     */
    private List<String> findWord(String inputLetters, List<String> listWithAllWord) {

        List<String> wordsThatMatched = new ArrayList<>();

        char[] arrayWordSymbols = inputLetters.toLowerCase().toCharArray();
        int coincidence = 0;

        // Run through the list of words
        for (String word : listWithAllWord) {
            //Run through each character of the word
            for (int j = 0; j < word.length(); j++) {

                /* Until the match is equal to the sum of the input letters
                and the letter from the line of the file is equal to the letter entered by the user */
                if (word.charAt(j) == arrayWordSymbols[coincidence]) {
                    coincidence++;

                    /* If the matches are equal to the sum of the input letters entered by the user,
                     add the word to the list*/
                    if (coincidence == arrayWordSymbols.length) {
                        wordsThatMatched.add(word);
                        // stop processing of the current word
                        break;
                    }
                }
            }
            coincidence = 0;
        }
        return wordsThatMatched;
    }

    /**
     * This method reads information from the file line by line and adds it to the letter.
     * @return  a list of lines read from the file.
     */
    private List<String> readWordsFromFile() {

        List<String> listWithWord = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                listWithWord.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listWithWord;
    }
}
