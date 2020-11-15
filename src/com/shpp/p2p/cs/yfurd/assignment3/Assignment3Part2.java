package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    public void run() {

        println("Enter a number:");
        int inputNumber = readInt();

        if (inputNumber < 1) {
            System.out.println("Incorrect input");
        } else {
            calculate(inputNumber);
        }
    }

    /**
     * This method calculates the input number until it becomes less than one.
     * if the number is even then divide it by 2
     * if the number is odd then multiply it by 3 and add 1
     */
    private void calculate(int inputNumber) {
        int value = inputNumber;
        while (value > 1) {
            if (value % 2 == 0) {
                System.out.println(value + " - is even so I take half: " + (value / 2));
                value = value / 2;
            } else {
                System.out.println(value + " - is odd so I make 3n + 1: " + (3 * value + 1));
                value = 3 * value + 1;
            }
        }
        System.out.println("The end");
    }
}
