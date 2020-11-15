package com.shpp.p2p.cs.yfurd.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    public void run() {
        print("Please enter a:");
        double a = readDouble();
        print("Please enter b:");
        double b = readDouble();
        print("Please enter c:");
        double c = readDouble();

        // If a = 0 the equation is not quadratic but linear.
        if (a == 0) {
            System.out.println("This is not a quadratic equation");
        } else {
            //calculate the discriminant.
            double discriminant = b * b - 4 * a * c;

            if (discriminant < 0) {
                println("There are no real roots");
                // if discriminant more than null, he calculate the rots
            } else {
                double x1 = normalizeNegativeZero((-b - Math.sqrt(discriminant)) / (2 * a));
                double x2 = normalizeNegativeZero((-b + Math.sqrt(discriminant)) / (2 * a));
                if (x1 == x2) {
                    System.out.println("There is one root: " + x1);
                } else {
                    System.out.println("There are two roots: " + x1 + " and " + x2);
                }
            }
        }
    }

    //Check that the root 0 is displayed normally.
    private double normalizeNegativeZero(double x) {
        return x == -0.0 ? Math.abs(x) : x;
    }
}


