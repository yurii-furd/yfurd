package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.util.ArrayList;
import java.util.List;

public class AlgorismAlgorithms extends TextProgram {

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

        for (int i = 0; i < ch.length; i++) {

            int x = 0;
            int y = 0;

            if (nOne[i] < nOne.length) {
                x = 0;
            } else {
                x = nOne[i] - '0';
            }

            if (nTwo[i] < nTwo.length){
                y = 0;
            } else {
                y = nTwo[i] - '0';
            }


            if (x + y + temp >= 10) {
                sum += x + y - 10 + temp;
                temp = 1;
            } else {
                sum += x + y + temp;
                temp = 0;
            }
        }
        return sum;
    }


    private String revers(String s) {
        String str = "";
        char[] ch = s.toCharArray();

        for (int i = ch.length; i > 0; i--) {
            str += ch[i - 1];
        }
        return str;
    }
}
