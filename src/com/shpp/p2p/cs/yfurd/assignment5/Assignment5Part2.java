package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * This method takes two strings turns them into an array char
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        char[] nOne = revers(n1).toCharArray();
        char[] nTwo = revers(n2).toCharArray();

        int maxLength = Math.max(nOne.length, nTwo.length);

        return sum(maxLength, nOne, nTwo);
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param maxLength takes the maximum length of the array
     * @param nOne the first array char created from the string
     * @param nTwo the second array char created from the string
     * @return a String representation of n1 + n2
     */
    private String sum(int maxLength, char[] nOne, char[] nTwo) {
        int temp = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {

            int x = 0;
            int y = 0;

            if (i < nOne.length) {
                x = nOne[i] - '0';
            }
            if (i < nTwo.length) {
                y = nTwo[i] - '0';
            }

            int sumNumbers = x + y + temp;
            result.append(sumNumbers % 10);
            temp = sumNumbers / 10;
        }

        if (temp != 0) {
            result.append(temp);
        }
        return result.reverse().toString();
    }

    /**
     * Takes the term and reverses it.
     *
     * @param word accepts the term.
     * @return returns the reverse term.
     */
    private String revers(String word) {
        return new StringBuilder(word).reverse().toString();
    }
}
