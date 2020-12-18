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

import static java.lang.Thread.sleep;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

    private Map<String, NameSurferEntry> map = new HashMap<>();
    private List<NameSurferEntry> list = new ArrayList<>();
    private GCanvas gCanvas = new GCanvas();


    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        // You fill in the rest //
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
//        list.removeAll(list);
    }

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */

    public void addEntry(NameSurferEntry entry) {
//        map.put(entry.getName(), entry);
//        list.add(entry);
//        System.out.println(list.size());
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        createAndAddTable();
    }

    private void createAndAddTable() {
        createAndAddLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE, Color.BLACK);
        createAndAddLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE, Color.BLACK);
        int columns = getWidth() / NDECADES;

        for (int i = 0; i < NDECADES; i++) {
            createAndAddLine(i * columns, 0, i * columns, getHeight(), Color.BLACK);
            createAndAddLabel(Integer.toString(START_DECADE + i * DECADE), 1 + i * columns, getHeight() - 1);
        }
    }

    private void createAndAddLabel(String text, double x, double y) {
        GLabel gLabel = new GLabel(text, x, y);
        add(gLabel);
    }

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
