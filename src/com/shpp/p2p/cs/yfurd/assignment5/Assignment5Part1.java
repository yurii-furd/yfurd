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
        boolean isE = false;

        for (int i = 0; i < str.length; i++) {
            for (char vowel : vowels) {
                if (str[i] == vowel) {

                    if (i == str.length - 1 && str[i] == 'e' && i != 0) {
                        if (    str[i - 1] != 'a' ||
                                str[i - 1] != 'i' ||
                                str[i - 1] != 'o' ||
                                str[i - 1] != 'u' ||
                                str[i - 1] != 'y' ||
                                str[i - 1] != 'e'
                        ) {
                            isE = true;

                        }
                    }

                    sum++;
                    for (char c : vowels) {
                        if (i + 1 < str.length && str[i + 1] == c) {
                            sum--;
                        }
                    }
                }
            }
        }
        if (isE) {
            sum--;
            isE = false;
        }

        if (sum < 1) {
            return 1;
        }

        return sum;
    }
}

/*  !Words to check!

    Unity: 3 syllables
    Unite: 2 syllables
    Growth: 1 syllable
    Possibilities: 5 syllables
    Nimble: 1 syllable (насправді 2, але наш спрощений алгоритм повинен видати 1)
    Me: 1 syllable
    Beautiful: 3 syllables
    Manatee: 3 syllables
    quokka — 2 склади,
    springbok - 2 склади
    syllable - 2 склади
*/
