package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    //The amount of days on which doctors recommend 30 minutes of aerobics.
    private static final int AEROBIC_DAYS_REQUIRED = 5;
    //The amount of days that doctors recommend at least 40 minutes to maintain low blood pressure.
    private static final int LOW_BLOOD_PRESSURE_DAYS_REQUIRED = 3;

    //The minimum number of minutes recommended by a doctor to do aerobics.
    private static final int MIN_MINUTES_FOR_AEROBIC_DAYS_REQUIRED = 30;
    //The minimum number of minutes recommended by a doctor, to maintain low blood pressure
    private static final int MIN_MINUTES_FOR_LOW_BLOOD_PRESSURE_DAYS_REQUIRED = 40;

    //How many days a week.
    private static final int DAYS_OF_THE_WEEK = 7;
    //How mani minutes a day.
    private static final int DAY_HAS_MINUTES = 1440;

    public void run() {

        int[] minutesPerDay = readInputData();

        int calcAerobicDays = 0;
        int calcLowBloodPressureDays = 0;

        for (int minutes : minutesPerDay) {
            if (minutes >= MIN_MINUTES_FOR_AEROBIC_DAYS_REQUIRED) {
                calcAerobicDays++;
            }
            if (minutes >= MIN_MINUTES_FOR_LOW_BLOOD_PRESSURE_DAYS_REQUIRED) {
                calcLowBloodPressureDays++;
            }
        }

        System.out.println("Cardio vascular health:");
        if (calcAerobicDays < AEROBIC_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " + (AEROBIC_DAYS_REQUIRED - calcAerobicDays) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }

        System.out.println("Blood pressure:");
        if (calcLowBloodPressureDays < LOW_BLOOD_PRESSURE_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " + (LOW_BLOOD_PRESSURE_DAYS_REQUIRED - calcLowBloodPressureDays) + " more day(s) a week!");
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

            while (array[i] < 0 || array[i] > DAY_HAS_MINUTES) {
                print("Wrong answer. How many minutes did you do on day" + (i + 1) + "?");
                array[i] = readInt();
            }
        }
        return array;
    }

}