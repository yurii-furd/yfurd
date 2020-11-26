package com.shpp.p2p.cs.yfurd.assignment4;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Thread.sleep;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Half the length of the paddle
     */
    private static final int MIDDLE_PADDLE_WIDTH = PADDLE_WIDTH / 2;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 1;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;
    private static final int BALL_DIAMETER = BALL_RADIUS * 2;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;


    //The amount of time to pause between frames (50fps)
    public double PAUSE_TIME = 20;

    public static final Color ORANGE = new Color(255, 165, 0);

    private double vx = 0;
    private double vy = 3.0;

    GRect paddle = null;
    GOval boll = null;
    ArrayList<GRect> bricks = new ArrayList<>();

    //this variable counts the bricks that knocked down...
    int calcDeleteBricks = 0;
    //this variable decrements after the cell has fallen outside the window
    int calcNTurnsLabelDecrement = 3;

    public void run() {
        //This command to display the program window correctly(Linux).
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addMouseListeners();
        // create paddle
        paddle = createRectangle();
        // create boll
        boll = createBoll();
        // create bricks
        createGridOfBricks();

        startGames();
    }

    private void startGames() {

        GLabel label1 = createLabel("Tap to start. You have " + calcNTurnsLabelDecrement + " attempts");
        waitForClick();
        remove(label1);

        randomGenerator();

        createAnimation();
    }

    private void createAnimation() {

        while (true) {

            boll.move(vx, vy);

            if (boll.getX() < 0) {
                vx *= -1;
            }

            if (boll.getX() + BALL_RADIUS * 2 > getWidth()) {
                vx *= -1;
            }

            if (boll.getY() < 0) {
                vy *= -1;
            }

            GObject collider;
            if (boll.getY() <= paddle.getY() - BALL_DIAMETER + 1) {
                collider = getCollidingObject(boll);

                if (collider == paddle) {
                    vy *= -1;
                }

            } else {
                collider = null;
            }

            int calcNTurns = 0;
            if (calcDeleteBricks < NBRICKS_PER_ROW * NBRICK_ROWS) {
                if (boll.getY() > getHeight()) {
                    calcNTurnsLabelDecrement--;
                    calcNTurns++;
                    if (NTURNS > calcNTurns) {
                        remove(boll);
                        boll = createBoll();
                        startGames();
                    } else {
                        remove(boll);
                        GLabel label2 = createLabel("Game over, unfortunately you lost, try again");
                        break;
                    }
                }
            } else {
                GLabel label3 = createLabel("Game over, congratulations you won the game");
                remove(boll);
                break;
            }

            Iterator<GRect> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                GRect brick = iterator.next();
                if (collider == brick) {
                    iterator.remove();
                    remove(brick);
                    calcDeleteBricks++;
                    vy *= -1;
                }
            }

            pause(PAUSE_TIME);
        }

    }

    private GObject getCollidingObject(GOval boll) {
        ArrayList<GObject> gObjects = new ArrayList<>();

        gObjects.add(getElementAt(boll.getX(), boll.getY()));
        gObjects.add(getElementAt(boll.getX() + BALL_DIAMETER, boll.getY()));
        gObjects.add(getElementAt(boll.getX(), boll.getY() + BALL_DIAMETER));
        gObjects.add(getElementAt(boll.getX() + BALL_DIAMETER, boll.getY() + BALL_DIAMETER));

        for (GObject o : gObjects) {
            if (o != null) {
                return o;
            }
        }
        return null;
    }

    //This method creates a matrix.
    private void createGridOfBricks() {
        int coordinateY = BRICK_Y_OFFSET;
        coordinateY = createTwoRows(coordinateY, Color.RED);
        coordinateY = createTwoRows(coordinateY, ORANGE);
        coordinateY = createTwoRows(coordinateY, Color.YELLOW);
        coordinateY = createTwoRows(coordinateY, Color.GREEN);
        createTwoRows(coordinateY, Color.BLUE);
    }

    //This method creates two rows of the matrix.
    private int createTwoRows(int coordinateY, Color color) {

        //this loop is iterated on lines
        for (int i = 0; i < 2; i++) {
            coordinateY += i * (BRICK_HEIGHT + BRICK_SEP);
            //this loop creates bricks in a row
            for (int j = 0; j < NBRICKS_PER_ROW; j++) {
                //here we find the first X coordinate
                int firstCoordinateX = (getWidth() - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP)) / 2;
                //create a brick and add it to the collection.
                bricks.add(createBrick(firstCoordinateX + j * (BRICK_WIDTH + BRICK_SEP), coordinateY, color));
            }
        }
        return coordinateY + BRICK_HEIGHT + BRICK_SEP;
    }

    //This method binds the mouse to the paddle, and does not allow the paddle to go outside the window
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getX() <= MIDDLE_PADDLE_WIDTH || mouseEvent.getX() >= getWidth() - MIDDLE_PADDLE_WIDTH) {

            if (mouseEvent.getX() <= MIDDLE_PADDLE_WIDTH) {
                paddle.setLocation(0, paddle.getY());
            } else {
                paddle.setLocation(getWidth() - PADDLE_WIDTH, paddle.getY());
            }

        } else {
            paddle.setLocation(mouseEvent.getX() - MIDDLE_PADDLE_WIDTH, paddle.getY());
        }
    }

    private GLabel createLabel(String text) {
        GLabel label = new GLabel(text, 0, 0);
        label.setColor(Color.BLACK);
        label.setLocation((getWidth() - label.getWidth()) / 2, (getHeight() - label.getHeight()) / 2.5);
        add(label);
        return label;
    }

    // create boll and add boll
    private GOval createBoll() {
        GOval boll = new GOval(
                getWidth() / 2 - BALL_RADIUS,
                getHeight() / 2 - BALL_RADIUS,
                BALL_DIAMETER,
                BALL_DIAMETER);
        boll.setFilled(true);
        boll.setColor(Color.BLACK);
        add(boll);
        return boll;
    }

    //create a brick and add.
    private GRect createBrick(int x, int y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);
        add(brick);
        return brick;
    }

    //Create and add paddle
    private GRect createRectangle() {
        paddle = new GRect(
                getWidth() / 2 - MIDDLE_PADDLE_WIDTH,
                getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET,
                PADDLE_WIDTH,
                PADDLE_HEIGHT
        );
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLACK);
        add(paddle);
        return paddle;
    }

    private void randomGenerator() {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        vx = randomGenerator.nextDouble(1.0, 3.0);
        if (randomGenerator.nextBoolean(0.5))
            vx = -vx;
    }
}