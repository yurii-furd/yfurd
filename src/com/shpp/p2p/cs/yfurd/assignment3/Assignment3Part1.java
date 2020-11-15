package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    public void run() {
        print("How many minutes did you do on day 1?");
        int day1 = readInt();
        print("How many minutes did you do on day 2?");
        int day2 = readInt();
        print("How many minutes did you do on day 3?");
        int day3 = readInt();
        print("How many minutes did you do on day 4?");
        int day4 = readInt();
        print("How many minutes did you do on day 5?");
        int day5 = readInt();
        print("How many minutes did you do on day 6?");
        int day6 = readInt();
        print("How many minutes did you do on day 7?");
        int day7 = readInt();

        checkEveryDayFor30Minutes(day1, day2, day3, day4, day5, day6, day7);
        checkEveryDayFor40Minutes(day1, day2, day3, day4, day5, day6, day7);
    }

    private void checkEveryDayFor30Minutes(int day1, int day2, int day3, int day4, int day5, int day6, int day7) {

        int res1 = check30Minutes(day1);
        int res2 = check30Minutes(day2);
        int res3 = check30Minutes(day3);
        int res4 = check30Minutes(day4);
        int res5 = check30Minutes(day5);
        int res6 = check30Minutes(day6);
        int res7 = check30Minutes(day7);
        int count = res1 + res2 + res3 + res4 + res5 + res6 + res7;

        System.out.println("Cardiovacular health:");
        switch (count) {
            case 0:
                System.out.println("You needed to train hard for at least 5 more day(s) a week");
                break;
            case 1:
                System.out.println("You needed to train hard for at least 4 more day(s) a week");
                break;
            case 2:
                System.out.println("You needed to train hard for at least 3 more day(s) a week");
                break;
            case 3:
                System.out.println("You needed to train hard for at least 2 more day(s) a week");
                break;
            case 4:
                System.out.println("You needed to train hard for at least 1 more day(s) a week");
                break;
            default:
                System.out.println("Great job! You've done enough exercise for cardiovacular health.");
        }
    }

    private int check30Minutes(int day) {
        return day >= 30 ? 1 : 0;
    }

    private void checkEveryDayFor40Minutes(int day1, int day2, int day3, int day4, int day5, int day6, int day7) {

        int res1 = check40Minutes(day1);
        int res2 = check40Minutes(day2);
        int res3 = check40Minutes(day3);
        int res4 = check40Minutes(day4);
        int res5 = check40Minutes(day5);
        int res6 = check40Minutes(day6);
        int res7 = check40Minutes(day7);
        int count = res1 + res2 + res3 + res4 + res5 + res6 + res7;

        System.out.println("Blood pressure:");
        switch (count) {
            case 0:
                System.out.println("You needed to train hard for at least 5 more day(s) a week!");
                break;
            case 1:
                System.out.println("You needed to train hard for at least 4 more day(s) a week");
                break;
            case 2:
                System.out.println("You needed to train hard for at least 3 more day(s) a week");
                break;
            default:
                System.out.println("Great job! You've done enough exercise to keep a low blood pressure.");
        }
    }

    private int check40Minutes(int day) {
        return day >= 40 ? 1 : 0;
    }
}
