package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment2Part4 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 1000;
    public static final int STRIP_WIDTH = 100;
    public static final int STRIP_HEIGHT = 300;


    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int middleX = getWidth()/2 - STRIP_WIDTH/2;
        int middleY = getHeight()/2 - STRIP_HEIGHT/2;

        GRect gRect = new GRect(middleX, middleY, STRIP_WIDTH, STRIP_HEIGHT);
        gRect.setFilled(true);
        gRect.setFillColor(Color.YELLOW);
        gRect.setColor(Color.YELLOW);
        add(gRect);

        GRect gRect2 = new GRect(middleX - STRIP_WIDTH, middleY, STRIP_WIDTH, STRIP_HEIGHT);
        gRect2.setFilled(true);
        gRect2.setFillColor(Color.BLACK);
        gRect2.setColor(Color.BLACK);
        add(gRect2);

        GRect gRect3 = new GRect(middleX + STRIP_WIDTH, middleY, STRIP_WIDTH, STRIP_HEIGHT);
        gRect3.setFilled(true);
        gRect3.setFillColor(Color.RED);
        gRect3.setColor(Color.RED);
        add(gRect3);
    }
}
