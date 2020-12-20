package com.shpp.p2p.cs.yfurd.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import javax.swing.*;
import java.awt.event.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {

    private final JTextField inputText = new JTextField(GRAPH_MARGIN_SIZE);
    private final JButton buttonGraph = new JButton("Graph");
    private final JButton buttonClear = new JButton("Clear");
    private final NameSurferDataBase dataBase = new NameSurferDataBase("src/com/shpp/p2p/cs/yfurd/assignment7/names-data.txt");
    private final NameSurferGraph graph = new NameSurferGraph();

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(graph);
        add(new JLabel("Name:"), NORTH);
        add(inputText, NORTH);
        add(buttonGraph, NORTH);
        add(buttonClear, NORTH);
        inputText.addActionListener(this);
        addActionListeners();
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonGraph) || e.getSource().equals(inputText)) {
            if (dataBase.findEntry(inputText.getText()) != null) {
                graph.addEntry(dataBase.findEntry(inputText.getText()));
            }
        }
        if (e.getSource().equals(buttonClear)) {
            graph.clear();
        }
    }

}
