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
import java.util.List;
import java.util.Objects;

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
     * Radius and diameter of the ball in pixels
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

    // The amount of time to pause between frames (50fps)
    private static final int PAUSE_TIME = 1000 / 50;

    // Parameters that monitor for the speed of boll X and Y.
    private double vx = 0;
    private double vy = 3.0;

    private GRect racket;
    //this variable decrements after the cell has fallen outside the window
    private int attemptsLeft = NTURNS;

    public void run() {
        //This command to display the program window correctly(Linux).
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        addMouseListeners();
        // create paddle
        racket = createRectangle();
        // create boll
        GOval ball = createBoll();
        // create bricks
        List<GRect> bricks = createGridOfBricks();

        startGames(ball, bricks);
    }

    // This method starts the game.
    private void startGames(GOval boll, List<GRect> bricks) {

        GLabel label = createLabel("Tap to start. You have " + attemptsLeft + " attempts");
        waitForClick();
        remove(label);
        randomGenerator();
        createAnimation(boll, bricks);
    }

    // This method creates an animation.
    private void createAnimation(GOval ball, List<GRect> bricks) {
        //this variable counts the bricks that knocked down...
        int calcDeleteBricks = 0;

        while (true) {
            ball.move(vx, vy);

            processCollidingWithWalls(ball);
            GObject collider = getCollidingObjectRacket(ball);
            processCollidingWithBricks(collider, bricks, calcDeleteBricks);

            // If there are no bricks left, the player wins and the game is over.
            if (bricks.isEmpty()) {
                createLabel("Game over, congratulations you won the game");
                remove(ball);
                break;
            }

            //if the ball flew outside the program
            if (ball.getY() > getHeight()) {
                attemptsLeft--;

                //if there are still free attempts, then create a new ball and restart the animation
                if (attemptsLeft > 0) {
                    remove(ball);
                    ball = createBoll();
                    startGames(ball, bricks);
                } else {// If there are no free attempts, it is time to remove the ball and finish the game
                    remove(ball);
                    createLabel("Game over, unfortunately you lost, try again");
                    break;
                }
            }
            pause(PAUSE_TIME);
        }
    }

    // If the ball hits the brick then hit it in the other direction
    private void processCollidingWithBricks(GObject collider, List<GRect> bricks, int calcDeleteBricks) {
        //here we run through a list of bricks.
        Iterator<GRect> iterator = bricks.iterator();
        while (iterator.hasNext()) {
            GRect brick = iterator.next();

        /*if the function returned the graphic object that the bullet collided with,
        and it is equal to the brick, then bounce off it in the other direction and delete it*/
            if (Objects.equals(brick, collider)) {
                iterator.remove();
                remove(brick);
                calcDeleteBricks++;
                vy *= -1.01;
            }
        }
    }

    /* If the cell is lower than the racket, then getCollidingObject () will always be null,
        and if higher, then it is reflected in the opposite direction
        */
    private GObject getCollidingObjectRacket(GOval boll) {
        GObject collider;
        /* If the coordinate of the ball Y + 2R - vy is greater than the coordinate of the racket,
            then read through the "getElementAt ()" coordinates
            */
        if (boll.getY() + BALL_DIAMETER - vy <= racket.getY()) {
            collider = getCollidingObject(boll);

                /* If the function returned the graphic object that the bullet collided with,
                and it is equal to the racket, then bounce off it in the other direction
                */
            if (collider == racket) {
                vy *= -1;
            }
        } else {/* If the coordinate of the ball Y + 2R -vy is less than the coordinate of the racket,
                  then read through "getElementAt ()" is not required
                  */
            collider = null;
        }
        return collider;
    }

    // This method describes the instruction when the ball hits the right, left or top wall.
    private void processCollidingWithWalls(GOval boll) {
        //If the ball reaches the left wall, then we direct it to another
        if (boll.getX() < 0) {
            vx *= -1;
        }

        //If the ball reaches the right wall, then we direct it to another
        if (boll.getX() + BALL_DIAMETER > getWidth()) {
            vx *= -1;
        }

        //If the ball hits the top of the window, direct it down
        if (boll.getY() < 0) {
            vy *= -1;
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
    private List<GRect> createGridOfBricks() {
        int coordinateY = BRICK_Y_OFFSET;
        List<GRect> bricks = new ArrayList<>();

        Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};
        // This loop controls the printing of strings
        for (int i = 0; i < NBRICK_ROWS; i++) {
            // This variable controls the color of the line print
            int colorIndex = i / 2;
            // If x is larger than the color array, always print the color that is last in the array
            if (colorIndex >= color.length - 1) {
                colorIndex = color.length - 1;
            }
            bricks.addAll(createRows(coordinateY, color[colorIndex]));
            coordinateY = coordinateY + BRICK_HEIGHT + BRICK_SEP;
        }
        return bricks;
    }

    //This method creates one rows of the matrix.
    private List<GRect> createRows(int coordinateY, Color color) {
        List<GRect> bricks = new ArrayList<>();

        //this loop creates bricks in a row
        for (int j = 0; j < NBRICKS_PER_ROW; j++) {
            //here we find the first X coordinate
            int firstCoordinateX = (getWidth() - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP)) / 2;
            //create a brick and add it to the collection.
            bricks.add(createBrick(firstCoordinateX + j * (BRICK_WIDTH + BRICK_SEP), coordinateY, color));
        }
        return bricks;
    }

    //This method binds the mouse to the paddle, and does not allow the paddle to go outside the window
    public void mouseMoved(MouseEvent mouseEvent) {
        if (mouseEvent.getX() <= MIDDLE_PADDLE_WIDTH || mouseEvent.getX() >= getWidth() - MIDDLE_PADDLE_WIDTH) {

            if (mouseEvent.getX() <= MIDDLE_PADDLE_WIDTH) {
                racket.setLocation(0, racket.getY());
            } else {
                racket.setLocation(getWidth() - PADDLE_WIDTH, racket.getY());
            }

        } else {
            racket.setLocation(mouseEvent.getX() - MIDDLE_PADDLE_WIDTH, racket.getY());
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
                getWidth() / 2.0 - BALL_RADIUS,
                getHeight() / 2.0 - BALL_RADIUS,
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
        return createAndAddGRect(x, y, BRICK_WIDTH, BRICK_HEIGHT, color);
    }

    //Create and add racket
    private GRect createRectangle() {
        return createAndAddGRect(getWidth() / 2.0 - MIDDLE_PADDLE_WIDTH,
                getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET,
                PADDLE_WIDTH,
                PADDLE_HEIGHT,
                Color.BLACK
        );
    }

    /**
     * This method create and add rect
     *
     * @param x      this is coordinate X
     * @param y      this is coordinate Y
     * @param width  rect
     * @param height rect
     * @param color  rect
     * @return add rect
     */
    private GRect createAndAddGRect(double x, int y, int width, int height, Color color) {
        GRect gRect = new GRect(x, y, width, height);
        gRect.setFilled(true);
        gRect.setFillColor(color);
        add(gRect);
        return gRect;
    }

    //This method generates a random number from [1-3].
    private void randomGenerator() {
        RandomGenerator randomGenerator = RandomGenerator.getInstance();
        vx = randomGenerator.nextDouble(1.0, 3.0);
        if (randomGenerator.nextBoolean(0.5))
            vx = -vx;
    }
}