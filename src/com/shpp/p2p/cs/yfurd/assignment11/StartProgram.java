package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.List;
import java.util.Map;

public class StartProgram {

    public static void main(String[] args) {

        //"-(1+2*3/4^5+(-6*7/(cos(8)^9+sin(tan(atan(sin(10)^11)/12)*13)+14-15*16))^17-18+(-19^(-20))*(-21)+22^23+tan(24)-sqrt(25)-26+27^28/29-30)/31^a+sqrt(sqrt(625))" "a=36"
        ProcessInputFormula inputFormula = new ProcessInputFormula();
        List<String> listValues = inputFormula.fondValues(args[0]);

        ProcessInputValue inputValue = new ProcessInputValue();
        Map<String, Double> variables = inputValue.parseVariables(args);

        Calculation calculation = new Calculation();
        double res = calculation.calculateValue(listValues, variables);

        System.out.println(listValues);
        System.out.println(variables);
        System.out.println("result -> " + res);
    }
}
