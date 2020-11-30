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

    //How many days in a week.
    private static final int DAYS_IN_THE_WEEK = 7;
    //How many minutes in a day.
    private static final int DAY_HAS_MINUTES = 24 * 60;

    //these constants count the days of the minimum allowable exercise
    int calcAerobicDays;
    int calcLowBloodPressureDays;

    public void run() {

        int[] minutesPerDay = readInputData();
        calculateInputData(minutesPerDay);
        outputInformation();

    }

    //This method displays the result of the program in the console.
    private void outputInformation() {
        System.out.println("Cardio vascular health:");
        if (calcAerobicDays < AEROBIC_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " +
                    (AEROBIC_DAYS_REQUIRED - calcAerobicDays) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }

        System.out.println("Blood pressure:");
        if (calcLowBloodPressureDays < LOW_BLOOD_PRESSURE_DAYS_REQUIRED) {
            System.out.println("You needed to train hard for at least " +
                    (LOW_BLOOD_PRESSURE_DAYS_REQUIRED - calcLowBloodPressureDays) + " more day(s) a week!");
        } else {
            System.out.println("Great job! You've done enough exercise for cardio vascular health.");
        }
    }

    /**
     * This method takes the input data and compares it to a constant time,
     * writing anything more than that time in two variables (calcAerobicDays, calcLowBloodPressureDays)
     *
     * @param array it is an archive that contains data in minutes, every day of the week.
     */
    private void calculateInputData(int[] array) {
        calcAerobicDays = 0;
        calcLowBloodPressureDays = 0;

        for (int minutes : array) {
            if (minutes >= MIN_MINUTES_FOR_AEROBIC_DAYS_REQUIRED) {
                calcAerobicDays++;
            }
            if (minutes >= MIN_MINUTES_FOR_LOW_BLOOD_PRESSURE_DAYS_REQUIRED) {
                calcLowBloodPressureDays++;
            }
        }
    }

    //This method reads the input date.
    private int[] readInputData() {
        int[] array = new int[DAYS_IN_THE_WEEK];

        for (int i = 0; i < array.length; i++) {
            println("How many minutes did you do on day " + (i + 1) + "?");
            array[i] = readInt();

            while (array[i] < 0 || array[i] > DAY_HAS_MINUTES) {
                print("Wrong answer. How many minutes did you do on day " + (i + 1) + "?");
                array[i] = readInt();
            }
        }
        return array;
    }
}