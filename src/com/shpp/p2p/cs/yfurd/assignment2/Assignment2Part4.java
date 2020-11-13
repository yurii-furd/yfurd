package com.shpp.p2p.cs.yfurd.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment2Part4 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;
    public static final int STRIP_WIDTH = 100;
    public static final int STRIP_HEIGHT = 300;


    public void run() {
        //This command to display the program window correctly.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Calculating the coordinates X and Y of the middle strip
        int middleX = getWidth() / 2 - STRIP_WIDTH / 2;
        int middleY = getHeight() / 2 - STRIP_HEIGHT / 2;

        createRect(middleX, middleY, Color.YELLOW);
        createRect(middleX - STRIP_WIDTH, middleY, Color.BLACK);
        createRect(middleX + STRIP_WIDTH, middleY, Color.RED);

        //Create a caption.
        GLabel gLabel = new GLabel("Flag of Belgium");
        gLabel.setColor(Color.BLACK);
        gLabel.setLocation(getWidth() - gLabel.getWidth(), getHeight() - gLabel.getHeight()/2);
        add(gLabel);
    }

    //Create rect.
    private void createRect(int x, int y, Color color) {
        GRect gRect = new GRect(x, y, STRIP_WIDTH, STRIP_HEIGHT);
        gRect.setFilled(true);
        gRect.setFillColor(color);
        gRect.setColor(color);
        add(gRect);
    }
}
