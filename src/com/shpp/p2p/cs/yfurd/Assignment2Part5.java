package com.shpp.p2p.cs.yfurd;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 15;
    private static final int NUM_COLS = 16;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {
        createRow(NUM_COLS);
    }

    private void createRow(int cols) {
        for (int i = 0; i < NUM_COLS; i++) {
            createColm(NUM_ROWS, i);

            GRect gRect = new GRect(BOX_SIZE + i * (BOX_SIZE + BOX_SIZE / 2), BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            gRect.setColor(Color.BLACK);
            gRect.setFilled(true);
            gRect.setFillColor(Color.BLACK);
            add(gRect);
        }
    }

    private void createColm(int ows, int colm) {
        for (int i = 0; i < NUM_ROWS; i++) {

            GRect gRect = new GRect(
                    BOX_SIZE + colm * (BOX_SIZE + BOX_SIZE / 2),
                    (BOX_SIZE / 2) + i * (BOX_SIZE + BOX_SIZE / 2),
                    BOX_SIZE,
                    BOX_SIZE
            );
            gRect.setColor(Color.BLACK);
            gRect.setFilled(true);
            gRect.setFillColor(Color.BLACK);
            add(gRect);

        }
    }
}
