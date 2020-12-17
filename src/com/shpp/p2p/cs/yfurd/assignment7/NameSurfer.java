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

    private JTextField jTextField = new JTextField(20);
    private JButton graph = new JButton("Graph");
    private JButton clear = new JButton("Clear");

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        add(new JLabel("Name:"), NORTH);
        add(jTextField, NORTH);
        add(graph, NORTH);
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
        if (e.getSource().equals(graph) && jTextField.getText().length() > 0
                || e.getSource().equals(jTextField) && jTextField.getText().length() > 0) {

            System.out.println(jTextField.getText());
        }
        if (e.getSource().equals(clear)) {
            System.out.println("clear");
        }
    }
}
