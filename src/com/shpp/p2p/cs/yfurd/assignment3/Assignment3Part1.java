package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    //The amount of days on which doctors recommend 30 minutes of aerobics.
    private static final int AEROBIC_DAYS_REQUIRED = 5;
    //The amount of days that doctors recommend at least 40 minutes to maintain low blood pressure.
    private static final int LOW_BLOOD_PRESSURE_DAYS_REQUIRED = 3;
    // How many days a week.
    private static final  int DAYS_OF_THE_WEEK = 7;

    public void run() {

        int[] minutesPerDay = readInputData();

        int CALC_AEROBIC_DAYS = 0;
        int CALC_LOW_BLOOD_PRESSURE_DAYS = 0;

        for (int i : minutesPerDay) {
            if (i >= 30) {
                CALC_AEROBIC_DAYS++;
            }
            if (i >= 40) {
                CALC_LOW_BLOOD_PRESSURE_DAYS++;
            }
        }

        System.out.println("Cardio vascular health:");
        if (CALC_AEROBIC_DAYS < AEROBIC_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " + (AEROBIC_DAYS_REQUIRED - CALC_AEROBIC_DAYS) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }

        System.out.println("Blood pressure:");
        if (CALC_LOW_BLOOD_PRESSURE_DAYS < LOW_BLOOD_PRESSURE_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " + (LOW_BLOOD_PRESSURE_DAYS_REQUIRED - CALC_LOW_BLOOD_PRESSURE_DAYS) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }
    }

    //This method reads the input date.
    private int[] readInputData() {
        int[] array = new int[DAYS_OF_THE_WEEK];
        for (int i = 0; i < array.length; i++) {
            print("How many minutes did you do on day" + (i + 1) + "?");
            array[i] = readInt();
        }
        return array;
    }

}