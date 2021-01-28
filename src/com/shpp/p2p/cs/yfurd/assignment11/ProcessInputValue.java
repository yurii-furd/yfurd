package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.*;

public class ProcessInputValue {

    final Set<Character> OPERATORS = new HashSet<>(Arrays.asList('+', '*', '/', '^'));
    final Set<Character> OPERATORS_FULL = new HashSet<>(Arrays.asList('+', '*', '/', '^', '-'));
    final Set<Character> NUMBERS = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    final Set<Character> LETTERS = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a',
            's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'));
    final Set<String> math = new HashSet<>(Arrays.asList("sin", "cos", "tan", "atan", "log10", "sqrt"));

    boolean negativeNumber = false;
    boolean positiveNumber = false;
    boolean negativeVariable = false;
    boolean positiveVariable = false;
    int count = 0;
    Map<String, Double> variables = new HashMap<>();

    /**
     * This method processes the array of strings and gets from it the name of the variable and its value.
     *
     * @param args input information.
     * @return list of processed information.
     */
    public Map<String, Double> parseVariables(String[] args) {
        StringBuilder tempKey = new StringBuilder();
        StringBuilder tempValue = new StringBuilder();
        char[] tempChFormula;
        for (int i = 1; i < args.length; i++) {
            tempChFormula = removeSpaces(args[i]).toString().toCharArray();

            for (int j = 0; j < tempChFormula.length; j++) {

                if (tempChFormula.length < 3 || !LETTERS.contains(tempChFormula[0])
                        || !NUMBERS.contains(tempChFormula[tempChFormula.length - 1])) {
                    throwException("Wrong input parameters!!!");
                } else if (j == 0 && LETTERS.contains(tempChFormula[j]) || positiveVariable) {
                    positiveVariable = true;
                    tempKey.append(tempChFormula[j]);
                    if (tempChFormula[j + 1] == '=') {
                        positiveVariable = false;
                    }
                } else if (j > 1 && tempChFormula[j - 1] == '=' && NUMBERS.contains(tempChFormula[j])
                        || j > 1 && tempChFormula[j - 1] == '=' && tempChFormula[j] == '-' && NUMBERS.contains(tempChFormula[j + 1])
                        || positiveNumber
                ) {
                    positiveNumber = true;
                    checkDot(tempChFormula[j]);
                    tempValue.append(tempChFormula[j]);
                    if (j == tempChFormula.length - 1) {
                        positiveNumber = false;
                        variables.put(tempKey.toString(), Double.parseDouble(tempValue.toString()));
                        count = 1;
                        tempKey.setLength(0);
                        tempValue.setLength(0);
                    }
                }
            }
        }
        return variables;
    }

    /**
     * This method checks to see if there are enough dots in the number.
     *
     * @param ch the current position in the array of spells.
     */
    private void checkDot(char ch) {
        if (ch == '.' && count == 2) {
            throwException("Wrong number!!!");
        }
        if (ch == '.' && count == 1) {
            count = 2;
        }
    }

    /**
     * This method removes all spaces from the text string.
     *
     * @param str input text string.
     * @return text string without spaces.
     */
    private StringBuilder removeSpaces(String str) {
        char[] ch = str.trim().toCharArray();
        StringBuilder newForm = new StringBuilder();
        for (char c : ch) {
            if (c != ' ') {
                newForm.append(c);
            }
        }
        return newForm;
    }

    /**
     * This method throws an error.
     *
     * @param s comment to error.
     */
    private void throwException(String s) {
        System.out.println(s);
        throw new NumberFormatException();
    }

}
