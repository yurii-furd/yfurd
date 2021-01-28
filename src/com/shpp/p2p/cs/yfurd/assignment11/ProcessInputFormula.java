package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.*;

public class ProcessInputFormula {

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

    public static List<String> listValues = new LinkedList<>();

    int count = 0;
    int open = 0;
    int close = 0;
    StringBuilder numberStr = new StringBuilder();

    /**
     * Looks for variables in the text string.
     *
     * @param args input information.
     */
    public List<String> fondValues(String args) {
        StringBuilder newForm = removeSpaces(args);
        char[] formCh = newForm.toString().toLowerCase().toCharArray();

        System.out.println(formCh);
        if (formCh.length != 0) {
            for (int i = 0; i < formCh.length; i++) {
                fondNegativeNumber(i, formCh);
                fondPositiveNumber(i, formCh);
                fondNegativeVariable(i, formCh);
                fondPositiveVariable(i, formCh);
                fondOperator(i, formCh);
                checkErrorInFormula(i, formCh);
            }
            if (formCh[0] == '-' && formCh[1] == '(') {
                listValues.add(0, "-1");
                listValues.add(1, "*");
            }
            if (open != close) {
                throwException("Incorrect input: open brackets are not equal to closed");
            }
            listValues.add(0, "(");
            listValues.add(listValues.size(), ")");
        }
        return listValues;
    }

    /**
     * Looks for negative numbers in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondNegativeNumber(int i, char[] formCh) {
        if (i == 1 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '('
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^'
                || negativeNumber
        ) {
            negativeNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !NUMBERS.contains(formCh[i + 1]) && formCh[i + 1] != '.'
                    || i == formCh.length - 1 && NUMBERS.contains(formCh[i])
            ) {
                negativeNumber = false;
                count = 1;
                listValues.add("-" + numberStr.toString());
                numberStr.setLength(0);
            }
        }
    }

    /**
     * Looks for positive numbers in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondPositiveNumber(int i, char[] formCh) {
        if (i == 0 && NUMBERS.contains(formCh[i])
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == ')'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '('
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && NUMBERS.contains(formCh[i - 2])
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && LETTERS.contains(formCh[i - 2])
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '^'
                || positiveNumber
        ) {
            positiveNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !NUMBERS.contains(formCh[i + 1]) && formCh[i + 1] != '.'
                    || i == formCh.length - 1 && NUMBERS.contains(formCh[i])
            ) {
                positiveNumber = false;
                count = 1;
                listValues.add(numberStr.toString());
                numberStr.setLength(0);
            }
        }
    }

    /**
     * Looks for negative variables in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondNegativeVariable(int i, char[] formCh) {
        if (i == 1 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '('
                || negativeVariable
        ) {
            negativeVariable = true;
            numberStr.append(formCh[i]);

            if (checkIsFinishedVariable(i, formCh)) {
                negativeVariable = false;
                listValues.add("-1");
                listValues.add("*");
                listValues.add(numberStr.toString());
                numberStr.setLength(0);
            }
        }
    }

    /**
     * Looks for positive variables in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondPositiveVariable(int i, char[] formCh) {
        if (i == 0 && LETTERS.contains(formCh[i])
                || i >= 1 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '('
                || i >= 2 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 1 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && NUMBERS.contains(formCh[i - 2])
                || i >= 2 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && LETTERS.contains(formCh[i - 2])
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^'
                || i >= 2 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '^'
                || positiveVariable
        ) {
            positiveVariable = true;
            numberStr.append(formCh[i]);

            if (checkIsFinishedVariable(i, formCh)) {
                positiveVariable = false;
                listValues.add(numberStr.toString());
                numberStr.setLength(0);
            }
        }
    }

    /**
     * Looks for operators in the text string +, -, *, /, ^, (, ).
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondOperator(int i, char[] formCh) {
        if (i >= 1 && i <= formCh.length - 1 && OPERATORS_FULL.contains(formCh[i]) && formCh[i - 1] != '(' && formCh[i - 1] != ')' && formCh[i - 1] != '^'
                || i > 0 && OPERATORS_FULL.contains(formCh[i]) && formCh[i - 1] == ')'
                || formCh[i] == '('
                || formCh[i] == ')'
        ) {
            listValues.add(numberStr.append(formCh[i]).toString());
            numberStr.setLength(0);
            checkBrackets(formCh, i);
        }
    }

    /**
     * Сhecks in the text string for errors.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void checkErrorInFormula(int i, char[] formCh) {
        if (i == 0 && OPERATORS.contains(formCh[i])
                || i == formCh.length - 1 && OPERATORS_FULL.contains(formCh[i])
                || i >= 0 && formCh[i] == '(' && formCh[i + 1] == ')'
                || i >= 0 && OPERATORS_FULL.contains(formCh[i]) && formCh[i + 1] == ')'
                || i >= 1 && formCh[i] == '-' && OPERATORS_FULL.contains(formCh[i + 1])
                || i >= 1 && formCh[i] == '(' && OPERATORS.contains(formCh[i + 1])
                || i >= 1 && OPERATORS.contains(formCh[i]) && OPERATORS.contains(formCh[i + 1])
                || i >= 1 && OPERATORS_FULL.contains(formCh[i]) && OPERATORS_FULL.contains(formCh[i + 1]) && OPERATORS_FULL.contains(formCh[i + 2])
        ) {
            throwException("Wrong operation!!!");
        }
    }

    private void checkBrackets(char[] formCh, int i) {
        if (formCh[i] == '(') {
            open++;
        } else if (formCh[i] == ')') {
            close++;
            if (close > open) {
                throwException("Incorrect input: open brackets are not equal to closed");
            }
        }
    }

    /**
     * Check for the end of a non-numeric variable.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     * @return end of non-numerical formula.
     */
    private boolean checkIsFinishedVariable(int i, char[] formCh) {
        return i < formCh.length - 1 && OPERATORS_FULL.contains(formCh[i + 1])
                || i < formCh.length - 1 && formCh[i + 1] == '('
                || i < formCh.length - 1 && formCh[i + 1] == ')'
                || i == formCh.length - 1 && LETTERS.contains(formCh[i])
                || i == formCh.length - 1 && NUMBERS.contains(formCh[i])
                || i == formCh.length - 1 && formCh[i] == '_'
                || i == formCh.length - 1 && formCh[i] == '$'
                || i == formCh.length - 1 && formCh[i] == ')';
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

