package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    //Caterpillar segments with this diameter.
    private static final double CATERPILLAR_SEGMENTS_DIAMETER = 180;

    //Constants that control the location of the first segment of the caterpillar in the lower position.
    private static final double X_FIRST = 0;
    private static final double Y_FIRST = 60;

    //Constants that control the location of the second segment of the caterpillar in the upper position.
    private static final double X_SECOND = 110;
    private static final double Y_SECOND = 0;

    /*These directives allow you to configure the initial approximate size of programs,
    which does not change with getWidth () and getHeight () during its operation.*/
    public static final int APPLICATION_WIDTH = 800;
    public static final int APPLICATION_HEIGHT = 800;

    //Here you can set how many segments the caterpillar will have.
    private static final double SEGMENTS = 5;


    public void run() {

        for (int i = 0; i < SEGMENTS; i++) {

            double coordinateX = i * X_SECOND;
            double coordinateY = selectY(i);

            createAndAddOval(coordinateX, coordinateY);
        }
    }

    //depending on the value of "i" in the cycle, this method returns the value of the first or second line.
    private double selectY(int i) {
        return i % 2 == 0 ? Y_FIRST : Y_SECOND;
    }

    // Create an oval.
    private void createAndAddOval(double x, double y) {
        GOval gOval = new GOval(x, y, CATERPILLAR_SEGMENTS_DIAMETER, CATERPILLAR_SEGMENTS_DIAMETER);
        gOval.setColor(Color.RED);
        gOval.setFilled(true);
        gOval.setFillColor(Color.GREEN);
        add(gOval);
    }
}