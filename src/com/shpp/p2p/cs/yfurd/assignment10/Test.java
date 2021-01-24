package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Assignment10Part1 expression = new Assignment10Part1();
        expression.fondValues(args[0]);
        Map<String, Double> variables = expression.parseVariables(args);
        double res = expression.calculateValue(variables);
        System.out.println("-> " + res);
    }
}