package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;
    private static final double DIAMETER = 200;

    public void run() {
        //This command to display the program window correctly.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        createOval(getWidth() - DIAMETER, getHeight() - DIAMETER);
        createOval(getWidth() - DIAMETER, 0);
        createOval(0, getHeight() - DIAMETER);
        createOval(0, 0);


        // Create a rectangle
        GRect gRect = new GRect(DIAMETER / 2, DIAMETER / 2, getWidth() - DIAMETER, getHeight() - DIAMETER);
        gRect.setColor(Color.WHITE);
        gRect.setFilled(true);
        gRect.setFillColor(Color.WHITE);
        add(gRect);
    }

    // This method create oval.
    private void createOval(double x, double y) {
        GOval gOval = new GOval(x, y, DIAMETER, DIAMETER);
        gOval.setColor(Color.BLACK);
        gOval.setFilled(true);
        gOval.setFillColor(Color.BLACK);
        add(gOval);
    }
}


