package com.shpp.p2p.cs.yfurd;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 100;
    public static final int APPLICATION_HEIGHT = 100;
    public static final int STRIP_WIDTH = APPLICATION_WIDTH / 5;
    public static final int STRIP_HEIGHT = (APPLICATION_HEIGHT / 5) * 4;

    public void run() {

        GRect gRect = new GRect(APPLICATION_WIDTH / 5, APPLICATION_HEIGHT / 10, STRIP_WIDTH, STRIP_HEIGHT);
        gRect.setFilled(true);
        gRect.setFillColor(Color.BLACK);
        gRect.setColor(Color.BLACK);
        add(gRect);

        GRect gRect2 = new GRect(APPLICATION_WIDTH / 5 + STRIP_WIDTH, APPLICATION_HEIGHT / 10, STRIP_WIDTH, STRIP_HEIGHT);
        gRect2.setFilled(true);
        gRect2.setFillColor(Color.YELLOW);
        gRect2.setColor(Color.YELLOW);
        add(gRect2);

        GRect gRect3 = new GRect(APPLICATION_WIDTH / 5 + STRIP_WIDTH * 2, APPLICATION_HEIGHT / 10, STRIP_WIDTH, STRIP_HEIGHT);
        gRect3.setFilled(true);
        gRect3.setFillColor(Color.RED);
        gRect3.setColor(Color.RED);
        add(gRect3);
    }
}
