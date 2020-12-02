package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

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
        word = word.toLowerCase();
        char[] str = word.toCharArray();
        // array chars with vowels.
        char[] vowels = {'a', 'i', 'o', 'u', 'y', 'e'};
        int sum = 0;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'a' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u' || str[i] == 'y' || str[i] == 'e') {

                for (int j = 0; j < vowels.length; j++) {

                    if (str[i] != str[str.length - 1]) {

                        if (str[i + 1] == vowels[j]) {
                            sum--;
                        }
                    }
                }
                sum++;
            }
        }
        if (str[str.length - 1] == 'e') {
            sum--;
        }

        if (sum < 1){
            return 1;
        }
        return sum;
    }
}

