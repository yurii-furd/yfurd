package com.shpp.p2p.cs.yfurd.assignment11;

import org.junit.Test;

public class TestProgram {



    /*
    // a^-b a=2 b=-2
     */
        // 1*-a^b a=2 b=2
        // 1+-a a=2 b=2
     /*   System.out.println("\nCheck class Parser");
    checkParser(new String[]{}, TEXT_EMPTY_COMMAND_LINE);
    checkParser(new String[]{""}, TEXT_NO_PARAMETERS);
    checkParser(new String[]{"1.0 / a + 2.0 * b ^ 2.0 / c"}, TEXT_NO_PARAMETERS);

    checkParser(new String[]{"1.0 / a + 2.0 * b ^ 2.0 / c", "b=10.0", "c=0"}, TEXT_FEW_PARAMETERS);
    checkParser(new String[]{"1.0/A+2.0*^2/C", "b=10.0", "A=2.0"}, TEXT_FEW_PARAMETERS);
    checkParser(new String[]{"1.0 / bc + 2.0 * b ^ 2.0 / c + c * 2", "b=10.0", "A=2.0"}, TEXT_FEW_PARAMETERS);


    checkParser(new String[]{"1.0 / a ", "a=1.79769E+310"}, TEXT_WRONG_NUMBER);
    checkParser(new String[]{"1.0 / a ", "a=-1.79769E+310"}, TEXT_WRONG_NUMBER);

    checkParser(new String[]{"1.0 / a ", "a=1abc.797E6931348623157E308"}, TEXT_WRONG_FORMAT_PARAMETER);
    checkParser(new String[]{"1.0 / a ", "a=1abc.797E6931348623157E308", "b=10"}, TEXT_WRONG_FORMAT_PARAMETER);
    checkParser(new String[]{"1.0 / a ", "b=10.0", "A68/4/=2.0"}, TEXT_WRONG_FORMAT_PARAMETER);

    checkParser(new String[]{"1.0/a;+2.0*^2.0/c!", "b=10.0", "a=2.0", "c=1"}, TEXT_EXPRESSION_INCORRECT_SYMBOLS);
    checkParser(new String[]{"1.E10/a;+2.0*^2.0/c!", "b=10.0", "a=2.0", "c=1"}, TEXT_EXPRESSION_INCORRECT_SYMBOLS);
    checkParser(new String[]{"1.asdf10/a;+2.0*^2.0/c!", "b=10.0", "a=2.0", "c=1"}, TEXT_EXPRESSION_INCORRECT_SYMBOLS);
    checkParser(new String[]{"Hello:)", "c=0", "A=2.0"}, TEXT_EXPRESSION_INCORRECT_SYMBOLS);
    checkParser(new String[]{"b ^_^ 2.0 ", "b=10.0", "c=0", "A=2.0"}, TEXT_EXPRESSION_INCORRECT_SYMBOLS);

    checkParser(new String[]{"1.0 / abc + 2.0 * b ^ 2.995815 / c + c * 2", "c=23.50", "b=10.0", "A=2.0"}, TEXT_WRONG_VARIABLE);
    checkParser(new String[]{"1.0ab / b + 2.0 * b ^ 2.0 / c + c * 2", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_VARIABLE);
    checkParser(new String[]{"1.0ab -/ b ++ 2.0 * b ^ 255555.0 / c + c * 2", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_VARIABLE);

    checkParser(new String[]{"a ++ b ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"a+-b ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"a+-b ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"b ^- 2.0 ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"b -^- 2.0 ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"b ^-^ 2.0 ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"1.0+a^b^2 -/ b + 2.0 * b ^ 2.0 / c + c * 2", "a=10", "b=10.0", "c=0"}, TEXT_WRONG_MATH_OPERATOR);
    checkParser(new String[]{"128.0+a^b^-2 -/ b + 2.0 * b ^ 2.0 / c + c * 2", "a=10", "b=10.0", "c=0"}, TEXT_WRONG_MATH_OPERATOR);

    checkParser(new String[]{"^b ^- 2.0 ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_PATTERN_EXPRESSION);
    checkParser(new String[]{"b ^- 2.0- ", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_PATTERN_EXPRESSION);
    checkParser(new String[]{"*1.0+a^b^-2 -/ b + 2.0 * b ^ 2.0 / c + c * 2", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_PATTERN_EXPRESSION);
    checkParser(new String[]{"1.0+a^b^-2 -/ b + 2.0 * b ^ 2.0 / c + c * 2*", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_PATTERN_EXPRESSION);
    checkParser(new String[]{"1.0+a^b^-2 -/ b + 2.0 * b ^ 2.0 / c + c * 2-", "b=10.0", "c=0", "A=2.0"}, TEXT_WRONG_PATTERN_EXPRESSION);

    checkParser(new String[]{"+-/* ^^", "b=10.0", "c=0"}, TEXT_NO_VARIABLES_IN_EXPRESSION);
    checkParser(new String[]{"^-^", "b=10.0", "c=0"}, TEXT_NO_VARIABLES_IN_EXPRESSION);

        System.out.println("\nChecks simplifyExpression() method");
    checkSimplifyExpression(new String[]{"-a^b", "b=10.59", "a=2.0", "c=0"}, EXCEPTION_EXPONENT);
    checkSimplifyExpression(new String[]{"-a/b", "b=0", "a=2.0", "c=0"}, EXCEPTION_DIVISION_BY_ZERO);
    checkSimplifyExpression(new String[]{"a^3^b", "b=1024", "a=2.0", "c=0"}, OVERFLOW_ERROR);
    checkSimplifyExpression(new String[]{"a + 1.7976931348623157*10^308 + 1.7976931348623157*10^308", "a=2"}, OVERFLOW_ERROR);
    checkSimplifyExpression(new String[]{"4.9/10^324 - a", "a=2000000000.01"}, OVERFLOW_ERROR);

        System.out.println("\nChecks calculate() method");
    checkCalculate(new String[]{"a^b", "a=2", "b=2"}, 4);
    checkCalculate(new String[]{"3.0^a/10^80+50", "a=2"}, 50.0);
    checkCalculate(new String[]{"a^-b", "a=2", "b=2"}, 0.25);
    checkCalculate(new String[]{"-a^-b", "a=2", "b=3"}, -0.125);
    checkCalculate(new String[]{"-a^-b", "a=-2", "b=3"}, 0.125);
    checkCalculate(new String[]{"-a^-b-10^c", "a=2", "b=3", "c=1"}, -10.125);
    checkCalculate(new String[]{"-10^c", "a=2", "b=3", "c=2"}, 100);
    checkCalculate(new String[]{"-10^c^a^b", "a=2", "b=3", "c=2"}, 1.0E256);
    checkCalculate(new String[]{"-10^255*a", "a=1", "b=3", "c=2"}, -1.0E255);
    checkCalculate(new String[]{"-10^c+a^b-10", "a=2", "b=3", "c=2"}, 98.0);
    checkCalculate(new String[]{"-10^c+a^-b-10", "a=2", "b=3", "c=2"}, 90.125);
    checkCalculate(new String[]{"-1.0+15-10*10/2*a-c*-b/-c", "a=2", "b=3", "c=2"}, -89);
    checkCalculate(new String[]{"-100.0+15-10*10/2*a-c*-b/-c", "a=2", "b=3", "c=2"}, -188);
    checkCalculate(new String[]{"-1.0+15-10*10/2*a-c*-b/-c", "a=2", "b=3", "c=-2"}, -89);
    checkCalculate(new String[]{"10*10/2*2-c*-3/-c", "a=2", "b=3", "c=-2"}, 97);
    checkCalculate(new String[]{"10/2*2-c*-3/-c", "a=2", "b=3", "c=-2"}, 7);
    checkCalculate(new String[]{"1+a*2", "a=2"}, 5);
    checkCalculate(new String[]{"a", "a=2"}, 2);
    checkCalculate(new String[]{"1+a*-2", "a=2"}, -3);
    checkCalculate(new String[]{"-1+a*-2", "a=2"}, -5);
    checkCalculate(new String[]{"1-a*2", "a=2"}, -3);
    checkCalculate(new String[]{"1-a/2", "a=2"}, 0);
    checkCalculate(new String[]{"1-a^3", "a=2"}, -7);
    checkCalculate(new String[]{"1-a+2", "a=2"}, 1);
    checkCalculate(new String[]{"1-a+2*b", "a=2", "b=10"}, 19);
    checkCalculate(new String[]{"1-a+2*b^2", "a=2", "b=10"}, 199);
    checkCalculate(new String[]{"1/a+2*b^2", "a=2", "b=10"}, 200.5);
    checkCalculate(new String[]{"1.0/a+2.0*b^2.0-c", "b=10.0", "a=2.0", "c=0"}, 200.5);
    checkCalculate(new String[]{"-a^b^-c", "b=10.0", "a=2.0", "c=0"}, -2);
    checkCalculate(new String[]{"a^b", "b=10.59", "a=2.0", "c=0"}, 1541.372669348932845);
    checkCalculate(new String[]{"a*10/b*3", "b=10", "a=2.0", "c=0"}, 6);
    checkCalculate(new String[]{"a^3^b^-6", "b=10", "a=2.0", "c=0"}, 2.000001523001437);
    checkCalculate(new String[]{"a*-b", "b=10", "a=2.0", "c=0"}, -20.0);
    checkCalculate(new String[]{"a^-b", "b=-2", "a=2.0", "c=0"}, -20.0)
            */
}
