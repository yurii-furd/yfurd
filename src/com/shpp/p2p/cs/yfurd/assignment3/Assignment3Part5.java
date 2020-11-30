package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part5 extends TextProgram {

    public void run() {

        //the amount of $ that the lucky person has
        int happyMan = 0;
        //counts the number of games before the lucky winner receives $ 20
        int count = 0;

        while (happyMan < 20) {
            count++;
            int moneyOnTheTable = 1;

            while (Math.random() < 0.5) {
                moneyOnTheTable *= 2;
            }
            System.out.println("This game, you earned " + moneyOnTheTable + " $");
            happyMan += moneyOnTheTable;
            System.out.println("Your total is " + happyMan + " $" );
        }
        System.out.println("It took " + count + " games to earn 20 $");
    }
}
