package com.shpp.p2p.cs.yfurd.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;


public class Assignment3Part4 extends WindowProgram {

    /*These directives allow you to configure the initial approximate size of programs,
    which does not change with getWidth () and getHeight () during its operation.*/
    public static final int APPLICATION_WIDTH = 2000;
    public static final int APPLICATION_HEIGHT = 900;

    //The height of the brick.
    public static final int BRICK_HEIGHT = 25;
    //The width of the brick.
    public static final int BRICK_WIDTH = 100;
    //How many bricks in the base.
    public static final int BRICKS_IN_BASE = 17;

    public void run() {
        //This command to display the program window correctly.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buildsPyramid();
    }

    private void buildsPyramid() {

        //How many bricks in the base.
        int tempBricksNextRow = BRICKS_IN_BASE;

        //With each iteration of the cycle the line of the pyramid changes.
        for (int i = 0; i < BRICKS_IN_BASE; i++) {

            //Coordinates X and Y of the beginning of each line of the pyramid
            //The coordinates are changed by a cycle
            int coordinateOfTheFirstBrickX = ((getWidth() - BRICKS_IN_BASE * BRICK_WIDTH) / 2) + i * (BRICK_WIDTH / 2);
            int coordinateOfTheFirstBrickY = (getHeight() - BRICK_HEIGHT) - i * (BRICK_HEIGHT);

            //This loop builds the row of the pyramid.
            for (int j = 0; j < tempBricksNextRow; j++) {
                createRect(coordinateOfTheFirstBrickX + j * BRICK_WIDTH, coordinateOfTheFirstBrickY);

            }
            //This code allows you to build one brick less in each row of the pyramid.
            tempBricksNextRow = tempBricksNextRow - 1;
        }
    }

    //This method create rect.
    private void createRect(int x, int y) {
        GRect gRect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        gRect.setColor(Color.BLACK);
        gRect.setFilled(true);
        gRect.setFillColor(new Color(160, 54, 35));
        add(gRect);
    }
}
