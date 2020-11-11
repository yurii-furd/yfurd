package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    //Caterpillar segments with this diameter.
    private static final double CATERPILLAR_SEGMENTS = 180;

    //Constants that control the location of the first segment of the caterpillar in the lower position.
    private static final double X_ONE = 0;
    private static final double Y_ONE = 60;

    //Constants that control the location of the second segment of the caterpillar in the lower position.
    private static final double X_TWO = 110;
    private static final double Y_TWO = 0;

    //Here you can set how many segments the caterpillar will have.
    private static final double SEGMENTS = 7;


    public void run() {

        for (int i = 0; i < SEGMENTS; i++) {

            double x = i * X_TWO;
            double y = selectY(i);

            createOval(x, y);
        }

    }
    //depending on the value of "i" in the cycle, this method returns the value of the first or second line.
    private double selectY(int i) {
        if(i%2 == 0){
            return Y_ONE;
        } else {
            return Y_TWO;
        }
    }

    // Create an oval.
    private void createOval(double x, double y) {
        GOval gOval = new GOval(x, y, CATERPILLAR_SEGMENTS, CATERPILLAR_SEGMENTS);
        gOval.setColor(Color.RED);
        gOval.setFilled(true);
        gOval.setFillColor(Color.GREEN);
        add(gOval);
    }
}
