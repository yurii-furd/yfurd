package com.shpp.p2p.cs.yfurd;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment2Part2 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 500;
    private static final int DIAMETER = 100;

    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        GOval gOval = new GOval(getWidth() - DIAMETER, getHeight() - DIAMETER, DIAMETER, DIAMETER);
        gOval.setColor(Color.BLACK);
        gOval.setFilled(true);
        gOval.setFillColor(Color.BLACK);
        add(gOval);


        GOval gOval2 = new GOval(0, getHeight() - DIAMETER, DIAMETER, DIAMETER);
        gOval2.setColor(Color.BLACK);
        gOval2.setFilled(true);
        gOval2.setFillColor(Color.BLACK);
        add(gOval2);

        GOval gOval3 = new GOval(getWidth() - DIAMETER, 0, DIAMETER, DIAMETER);
        gOval3.setColor(Color.BLACK);
        gOval3.setFilled(true);
        gOval3.setFillColor(Color.BLACK);
        add(gOval3);

        GOval gOval4 = new GOval(0, 0, DIAMETER, DIAMETER);
        gOval4.setColor(Color.BLACK);
        gOval4.setFilled(true);
        gOval4.setFillColor(Color.BLACK);
        add(gOval4);

        GRect gRect = new GRect(DIAMETER / 2, DIAMETER / 2, getWidth() - DIAMETER, getHeight() - DIAMETER);
        gRect.setColor(Color.WHITE);
        gRect.setFilled(true);
        gRect.setFillColor(Color.WHITE);
        add(gRect);
    }
}


