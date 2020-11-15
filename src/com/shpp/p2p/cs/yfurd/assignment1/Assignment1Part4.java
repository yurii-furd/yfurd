package com.shpp.p2p.cs.yfurd.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    public void run() throws Exception {

        putBeeper();
        //if the front is clean Karel goes south puts the beeper and come back.
        if (frontIsClear()) {
            moveToLineSouthReturnBack();
        } else {
            //if the front is blocked (the world is one cell wide) the Karel turns to the left,
            // passes to the end, and come back
            if (frontIsBlocked()) {
                turnLeft();
                moveToLineSouthReturnBack();
                turnRight();
            }
        }

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
     * Karel runs to the corner, and puts beepers. He comes back to the start
     */
    private void moveToLineSouthReturnBack() throws Exception {

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
        putBeeper();
        moveToLineSouthReturnBack();
    }

    /**
     * Karel runs all the even lines, after 1.
     */
    private void moveToEvenLines() throws Exception {
        if (frontIsClear()) {
            move();
            putBeeper();
            moveToLineSouthReturnBack();
        }
    }

    /**
     * This method returns Karl from one end to the other without putting beepers.
     */
    private void moveToBack() throws Exception {
        turnLeft();
        turnLeft();
        while (frontIsClear()) {
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
