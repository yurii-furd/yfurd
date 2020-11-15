package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    //The amount of days on which doctors recommend 30 minutes of aerobics.
    private final static int daysInWhichMoreThan30Minutes = 5;
    //The amount of days that doctors recommend at least 40 minutes to maintain low blood pressure.
    private final static int daysInWhichMoreThan40Minutes = 3;

    public void run() {

        int[] array = new int[7];

        readInputData(array);

        int countDaysInWhichMoreThan30Minutes = 0;
        int countDaysInWhichMoreThan40Minutes = 0;

        for (int i: array) {
            if (i >= 30){
                countDaysInWhichMoreThan30Minutes++;
            }
            if (i >= 40){
                countDaysInWhichMoreThan40Minutes++;
            }
        }

        System.out.println("Cardio vascular health:");
        if (countDaysInWhichMoreThan30Minutes <= 5) {
            System.out.println("You needed to train hard for at least " + (daysInWhichMoreThan30Minutes - countDaysInWhichMoreThan30Minutes) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        }

        System.out.println("Blood pressure:");
        if (countDaysInWhichMoreThan40Minutes <= 3) {
            System.out.println("You needed to train hard for at least " + (daysInWhichMoreThan40Minutes - countDaysInWhichMoreThan40Minutes) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }
    }

    //This method reads the input date.
    private void readInputData(int[] array) {
        for (int i = 0; i < array.length; i++) {
            print("How many minutes did you do on day" + (i + 1) + "?");
            array[i] = readInt();
        }
    }
}