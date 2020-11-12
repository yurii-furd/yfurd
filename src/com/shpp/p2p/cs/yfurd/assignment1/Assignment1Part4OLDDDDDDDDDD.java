package com.shpp.p2p.cs.yfurd.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4OLDDDDDDDDDD extends KarelTheRobot {

    public void run() throws Exception {

        moveToLineSouthAndWest();
        while (frontIsClear()) {
            //If a beeper is present, the method for the even line is executed.
            if (beepersPresent()) {
                oneStepAndTurnRight();
                moveToEvenLines();
                //If a beeper is not present, the method for the odd line is executed.
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

        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
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
        oneStepAndTurnRight();
        moveToLineSouthOrWestThenReturnBack();
    }

    /**
     * Karel runs all the even lines, after 1.
     */
    private void moveToEvenLines() throws Exception {
        if (frontIsClear()) {
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

    /**
     * This method takes a step and turn to the right.
     */
    private void oneStepAndTurnRight() throws Exception {
        move();
        turnRight();
    }
}
