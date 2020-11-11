package com.shpp.p2p.cs.yfurd.assignment1;

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
        turnRightAndOneStep();
        turnLeft();
        while (noBeepersPresent()){
            move();
        }
    }

    /**
     * Prerequisite: Karel stands in the place of the beeper, looking to the east.
     * Result: Karel came to the starting point.
     */
    private void comeBack() throws Exception {
        turnLeft();
        turnLeft();
        while (frontIsClear()){
            move();
        }
        turnRightAndOneStep();
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
     * This method turn right and move one step.
     */
    private void turnRightAndOneStep() throws Exception {
        turnRight();
        move();
    }
}