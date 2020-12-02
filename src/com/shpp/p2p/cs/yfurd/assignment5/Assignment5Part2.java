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
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {

        String sum = "";
        n1 = revers(n1);
        n2 = revers(n2);
        char[] nOne = n1.toCharArray();
        char[] nTwo = n2.toCharArray();

        if (nOne.length > nTwo.length) {
            sum = sum(nOne, nOne, nTwo, sum);
        } else {
            sum = sum(nTwo, nOne, nTwo, sum);
        }

        return revers(sum);
    }

    private String sum(char[] ch, char[] nOne, char[] nTwo, String sum) {
        int temp = 0;

        StringBuilder sumBuilder = new StringBuilder(sum);
        for (int i = 0; i < ch.length; i++) {

            int x = 0;
            int y = 0;

            if (i <= nOne.length) {
                x = nOne[i] - '0';
            }

            if (i <= nTwo.length) {
                y = nTwo[i] - '0';
            }


            if (x + y + temp >= 10) {
                sumBuilder.append(x + y - 10 + temp);
                temp = 1;
            } else {
                sumBuilder.append(x + y + temp);
                temp = 0;
            }
        }

        if (temp != 0) {
            sumBuilder.append(temp);
        }

        sum = sumBuilder.toString();
        return sum;
    }


    private String revers(String s) {
        StringBuilder str = new StringBuilder();
        char[] ch = s.toCharArray();

        for (int i = ch.length; i > 0; i--) {
            str.append(ch[i - 1]);
        }
        return str.toString();
    }
}
