package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;

public class Assignment10Part1 {

    //"1 + a^2  * 2 + 12 * 2 + b"  "a= 2" "b = -88"
    public static void main(String[] args) {
        Assignment10Part1 part1 = new Assignment10Part1();
        part1.fondValues(args[0]);
        Map<String, Double> variables = part1.parseVariables(args);
        double res = part1.calculateValue(variables);
        System.out.println("-> " + res);
    }
    
    final Set<Character> OPERATORS = new HashSet<>(Arrays.asList('+', '*', '/', '^'));
    final Set<Character> OPERATORS_FULL = new HashSet<>(Arrays.asList('+', '*', '/', '^', '-'));
    final Set<Character> NUMBERS = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    final Set<Character> LETTERS = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't','y', 'u', 'i', 'o', 'p', 'a',
            's', 'd', 'f', 'g', 'h','j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'));
    boolean negativeNumber = false;
    boolean positiveNumber = false;
    boolean negativeVariable = false;
    boolean positiveVariable = false;

    public static List<String> listValues = new LinkedList<>();
    Map<String, Double> variables = new HashMap<>();

    int count = 0;
    int a = 1;
    StringBuilder numberStr = new StringBuilder();

    /**
     * Calculation of the input expression.
     *
     * @param variables list of variables and values.
     * @return result of the input expression.
     */
    public double calculateValue(Map<String, Double> variables) {
        List<String> tempListValues = listValues;
        groupToOneList(tempListValues, variables);
        while (count != 1) {
            for (int i = 0; i < tempListValues.size(); i++) {
                String s = tempListValues.get(i);

                if (Objects.equals(s, "^")) {
                    calculate(tempListValues, i, '^');
                    break;
                } else if (Objects.equals(s, "*") && !tempListValues.contains("^")) {
                    calculate(tempListValues, i, '*');
                    break;
                } else if (Objects.equals(s, "/") && !tempListValues.contains("^") && !tempListValues.contains("*")) {
                    calculate(tempListValues, i, '/');
                    break;
                } else if (Objects.equals(s, "+") && !tempListValues.contains("^") && !tempListValues.contains("*") && !tempListValues.contains("/")) {
                    calculate(tempListValues, i, '+');
                    break;
                } else if (Objects.equals(s, "-") && !tempListValues.contains("^") && !tempListValues.contains("*") && !tempListValues.contains("/") && !tempListValues.contains("+")) {
                    calculate(tempListValues, i, '-');
                    break;
                }
            }
        }
        return Double.parseDouble(tempListValues.get(0));
    }

    /**
     * This method, depending on the input parameter ch, somehow calculates the expression
     *
     * @param tempListValues list of broken operators and operands.
     * @param i              the current position in the array of spells.
     * @param ch             the name of the operation to be performed with the two operands.
     */
    private void calculate(List<String> tempListValues, int i, char ch) {
        double prev = Double.parseDouble(tempListValues.get(i - 1));
        double next = Double.parseDouble(tempListValues.get(i + 1));

        switch (ch) {
            case '^':
                insertCalculationNumbers(Math.pow(prev, next), i, tempListValues);
                break;
            case '*':
                insertCalculationNumbers(prev * next, i, tempListValues);
                break;
            case '/':
                insertCalculationNumbers(prev / next, i, tempListValues);
                break;
            case '+':
                insertCalculationNumbers(prev + next, i, tempListValues);
                break;
            case '-':
                insertCalculationNumbers(prev - next, i, tempListValues);
                break;
        }
    }

    /**
     * Insert the calculated result into the sheet, and delete the operation and two operands.
     *
     * @param d              the calculated result of two numbers.
     * @param i              the current position in the array of spells.
     * @param tempListValues list of broken operators and operands.
     */
    private void insertCalculationNumbers(double d, int i, List<String> tempListValues) {
        tempListValues.add(i - 1, Double.toString(d));
        tempListValues.remove(i);
        tempListValues.remove(i);
        tempListValues.remove(i);
        count--;
        count--;
    }

    /**
     * This method replaces non-numerical media in the main list.
     *
     * @param tempListValues list of broken operators and operands.
     * @param tempVariables  list of broken operators and operands.
     */
    private void groupToOneList(List<String> tempListValues, Map<String, Double> tempVariables) {
        for (int i = 0; i < tempListValues.size(); i++) {
            String temp = tempListValues.get(i);
            if (LETTERS.contains(temp.charAt(0))) {
                if (tempVariables.containsKey(temp)) {
                    tempListValues.add(i, tempVariables.get(temp).toString());
                }
                tempListValues.remove(i + 1);
            }
        }
    }

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
            tempChFormula = removeSpaces(args[i]).toString().toLowerCase().toCharArray();

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
                        || j > 1 && tempChFormula[j - 1] == '=' && tempChFormula[j] == '-'
                        || positiveNumber
                ) {
                    positiveNumber = true;
                    checkDot(tempChFormula[j]);
                    tempValue.append(tempChFormula[j]);
                    if (j == tempChFormula.length - 1) {
                        positiveNumber = false;
                        variables.put(tempKey.toString(), Double.parseDouble(tempValue.toString()));
                        a = 1;
                        tempKey.setLength(0);
                        tempValue.setLength(0);
                    }
                }
            }
        }
        if (args.length - variables.size() != 1) {
            throwException("Wrong input parameters!!!");
        }
        return variables;
    }

    /**
     * Looks for variables in the text string.
     *
     * @param args input information.
     */
    public void fondValues(String args) {
        StringBuilder newForm = removeSpaces(args);
        char[] formCh = newForm.toString().toLowerCase().toCharArray();

        if (formCh.length != 0) {
            for (int i = 0; i < formCh.length; i++) {
                fondNegativeNumber(i, formCh);
                fondPositiveNumber(i, formCh);
                fondNegativeVariable(i, formCh);
                fondPositiveVariable(i, formCh);
                fondOperator(i, formCh);
                checkErrorInFormula(i, formCh);
            }
            System.out.println(listValues);
        }
    }

    /**
     * Looks for negative numbers in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondNegativeNumber(int i, char[] formCh) {
        if (i == 1 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+'
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
                a = 1;
                listValues.add("-" + numberStr.toString());
                count++;
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
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 1 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && NUMBERS.contains(formCh[i - 2])
                || i >= 2 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && LETTERS.contains(formCh[i - 2])
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
                || i >= 3 && NUMBERS.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^'
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
                a = 1;
                listValues.add(numberStr.toString());
                count++;
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
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+'
                || i >= 3 && LETTERS.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^'
                || negativeVariable
        ) {
            negativeVariable = true;
            numberStr.append(formCh[i]);

            if (checkIsFinishedVariable(i, formCh)) {
                negativeVariable = false;
                listValues.add("-1");
                listValues.add("*");
                listValues.add(numberStr.toString());
                count++;
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
                count++;
                numberStr.setLength(0);
            }
        }
    }

    /**
     * Looks for operators in the text string +, -, *, /, ^.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondOperator(int i, char[] formCh) {
        if (i >= 1 && i <= formCh.length - 1 && OPERATORS_FULL.contains(formCh[i]) && !OPERATORS_FULL.contains(formCh[i - 1])) {
            listValues.add(numberStr.append(formCh[i]).toString());
            count++;
            numberStr.setLength(0);
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
                || i >= 1 && formCh[i] == '-' && OPERATORS_FULL.contains(formCh[i + 1])
                || i >= 1 && OPERATORS.contains(formCh[i]) && OPERATORS.contains(formCh[i + 1])
                || i >= 1 && OPERATORS_FULL.contains(formCh[i]) && OPERATORS_FULL.contains(formCh[i + 1]) && OPERATORS_FULL.contains(formCh[i + 2])
        ) {
            throwException("Wrong operation!!!");
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
                || i == formCh.length - 1 && LETTERS.contains(formCh[i])
                || i == formCh.length - 1 && NUMBERS.contains(formCh[i])
                || i == formCh.length - 1 && formCh[i] == '_'
                || i == formCh.length - 1 && formCh[i] == '$';
    }

    /**
     * This method checks to see if there are enough dots in the number.
     *
     * @param ch the current position in the array of spells.
     */
    private void checkDot(char ch) {
        if (ch == '.' && a == 2) {
            throwException("Wrong number!!!");
        }
        if (ch == '.' && a == 1) {
            a = 2;
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
