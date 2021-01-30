package com.shpp.p2p.cs.yfurd.assignment11;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class Assignment11Part1Test {

    @Test
    @Parameters(
            {
                    "-(1+2*3/4^5+(-6*7/(cos(8)^9+sin(tan(atan(sin(10)^11)/12)*13)+14-15*16))^17-18+(-19^(-20))*(-21)+22^23+tan(24)-sqrt(25)-26+27^28/29-30)/31^a+sqrt(sqrt(625)); a=36, 5.0",
                    "a + 1; a=5, 6.0",
                    "a + b + c; a=5; b=4; c=7, 16.0",
                    "a^-b; a=2; b=-2, 4.0 ",
                    "1*-a^b; a=2; b=2, 4.0",
                    "1+-a; a=2, -1.0",
                    "a^b; a=2; b=2, 4.0",
                    "3.0^a/10^80+50; a=2, 50.0",
                    "a^-b; a=2; b=2, 0.25",
                    "-a^-b; a=2; b=3, -0.125",
                    "-a^-b; a=-2; b=3, 0.125",
                    "-a^-b-10^c; a=2; b=3; c=1, -10.125",
                    "-10^c; a=2; b=3; c=2, 100",
                    "-10^c^a^b; a=2; b=3; c=2, 1.0E12",
                    "-10^255*a; a=1; b=3; c=2, -1.0E255",
                    "-10^c+a^b-10; a=2; b=3; c=2, 98.0",
                    "-10^c+a^-b-10; a=2; b=3; c=2, 90.125",
                    "-1.0+15-10*10/2*a-c*-b/-c; a=2; b=3; c=2, -89",
                    "-100.0+15-10*10/2*a-c*-b/-c; a=2; b=3; c=2, -188",
                    "-1.0+15-10*10/2*a-c*-b/-c; a=2; b=3; c=-2, -89",
                    "10*10/2*2-c*-3/-c; a=2; b=3; c=-2, 97",
                    "10/2*2-c*-3/-c; a=2; b=3; c=-2, 7",
                    "1+a*2; a=2, 5",
                    "a; a=2, 2",
                    "1+a*-2; a=2, -3",
                    "-1+a*-2; a=2, -5",
                    "1-a*2; a=2, -3",
                    "1-a/2; a=2, 0",
                    "1-a^3; a=2, -7",
                    "1-a+2; a=2, 1",
                    "1-a+2*b; a=2; b=10, 19",
                    "1-a+2*b^2; a=2; b=10, 199",
                    "1/a+2*b^2; a=2; b=10, 200.5",
                    "1.0/a+2.0*b^2.0-c; b=10.0; a=2.0; c=0, 200.5",
                    "-a^b^-c; b=10.0; a=2.0; c=0, 1",
                    "a^b; b=10.59; a=2.0; c=0, 1541.372669348932845",
                    "a*10/b*3; b=10; a=2.0; c=0, 6.0",
                    "a^3^b^-6; b=10; a=2.0; c=0, 2.000001523001437",////////????????????????????????????????????????
                    "a*-b; b=10; a=2.0; c=0, -20.0",
                    "a^-b; b=-2; a=2.0; c=0, 4.0"
            }
    )
    public void checkCalculate(String argsString, double expected) {

        double res = calculate(argsString.split(";"));
        Assert.assertEquals(expected, res, 0.0000000000001);
    }


    public double calculate(String[] args){
        ProcessInputFormula inputFormula = new ProcessInputFormula();
        List<String> listValues = inputFormula.fondValues(args[0]);

        ProcessInputValue inputValue = new ProcessInputValue();
        Map<String, Double> variables = inputValue.parseVariables(args);

        Calculation calculation = new Calculation();
        return calculation.calculateValue(listValues, variables);

    }

     /*   System.out.println("\nCheck class Parser");
    checkCalculate(new String[]{"a^-b", "b=-2", "a=2.0", "c=0"}, -20.0)
            */
}
