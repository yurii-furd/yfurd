package com.shpp.p2p.cs.yfurd;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {

    public void run() throws Exception {


        // Check if the world is a single cell.
        if (frontIsBlocked()) {
            putBeeper();
        } else {
            oneStepWithLeftToRightOnDiagonal();
        }

        if (!frontIsBlocked()) {
            moveToNorthEast();
            moveDownToEastSouth();
            moveToWestNorthThenSouth();
            moveToStart();
            pickBeeperOnDiagonal();
        }
    }

    /**
     * Karel moves one step of the diagonally.
     * Prerequisite: He is facing east.
     * Result: Karl is at the end of the diagonally in the corner.He is facing south after this operation.
     */
    private void moveToNorthEast() throws Exception {
        while (!frontIsBlocked()) {
            oneStepWithLeftToRightOnDiagonal();
        }
        putBeeper();
        turnRight();
    }

    /**
     * Prerequisite: Karel is on the north east position. He is facing south.
     * Result: Karel runs down to the south east. He is facing west after this operation.
     */
    private void moveDownToEastSouth() throws Exception {
        while (!frontIsBlocked()) {
            move();
        }
        turnRight();
    }

    /**
     * Prerequisite: Karel is on the south east position. He is facing west.
     * Result: Karel runs to the north west. When he meets beepers he runs down and puts beeper.
     * He is facing west after this operation.
     */
    private void moveToWestNorthThenSouth() throws Exception {
        while (!beepersPresent()) {
            oneStepWithRightToLeftOnDiagonal();
        }
        if (beepersPresent()) {
            turnLeft();
            moveToMiddleSouth();
        }
    }

    /**
     * Prerequisite: Karel stands on the middle south facing west.
     * Result: Karel went to the start, pick beeper. He is facing north after this operation.
     */
    private void moveToStart() throws Exception {
        while (!frontIsBlocked()) {
            move();
        }
        pickBeeper();
        turnRight();
    }

    /**
     * Prerequisite: Karel stands on the start. He is facing north.
     * Result: Karel picked all the beepers on the diagonally.
     */
    private void pickBeeperOnDiagonal() throws Exception {
        move();
        turnRight();
        move();
        while (!frontIsBlocked()) {
            pickBeeper();
            turnLeft();
            move();
            turnRight();
            move();
        }
        if (beepersPresent()) {
            pickBeeper();
        }
    }

    /**
     * Prerequisite: Karel is on the start.
     * Result: Karel put beeper and makes one step of the diagonally. He is facing east.
     */
    private void oneStepWithLeftToRightOnDiagonal() throws Exception {
        putBeeper();
        move();
        turnLeft();
        move();
        turnRight();
    }

    /**
     * Prerequisite: Karel stands in the corner facing west.
     * Result: Karel makes one step to the west and he turns right then makes step up.
     */
    private void oneStepWithRightToLeftOnDiagonal() throws Exception {
        move();
        if (beepersPresent()) {
            facingEast();
        } else {
            turnRight();
            move();
            turnLeft();
        }
    }

    /**
     * Prerequisite: Karel stands at the intersection of the diagonals. He is facing west.
     * Result: Karel runs down and puts the beeper in the end. He is facing west after this operation.
     */
    private void moveToMiddleSouth() throws Exception {
        while (!frontIsBlocked()) {
            move();
        }
        putBeeper();
        turnRight();
    }

    /**
     * This method turn Karel right.
     */
    private void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
