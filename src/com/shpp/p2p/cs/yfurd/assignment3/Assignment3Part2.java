package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    public void run() {

        println("Enter a number:");
        int inputNumber = readInt();

            findOutAnEvenOrOddNumber(inputNumber);
    }

    //This method solves the problem.
    private void findOutAnEvenOrOddNumber(int inputNumber) {
        while (inputNumber > 1) {
            if (inputNumber % 2 == 0) {
                System.out.println(inputNumber + " - is even so I take half: " + (inputNumber / 2));
                inputNumber = inputNumber / 2;
            } else {
                System.out.println(inputNumber + " - is odd so I make 3n + 1: " + (3 * inputNumber + 1));
                inputNumber = 3 * inputNumber + 1;
            }
        }
        System.out.println("The end");
    }
}
