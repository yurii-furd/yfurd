package com.shpp.p2p.cs.yfurd.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {

    public void run() throws Exception {

        putBeeper();
        //If front is blocked, means the world is one cell wide.
        if (frontIsBlocked()) {
            turnLeft();
            //Karel goes from one end to the other and puts beavers.
            goToWallAndPutBeepers();
        }
        //If front is Clear, Karel goes from one end to the other and puts beavers.
        if (frontIsClear()) {
            goToWallAndPutBeepers();
            turnLeft();
        }

        if (frontIsClear()) {
            while (frontIsClear()) {
                if (frontIsClear()) {
                    //Karl goes in even rows and puts beavers.
                    goInPairsAndPutBeepers();
                }
                if (frontIsClear()) {
                    turnRight();
                    //Karel goes from one end to the other and puts beavers.
                    goToWallAndPutBeepers();
                    turnLeft();
                } else {
                    turnRight();
                    move();

                    if (beepersPresent()) {
                        turnLeft();
                    } else {
                        goToWallAndPutBeepers();
                    }
                }
            }
        }
    }

    /**
     * Karl goes in even rows and puts beavers.
     */
    private void goInPairsAndPutBeepers() throws Exception {

        if (frontIsClear()) {
            if (noBeepersPresent()) {
                moveAndTurnLeft();
                putBeeper();
                goToWallTurnRightAndTakeAStep();

            } else {
                moveAndTurnLeft();
                move();
                putBeeper();
                goToWallTurnRightAndTakeAStep();
            }
        }
    }

    private void goToWallTurnRightAndTakeAStep() throws Exception {
        //Karel goes from one end to the other and puts beavers.
        goToWallAndPutBeepers();
        turnRight();
        /*
        This method checks:
        If front is clear and no beeper present, Karel takes one step and put beeper
        If front is not clear? Karel takes one step  */
        goStepAndPutBeeper();
    }

    /**
     * Karel goes from one end to the other and puts beavers.
     */
    private void goToWallAndPutBeepers() throws Exception {
        while (frontIsClear()) {
            move();
            if (frontIsClear()) {
                move();
                putBeeper();
            }
        }
    }

    /**
     * This method checks:
     * If front is clear and no beeper present, Karel takes one step and put beeper
     * If front is not clear? Karel takes one step
     */
    private void goStepAndPutBeeper() throws Exception {
        if (frontIsClear()) {
            if (noBeepersPresent()) {
                move();
                putBeeper();

            } else {
                move();
            }
        }
    }

    /**
     * Karel move and turn left.
     */
    private void moveAndTurnLeft() throws Exception {
        move();
        turnLeft();
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
