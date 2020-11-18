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

            while (isHeads()) {
                moneyOnTheTable *= 2;
            }
            System.out.println("This game, you earned $1 " + moneyOnTheTable);
            happyMan += moneyOnTheTable;
            System.out.println("Your total is $ " + happyMan);
        }
        System.out.println("It took " + count + " games to earn $20");
    }

    //this method simulates tossing a coin
    //the method randomly selects a number from [0;1)
    //and returns a true(heads) if the number is less than 0.5 and a false(tails) if greater
    private boolean isHeads() {
        return Math.random() < 0.5;
    }
}
