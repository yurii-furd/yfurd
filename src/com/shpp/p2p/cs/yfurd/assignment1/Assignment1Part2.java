package com.shpp.p2p.cs.yfurd.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot {

    public void run() throws Exception {

        while (frontIsClear()) {
            buildColumn();
            goToNextColumn();
        }
        buildColumn();
    }

    /**
     * This method builds a column.
     * Prerequisite: Karel is facing east and he is on the start of the column.
     * Result:Karel is at the beginning of the column facing east.
     */
    private void buildColumn() throws Exception {
        turnLeft();
        while (frontIsClear()) {
            ifNoBeepersPresentPutBeeper();
            move();
        }
        ifNoBeepersPresentPutBeeper();
        turnAround();
        moveToStartColumn();
    }

    /**
     * This method goes to another column.
     */
    private void goToNextColumn() throws Exception {
        if (frontIsClear()) {
            for (int i = 1; i < 5; i++) {
                move();
            }
        }
    }

    /**
     * Prerequisite: Karel is at the end of the column.
     * Result: Karel came to the start of the column.
     */
    private void moveToStartColumn() throws Exception {
        while (frontIsClear()) {
            move();
        }
        turnLeft();
    }

    /**
     * This method turns Karel around.
     */
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * If there is no beeper then put.
     */
    private void ifNoBeepersPresentPutBeeper() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
    }
}
