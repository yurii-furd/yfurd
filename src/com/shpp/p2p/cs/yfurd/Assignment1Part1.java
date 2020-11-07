package com.shpp.p2p.cs.yfurd;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {

    public void run() throws Exception {
        comeToTheBeeper();
        pickBeeper();
        comeBack();
    }

    /**
     * Prerequisite: Karel is at the start.
     * Result: Karel came to the beeper.
     */
    public void comeToTheBeeper() throws Exception {
        goTwoSteps();
        turnRight();
        move();
        turnLeft();
        goTwoSteps();
    }

    /**
     * Prerequisite: Karel stands in the place of the beeper, looking to the east.
     * Result: Karel came to the starting point.
     */
    private void comeBack() throws Exception {
        turnLeft();
        turnLeft();
        goTwoSteps();
        turnRight();
        move();
        turnLeft();
        goTwoSteps();
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
     * This method moves Karel two steps.
     */
    private void goTwoSteps() throws Exception {
        move();
        move();
    }
}