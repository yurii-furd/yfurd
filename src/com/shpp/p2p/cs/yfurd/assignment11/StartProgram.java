package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.List;
import java.util.Map;

public class StartProgram {

    public static void main(String[] args) {
        // 1*-a^b a=2 b=2
        // a^-b a=2 b=-2
        // 1+-a a=2 b=2

        ProcessInputFormula inputFormula = new ProcessInputFormula();
        List<String> listValues = inputFormula.fondValues(args[0]);

        ProcessInputValue inputValue = new ProcessInputValue();
        Map<String, Double> variables = inputValue.parseVariables(args);

        Calculation calculation = new Calculation();
        double res = calculation.calculateValue(listValues, variables);

        System.out.println(listValues);
        System.out.println(variables);
        System.out.println(res);


    }
}
