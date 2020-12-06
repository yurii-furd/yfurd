package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        char[] wordCharArray = word.toLowerCase().toCharArray();
        // List chars with vowels.
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'i', 'o', 'u', 'y', 'e'));

        int sum = 0;
        // We run through an array of spells.
        for (int i = 0; i < wordCharArray.length; i++) {
            //if a letter with vowels = iteration letter i
            if (vowels.contains(wordCharArray[i])) {

                /*1. if both the element is less than the length of the array and the element i + 1
                *    does not contain an element from the letter of vowels.
                * 2. if the last element contains a letter element of vowels and is not equal to the letter e.
                * 3. if before the last element is equal to a vowel, and the last is equal to the letter e.
                * then increase the amount by 1
                */
                if (i < wordCharArray.length - 1 && !vowels.contains(wordCharArray[i + 1])
                        || i == wordCharArray.length - 1 && vowels.contains(wordCharArray[i]) && wordCharArray[i] != 'e'
                        || i == wordCharArray.length - 1  && vowels.contains(wordCharArray[i - 1]) && wordCharArray[i] == 'e') {
                    sum++;
                }
            }
        }
        //The method must always return at least 1 syllable.
        if (sum < 1) {
            sum = 1;
        }
        return sum;
    }
}