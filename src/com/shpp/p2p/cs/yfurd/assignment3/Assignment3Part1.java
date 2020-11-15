package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    public void run() {

        int[] array = new int[7];

        for (int i = 0; i < array.length; i++) {
            print("How many minutes did you do on day" + (i + 1) + "?");
            array[i] = readInt();
        }

        int count30 = 0;
        int count40 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 30) {
                count30++;
            }
            if (array[i] > 40) {
                count40++;
            }
        }

        System.out.println("Cardiovacular health:");
        if (count30 <= 5) {
            System.out.println("You needed to train hard for at least " + (5 - count30) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        }

        System.out.println("Blood pressure:");
        if (count40 <= 3) {
            System.out.println("You needed to train hard for at least " + (3 - count30) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        }
    }
}