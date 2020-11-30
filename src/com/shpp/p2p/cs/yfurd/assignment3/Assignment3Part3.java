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

    /**
     * This method is universal, it raises any number to any degree
     * @param base input number
     * @param exponent input power
     * @return the number is raised to the power
     */
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (exponent < 0) {
            return 1 / toPowerOf(base, -exponent);
        } else {
            return toPowerOf(base, exponent);
        }
    }

    /**
     * This method is auxiliary, raises any number to a positive power.
     * @param base input number
     * @param exponent  input power
     */
    private double toPowerOf(double base, int exponent) {
        double value = base;
        for (int i = 1; i < exponent; i++) {
            value = value * base;
        }
        return value;
    }
}