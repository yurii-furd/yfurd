package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.*;

public class ProcessInputValue {

    boolean positiveNumber = false;
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
            tempChFormula = args[i].replaceAll(" ", "").toCharArray();

            for (int j = 0; j < tempChFormula.length; j++) {

                if (tempChFormula.length < 3 || !Character.isLetter(tempChFormula[0])
                        || !Character.isDigit(tempChFormula[tempChFormula.length - 1])) {
                    throwException("Wrong input parameters!!!");
                } else if (j == 0 && Character.isLetter(tempChFormula[j]) || positiveVariable) {
                    positiveVariable = true;
                    tempKey.append(tempChFormula[j]);
                    if (tempChFormula[j + 1] == '=') {
                        positiveVariable = false;
                    }
                } else if (j > 1 && tempChFormula[j - 1] == '=' && Character.isDigit(tempChFormula[j])
                        || j > 1 && tempChFormula[j - 1] == '=' && tempChFormula[j] == '-' && Character.isDigit(tempChFormula[j + 1])
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
     * This method throws an error.
     *
     * @param s comment to error.
     */
    private void throwException(String s) {
        System.out.println(s);
        throw new NumberFormatException();
    }

}
