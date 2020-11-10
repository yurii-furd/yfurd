package com.shpp.p2p.cs.yfurd;

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
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double sumBoxAndStreetX = (NUM_COLS * BOX_SIZE) + (NUM_COLS * BOX_SPACING) - BOX_SPACING;
        double firstElementX = (getWidth() - sumBoxAndStreetX) / 2;

        double sumBoxAndStreetY = (NUM_ROWS * BOX_SIZE + NUM_ROWS * BOX_SPACING) - BOX_SPACING;
        double firstElementY = (getHeight()- sumBoxAndStreetY) / 2;

        System.out.println(getHeight());

        createRow(NUM_COLS, firstElementX, firstElementY);
    }

    private void createRow(int cols, double firstElementX, double firstElementY) {
        for (int i = 0; i < NUM_COLS; i++) {
            createColm(NUM_ROWS, i, firstElementX, firstElementY);

            GRect gRect = new GRect(firstElementX + i * (BOX_SIZE + BOX_SPACING),  firstElementY, BOX_SIZE, BOX_SIZE);
            gRect.setColor(Color.BLACK);
            gRect.setFilled(true);
            gRect.setFillColor(Color.BLACK);
            add(gRect);
        }
    }

    private void createColm(int ows, int colm, double firstElementX, double firstElementY) {
        for (int i = 0; i < NUM_ROWS; i++) {

            GRect gRect = new GRect(
                     firstElementX+ colm * (BOX_SIZE + BOX_SPACING),
                    firstElementY + i * (BOX_SIZE + BOX_SPACING),
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

/*Ну і ось чергова ілюзія.

Необхідно намалювати матрицю із чорних боксів, розділених “вулицями”.

Вам може здатись, що на перехрестях є крапочки, але їх там немає. Не забудьте відцентрувати малюнок!*/
