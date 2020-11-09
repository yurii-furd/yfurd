package com.shpp.p2p.cs.yfurd;

/* TODO: Replace these file comments with a description of what your program
 * does.
 */

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part3 extends WindowProgram {

    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height.*/
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height.*/
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* The default width and height of the window. These constants will tell Java to
     * create a window whose size is *approximately* given by these dimensions. You should
     * not directly use these constants in your program; instead, use getWidth() and
     * getHeight(), which return the *exact* width and height of the window.
     */
    public static final int APPLICATION_WIDTH = 270;
    public static final int APPLICATION_HEIGHT = 220;

    public void run() {
        drawPawprint(30, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint. The parameters should specify the upper-left corner of the
     * bounding box containing that pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {

        GOval gOval1 = new GOval(x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        gOval1.setColor(Color.BLACK);
        gOval1.setFilled(true);
        gOval1.setFillColor(Color.BLACK);
        add(gOval1);

        GOval gOval2 = new GOval(x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        gOval2.setColor(Color.BLACK);
        gOval2.setFilled(true);
        gOval2.setFillColor(Color.BLACK);
        add(gOval2);

        GOval gOval3 = new GOval(x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y, TOE_WIDTH, TOE_HEIGHT);
        gOval3.setColor(Color.BLACK);
        gOval3.setFilled(true);
        gOval3.setFillColor(Color.BLACK);
        add(gOval3);

        GOval gOval4 = new GOval(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);
        gOval4.setColor(Color.BLACK);
        gOval4.setFilled(true);
        gOval4.setFillColor(Color.BLACK);
        add(gOval4);

    }
}
