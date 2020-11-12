package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 7;
    private static final int NUM_COLS = 10;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {
        //This command to display the program window correctly.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Calculation of the sum of boxes and streets on X.
        double sumBoxAndStreetX = (NUM_COLS * BOX_SIZE) + (NUM_COLS * BOX_SPACING) - BOX_SPACING;

        //Found the X coordinate of the first box.
        double firstElementX = (getWidth() - sumBoxAndStreetX) / 2;

        //Calculation of the sum of boxes and streets on Y.
        double sumBoxAndStreetY = (NUM_ROWS * BOX_SIZE + NUM_ROWS * BOX_SPACING) - BOX_SPACING;

        //Found the Y coordinate of the first box.
        double firstElementY = (getHeight() - sumBoxAndStreetY) / 2;

        createRow(firstElementX, firstElementY);
    }

    //This method creates a single row with boxes.
    private void createRow(double firstElementX, double firstElementY) {

        for (int i = 0; i < NUM_COLS; i++) {

            //Initial value coordinateXElementN = firstElementX.
            //At each iteration of the cycle the coordinate X is dropped to the right
            // by the distance of one box + spacing by boxes.
            double coordinateXElementN = firstElementX + i * (BOX_SIZE + BOX_SPACING);

            createBox(coordinateXElementN, firstElementY);

            for (int j = 0; j < NUM_ROWS; j++) {

                //Initial value coordinateYElementN = firstElementY.
                //On each iteration of the cycle the coordinate Y falls down
                // by a distance of one field + spacing by boxes.
                double coordinateYElementN = firstElementY + j * (BOX_SIZE + BOX_SPACING);

                createBox(coordinateXElementN, coordinateYElementN);
            }
        }
    }

    // Create a rectangle
    private void createBox(double x, double y) {
        GRect gRect = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        gRect.setColor(Color.BLACK);
        gRect.setFilled(true);
        gRect.setFillColor(Color.BLACK);
        add(gRect);

    }
}

