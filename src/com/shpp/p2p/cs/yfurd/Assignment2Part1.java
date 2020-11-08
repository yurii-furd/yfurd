package com.shpp.p2p.cs.yfurd;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    public void run() {
        println("Please enter a:");
        double a = readInt();
        print("Please enter b:");
        double b = readInt();
        print("Please enter c:");
        double c = readInt();

        //calculate the discriminant.
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            println("There are no real roots");
        // if discriminant more than zero, he calculate the rots
        } else {
            double x1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double x2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            if (x1 == x2) {
                System.out.println("There is one root: " + x1);
            } else {
                System.out.println("There are two roots: " + x1 + " and " + x2);
            }
        }
    }
}