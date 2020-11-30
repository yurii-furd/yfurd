package com.shpp.p2p.cs.yfurd.assignment3;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

import static java.lang.Thread.sleep;

public class Assignment3Part6 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 760;
    public static final int APPLICATION_HEIGHT = 800;

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
        //This command to display the program window correctly (Linux).
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

        //create hole
        createOval(coordinateHoleX, coordinateHoleY, DIAMETER_HOLE, Color.BLACK);
        //create boll
        GOval boll = createOval(coordinateBollXAndY, coordinateBollXAndY, DIAMETER_BOOL, Color.WHITE);
        //create flag
        createFlag(coordinateHoleX, coordinateHoleY);
        //create animation
        createAnimation(coordinateBollXAndY, boll);
    }

    private void createAnimation(int coordinateBollXAndY, GOval boll) {
        while (coordinateBollXAndY++ < 321) {
            boll.move(2.5, 2.5);
            pause(PAUSE_TIME);
        }
    }

    /**
     * This method creates a flag.
     * @param coordinateHoleX using this coordinate, the coordinate of the beginning
     *                       of the drawing of the flagpole is calculated
     * @param coordinateHoleY using this coordinate, the coordinate of the beginning
     *                        of the drawing of the flagpole is calculated
     */
    private void createFlag(int coordinateHoleX, int coordinateHoleY) {
        // create a stick for the flag
        createRectangle(
                coordinateHoleX + DIAMETER_HOLE,
                coordinateHoleY - HEIGHT_RECTANGLE,
                WIDTH_RECTANGLE,
                HEIGHT_RECTANGLE,
                BROWN);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                createRectangle(
                        coordinateHoleX + DIAMETER_HOLE - WIDTH_AND_HEIGHT_SQUARE_FLAG * 4 + i * WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        coordinateHoleY - HEIGHT_RECTANGLE + j * WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        WIDTH_AND_HEIGHT_SQUARE_FLAG,
                        selectColor(i + j));
            }
        }
    }

    /*Depending on which number comes to the input of the method,
    The method returns either white or black*/
    private Color selectColor(int i) {

        return i % 2 == 0 ? Color.WHITE : Color.BLACK;
    }

    /*this method takes coordinates, latitude, longitude, and color
    creates and adds rectangle*/
    private void createRectangle(int x, int y, int width, int height, Color color) {
        GRect rect = new GRect(x, y, width, height);
        rect.setFilled(true);
        rect.setFillColor(color);
        add(rect);
    }

    /*this method takes coordinates, diameter and color
    creates and adds a circle*/
    private GOval createOval(int x, int y, int diameter, Color color) {
        GOval oval = new GOval(x, y, diameter, diameter);
        oval.setFilled(true);
        oval.setFillColor(color);
        add(oval);
        return oval;
    }
}
