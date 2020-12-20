package com.shpp.p2p.cs.yfurd.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

    private final List<NameSurferEntry> listSchedule = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        listSchedule.removeAll(listSchedule);
        update();
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */

    public void addEntry(NameSurferEntry entry) {
        listSchedule.add(entry);
        update();
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        createAndAddGrids();
        createAndAddSchedule();
    }

    // Creates a graph of popularity
    private void createAndAddSchedule() {
        Color[] colors = {Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK};

        // depending on the size of the program window, calculates how much you need to divide the popularity
        // so that it is correctly displayed on the screen
        double sizeOnePixels = 1000 / (getHeight() - GRAPH_MARGIN_SIZE * 2.0);

        int checkColor = 0;

        for (NameSurferEntry entry : listSchedule) {
            if (checkColor == 4) {
                checkColor = 0;
            }
            for (int i = 1; i < NDECADES; i++) {

                int xPrevCoordinate = getWidth() / NDECADES * (i - 1);
                int xNextCoordinate = getWidth() / NDECADES * (i);

                double yPrevCoordinate;
                double yNextCoordinate;

                if (entry.getRank(i - 1) == 0) {
                    yPrevCoordinate = getHeight() - GRAPH_MARGIN_SIZE;
                } else {
                    yPrevCoordinate = GRAPH_MARGIN_SIZE + entry.getRank(i - 1) / sizeOnePixels;
                }

                if (entry.getRank(i) == 0) {
                    yNextCoordinate = getHeight() - GRAPH_MARGIN_SIZE;
                } else {
                    yNextCoordinate = GRAPH_MARGIN_SIZE + entry.getRank(i) / sizeOnePixels;
                }

                createAndAddLabel(entry.getName() + entry.getRank(i - 1), xPrevCoordinate, yPrevCoordinate, colors[checkColor]);
                createAndAddLine(xPrevCoordinate, yPrevCoordinate, xNextCoordinate, yNextCoordinate, colors[checkColor]);

                if (i == NDECADES - 1) {
                    createAndAddLabel(entry.getName() + entry.getRank(i), xNextCoordinate, yNextCoordinate, colors[checkColor]);
                }
            }
            checkColor++;
        }
    }

    // Draws a grid on the screen.
    private void createAndAddGrids() {
        createAndAddLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE, Color.BLACK);
        createAndAddLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE, Color.BLACK);

        int columns = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            createAndAddLine(i * columns, 0, i * columns, getHeight(), Color.BLACK);
            createAndAddLabel(Integer.toString(START_DECADE + i * DECADE), 1 + i * columns, getHeight() - 1, Color.BLACK);
        }
    }

    /**
     *  Creates and adds information to the schedule
     *
     * @param text information to add to the schedule
     * @param x the initial coordinate of the line
     * @param y the initial coordinate of the line
     * @param color of the text
     */
    private void createAndAddLabel(String text, double x, double y, Color color) {
        GLabel gLabel = new GLabel(text, x, y);
        gLabel.setColor(color);
        add(gLabel);
    }

    /**
     * This method creates and adds a line.
     *
     * @param x the initial coordinate of the line
     * @param y the initial coordinate of the line
     * @param x1 the final coordinate of the line
     * @param y1 the final coordinate of the line
     * @param color of the line
     */
    private void createAndAddLine(double x, double y, double x1, double y1, Color color) {
        GLine gLine = new GLine(x, y, x1, y1);
        gLine.setColor(color);
        add(gLine);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
