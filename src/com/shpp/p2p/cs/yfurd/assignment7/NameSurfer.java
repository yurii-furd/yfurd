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

    private final JTextField jTextField = new JTextField(GRAPH_MARGIN_SIZE);
    private final JButton jButton = new JButton("Graph");
    private final JButton clear = new JButton("Clear");
    private final NameSurferDataBase nameSurferDataBase = new NameSurferDataBase("src/com/shpp/p2p/cs/yfurd/assignment7/names-data.txt");
    private final NameSurferGraph graph = new NameSurferGraph();

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(graph);
        add(new JLabel("Name:"), NORTH);
        add(jTextField, NORTH);
        add(jButton, NORTH);
        add(clear, NORTH);
        jTextField.addActionListener(this);
        addActionListeners();
    }

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        boolean inputDontNull = jTextField.getText().length() != 0;
        if (e.getSource().equals(jButton) && inputDontNull || e.getSource().equals(jTextField) && inputDontNull) {
            graph.addEntry(nameSurferDataBase.findEntry(jTextField.getText()));
        }
        if (e.getSource().equals(clear)) {
            graph.clear();
        }
    }

}
