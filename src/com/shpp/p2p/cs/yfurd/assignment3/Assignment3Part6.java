package com.shpp.p2p.cs.yfurd.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment3Part6 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 740;

    //The amount of time to pause between frames (50fps)
    public static final int PAUSE_TIME = 1000 / 50;

    //The size of the ball and the hole
    public static final int DIAMETER_HOLE = 60;
    public static final int DIAMETER_BOOL = 50;

    //Length and width of the stick on the flag
    public static final int WIDTH_RECTANGLE = 10;
    public static final int HEIGHT_RECTANGLE = 300;

    private static final Color BROWN = new Color(101, 67, 33);

    //the size of the side of the square on the flag
    public static final int WIDTH_AND_HEIGHT_SQUARE_FLAG = 20;


    public void run() {
        //This command to display the program window correctly.
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //This method draws the program window
        setBackground(Color.GREEN);

        //coordinates X and Y hole.
        int coordinateHoleX = getWidth() - 2 * DIAMETER_HOLE;
        int coordinateHoleY = getHeight() - 2 * DIAMETER_HOLE;
        //coordinates X and Y boll.
        int coordinateBollXAndY = DIAMETER_BOOL * 2;

        createOval(coordinateHoleX, coordinateHoleY, DIAMETER_HOLE, Color.BLACK);

        GOval oval = createOval(coordinateBollXAndY, coordinateBollXAndY, DIAMETER_BOOL, Color.WHITE);

        createRectangle(coordinateHoleX + DIAMETER_HOLE, coordinateHoleY - HEIGHT_RECTANGLE, WIDTH_RECTANGLE, HEIGHT_RECTANGLE, BROWN);

        createFlag(coordinateHoleX, coordinateHoleY);

        while (coordinateBollXAndY++ <= coordinateHoleX / 2 + (DIAMETER_HOLE - DIAMETER_BOOL) / 4) {
            oval.move(2.5, 2.5);
            pause(PAUSE_TIME);
        }
    }

    //This method creates a flag.
    private void createFlag(int coordinateX, int coordinateY) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                createRectangle(coordinateX + DIAMETER_HOLE - WIDTH_AND_HEIGHT_SQUARE_FLAG * 4 + i * WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        coordinateY - HEIGHT_RECTANGLE + j * WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        selectColor(i + j));
            }
        }
    }

    //Depending on which number comes to the input of the method,
    //The method returns either white or black
    private Color selectColor(int i) {
        return i % 2 == 0 ? Color.WHITE : Color.BLACK;
    }

    //this method takes coordinates, latitude, longitude, and color
    //creates and adds rectangle
    private void createRectangle(int x, int y, int width, int height, Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setFilled(true);
        rect.setFillColor(color);
        add(rect);
    }

    //this method takes coordinates, diameter and color
    //creates and adds a circle
    private GOval createOval(int x, int y, int diameter, Color color) {
        GOval oval = new GOval(x, y, diameter, diameter);
        oval.setFilled(true);
        oval.setFillColor(color);
        add(oval);
        return oval;
    }
}
