package com.shpp.p2p.cs.yfurd.assignment8;

import acm.graphics.GRect;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Assignment8Part1 extends WindowProgram {

    public static final int APPLICATION_WIDTH = 1000;
    public static final int APPLICATION_HEIGHT = 1000;

    // The width and height of each box.
    private static final double BOX_SIZE = 40;

    private static final double COUNT_BOXES = 20;

    //The amount of time to pause between frames (50fps)
    public static final int PAUSE_TIME = 1000 / 50;

    double mouseX;
    double mouseY;

    private int direction = 1;

    List<GRect> moveToMouse = new ArrayList<>();
    List<GRect> moveFromMouse = new ArrayList<>();

    @Override
    public void run() {
        addMouseListeners();
        createBoxes();
        createAnimation();
    }

    private void createAnimation() {
        while (true) {
            for (GRect g : moveToMouse) {
                double lengthByX = mouseX - g.getX();
                double lengthByY = mouseY - g.getY();
                double distance = Math.sqrt(lengthByX * lengthByX + lengthByY * lengthByY);
                g.move(lengthByX / distance * direction, lengthByY / distance * direction);
            }

            for (GRect g : moveFromMouse) {
                double lengthByX = mouseX - g.getX();
                double lengthByY = mouseY - g.getY();
                double distance = Math.sqrt(lengthByX * lengthByX + lengthByY * lengthByY);
                g.move(lengthByX / distance * -1 * direction, lengthByY / distance * -1 * direction);
            }
            pause(PAUSE_TIME);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        direction = 1;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        direction = -1;
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }

    private void createBoxes() {
        RandomGenerator randomGenerator = new RandomGenerator();
        for (int i = 0; i < COUNT_BOXES; i++) {
            double x = randomGenerator.nextDouble(0, getWidth() - BOX_SIZE);
            double y = randomGenerator.nextDouble(0, getHeight() - BOX_SIZE);

            if (randomGenerator.nextBoolean()) {
                moveToMouse.add(createBox(x, y));
            } else {
                moveFromMouse.add(createBox(x, y));
            }
        }
    }

    private GRect createBox(double x, double y) {
        GRect gRect = new GRect(x, y, BOX_SIZE, BOX_SIZE);
        gRect.setColor(Color.BLACK);
        gRect.setFilled(true);
        gRect.setFillColor(Color.BLACK);
        add(gRect);
        return gRect;
    }
}
