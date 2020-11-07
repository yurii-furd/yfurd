package com.shpp.p2p.cs.yfurd;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    public void run() throws Exception {

        moveToLineSouthAndWest();
        while (!frontIsBlocked()) {
            if (beepersPresent()) {
                move();
                turnRight();
                moveToEvenLines();
            } else {
                moveToOddLines();
            }
        }
    }

    /**
     * Prerequisite: Karel stands on the start.
     * Result: Karel runs to the corner and puts beepers. He comes back to the start and runs to the north west.
     * Karel puts beepers and comes back to the start. He is facing north after this operation.
     */
    private void moveToLineSouthAndWest() throws Exception {
        putBeeper();
        moveToLineSouthOrWestThenReturnBack();
        moveToLineSouthOrWestThenReturnBack();
        turnRight();
    }

    /**
     * Karel runs to the corner, and puts beepers. He comes back to the start
     */
    private void moveToLineSouthOrWestThenReturnBack() throws Exception {

        while (!frontIsBlocked()) {
            move();
            if (!frontIsBlocked()) {
                move();
                putBeeper();
            }
        }
        moveToBack();
    }

    /**
     * Karel runs all the odd lines, after 1.
     */
    private void moveToOddLines() throws Exception {
        move();
        turnRight();
        moveToLineSouthOrWestThenReturnBack();
    }

    /**
     * Karel runs all the even lines, after 1.
     */
    private void moveToEvenLines() throws Exception {
        if (!frontIsBlocked()) {
            move();
            putBeeper();
            moveToLineSouthOrWestThenReturnBack();
        }
    }

    /**
     * This method returns Karl from one end to the other without putting beepers.
     */
    private void moveToBack() throws Exception {
        turnLeft();
        turnLeft();
        while (!frontIsBlocked()) {
            move();
        }
        turnRight();
    }

    /**
     * This method turns Karel right.
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
