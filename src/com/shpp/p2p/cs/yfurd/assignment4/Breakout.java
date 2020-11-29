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
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;////////////////////////////////////////////////////////

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
    public double PAUSE_TIME = 1000/50;

    public static final Color ORANGE = new Color(255, 165, 0);

    private double vx = 0;
    private double vy = 3.0;

    GRect rackets = null;
    GOval boll = null;
    ArrayList<GRect> bricks = new ArrayList<>();

    //this variable counts the bricks that knocked down...
    int calcDeleteBricks = 0;
    //this variable decrements after the cell has fallen outside the window
    int calcNTurnsLabelDecrement = 3;
    //this variable counts the number of attempts by the player
    int calcNTurns = 0;

    public void run() {
        //This command to display the program window correctly(Linux).
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addMouseListeners();
        // create paddle
        rackets = createRectangle();
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

        GObject collider;

        while (true) {

            boll.move(vx, vy);

            //If the ball reaches the left wall, then we direct it to another
            if (boll.getX() < 0) {
                vx *= -1;
            }

            //If the ball reaches the right wall, then we direct it to another
            if (boll.getX() + BALL_RADIUS * 2 > getWidth()) {
                vx *= -1;
            }

            //If the ball hits the top of the window, direct it down
            if (boll.getY() < 0) {
                vy *= -1;
            }

            /*if the coordinate of the ball Y + 2R -1 is greater than the coordinate of the racket,
            then read through the "getElementAt ()" coordinates*/
            if (boll.getY() + BALL_DIAMETER - 1 <= rackets.getY()) {
                collider = getCollidingObject(boll);

                /*if the function returned the graphic object that the bullet collided with,
                and it is equal to the racket, then bounce off it in the other direction*/
                if (collider == rackets) {
                    vy *= -1;
                }
            /*if the coordinate of the ball Y + 2R -1 is less than the coordinate of the racket,
            then read through "getElementAt ()" is not required*/
            } else {
                collider = null;
            }

            //here we run through a list of bricks.
            Iterator<GRect> iterator = bricks.iterator();
            while (iterator.hasNext()) {
                GRect brick = iterator.next();

                /*if the function returned the graphic object that the bullet collided with,
                and it is equal to the brick, then bounce off it in the other direction and delete it*/
                if (collider == brick) {
                    iterator.remove();
                    remove(brick);
                    calcDeleteBricks++;
                    vy *= -1;
                }
            }

            //while the brick is still present
            if (calcDeleteBricks < NBRICKS_PER_ROW * NBRICK_ROWS) {

                //if the ball flew outside the program
                if (boll.getY() > getHeight()) {
                    calcNTurnsLabelDecrement--;
                    calcNTurns++;

                    //if there are still free attempts, then create a new ball and restart the animation
                    if (NTURNS > calcNTurns) {
                        remove(boll);
                        boll = createBoll();
                        startGames();
                    }
                    //if there are no free attempts, it is time to remove the ball and finish the game
                    else {
                        remove(boll);
                        GLabel label2 = createLabel("Game over, unfortunately you lost, try again");
                        break;
                    }
                }
            }
            //if there are no bricks left, the player wins and the game is over
            else {
                GLabel label3 = createLabel("Game over, congratulations you won the game");
                remove(boll);
                break;
            }
            pause(PAUSE_TIME);
        }
    }

    /**
     * If the coordinate (x, y) is below the object, the function will return the graphic object that the ball collided with.
     * If there are no objects, the function will return ‘null’.
     *
     * @param boll which appears in the program
     * @return the graphic object the bullet collided with
     */
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

        if (NBRICK_ROWS == 1) {
            coordinateY = createRows(coordinateY, Color.RED);
        }
        if (NBRICK_ROWS == 2) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
        }
        if (NBRICK_ROWS == 3) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createRows(coordinateY, Color.ORANGE);
        }
        if (NBRICK_ROWS == 4) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
        }
        if (NBRICK_ROWS == 5) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
            coordinateY = createRows(coordinateY, Color.YELLOW);
        }
        if (NBRICK_ROWS == 6) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
            coordinateY = createTwoRows(coordinateY, Color.YELLOW);
        }
        if (NBRICK_ROWS == 7) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
            coordinateY = createTwoRows(coordinateY, Color.YELLOW);
            coordinateY = createRows(coordinateY, Color.GREEN);
        }
        if (NBRICK_ROWS == 8) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
            coordinateY = createTwoRows(coordinateY, Color.YELLOW);
            coordinateY = createTwoRows(coordinateY, Color.GREEN);
        }
        if (NBRICK_ROWS > 8) {
            coordinateY = createTwoRows(coordinateY, Color.RED);
            coordinateY = createTwoRows(coordinateY, Color.ORANGE);
            coordinateY = createTwoRows(coordinateY, Color.YELLOW);
            coordinateY = createTwoRows(coordinateY, Color.GREEN);
            for (int i = 8; i < NBRICK_ROWS; i++) {
                coordinateY = createRows(coordinateY, Color.blue);
            }
        }
    }

    private int createTwoRows(int coordinateY, Color color) {
        coordinateY = createRows(coordinateY, color);
        coordinateY = createRows(coordinateY, color);
        return coordinateY;
    }


    //This method creates two rows of the matrix.
    private int createRows(int coordinateY, Color color) {

        //this loop is iterated on lines
        for (int i = 0; i < 1; i++) {
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
                rackets.setLocation(0, rackets.getY());
            } else {
                rackets.setLocation(getWidth() - PADDLE_WIDTH, rackets.getY());
            }

        } else {
            rackets.setLocation(mouseEvent.getX() - MIDDLE_PADDLE_WIDTH, rackets.getY());
        }
    }

    /**
     * this method creates and adds a caption
     *
     * @param text this is the text to add
     * @return will return the ready inscription, in the set coordinates
     */
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

    /**
     * Create a brick and add.
     *
     * @param x     this is coordinate of X
     * @param y     this is coordinate of Y
     * @param color this is color of bricks
     * @return bricks
     */
    private GRect createBrick(int x, int y, Color color) {
        GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setFillColor(color);
        add(brick);
        return brick;
    }

    //Create and add rackets
    private GRect createRectangle() {
        rackets = new GRect(
                getWidth() / 2 - MIDDLE_PADDLE_WIDTH,
                getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET,
                PADDLE_WIDTH,
                PADDLE_HEIGHT
        );
        rackets.setFilled(true);
        rackets.setFillColor(Color.BLACK);
        add(rackets);
        return rackets;
    }

    //This method generates a random number from [1-3].
    private void randomGenerator() {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        vx = randomGenerator.nextDouble(1.0, 3.0);
        if (randomGenerator.nextBoolean(0.5))
            vx = -vx;
    }
}