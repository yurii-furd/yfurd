package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    public void run() {
        double res = raiseToPower(-5.5, 0);

    }

    //This method raises any number to any power.
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            System.out.println("Any number in 0 power is equal to 1");
            return 1;
        }
        if (exponent < 0) {
            int conversion = conversionFromNegativeToPositive(exponent);
            double findTheDegree = elevatedToTheDegree(base, conversion);
            double count = (1 / findTheDegree);
            System.out.println("Number " + base + " elevated to the degree " + exponent + " = " + count);
            return count;
        } else {
            double count = elevatedToTheDegree(base, exponent);
            System.out.println("Number " + base + " elevated to the degree " + exponent + " = " + count);
            return count;
        }
    }

    //This method converts a number from negative to positive.
    private int conversionFromNegativeToPositive(int exponent) {
        return exponent < 0 ? exponent * (-1) : exponent;
    }

    //This method raises any number to a positive power.
    private double elevatedToTheDegree(double base, int exponent) {
        double count = base;
        for (int i = 1; i < exponent; i++) {
            count = count * base;
        }
        return count;
    }
}