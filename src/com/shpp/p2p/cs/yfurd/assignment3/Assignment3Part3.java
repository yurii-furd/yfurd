package com.shpp.p2p.cs.yfurd.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {

    public void run() {

        System.out.print("Base = ");
        double number = readDouble();
        System.out.print("Exponent = ");
        int exponent = readInt();

        double res = raiseToPower(number, exponent);
        System.out.println("Number " + number + " elevated to the degree " + exponent + " = " + res);

    }

    //This method raises any number to any power.
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent < 0) {
            int positiveExponent = toPositive(exponent);
            return 1 / toPowerOf(base, positiveExponent);
        } else {
            return toPowerOf(base, exponent);
        }
    }

    //This method converts a number from negative to positive.
    private int toPositive(int exponent) {
        return exponent < 0 ? -exponent : exponent;
    }

    //This method raises any number to a positive power.
    private double toPowerOf(double base, int exponent) {
        double value = base;
        for (int i = 1; i < exponent; i++) {
            value = value * base;
        }
        return value;
    }
}