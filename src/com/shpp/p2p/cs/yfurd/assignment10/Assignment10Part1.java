package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;

public class Assignment10Part1 {

    final Set<Character> operators = new HashSet<>(Arrays.asList('+', '*', '/', '^'));
    final Set<Character> operatorsFull = new HashSet<>(Arrays.asList('+', '*', '/', '^', '-'));
    final Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    final Set<Character> letters = new HashSet<>(Arrays.asList('q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T',
            'y', 'Y', 'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's', 'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n', 'N', 'm', 'M'));
    boolean negativeNumber = false;
    boolean positiveNumber = false;
    boolean negativeVariable = false;
    boolean positiveVariable = false;

    public static List<String> listValues = new LinkedList<>();
    Map<String, Double> variables = new HashMap<>();

    int count = 0;
    int a = 1;
    StringBuilder numberStr = new StringBuilder();

    public double calculateValue(Map<String, Double> variables) {
        List<String> tempListValues = listValues;
        groupToOneList(tempListValues);
        System.out.println(tempListValues);
        System.out.println(variables);
        while (count != 1) {
            for (int i = 0; i < tempListValues.size(); i++) {
                String s = tempListValues.get(i);

                if (Objects.equals(s, "^")) {
                    checkIsPresenceParameters(tempListValues, i, '^');
                    break;
                } else if (Objects.equals(s, "*") && !tempListValues.contains("^")) {
                    checkIsPresenceParameters(tempListValues, i, '*');
                    break;
                } else if (Objects.equals(s, "/") && !tempListValues.contains("^") && !tempListValues.contains("*")) {
                    checkIsPresenceParameters(tempListValues, i, '/');
                    break;
                } else if (Objects.equals(s, "+") && !tempListValues.contains("^") && !tempListValues.contains("*") && !tempListValues.contains("/")) {
                    checkIsPresenceParameters(tempListValues, i, '+');
                    break;
                } else if (Objects.equals(s, "-") && !tempListValues.contains("^") && !tempListValues.contains("*") && !tempListValues.contains("/") && !tempListValues.contains("+")) {
                    checkIsPresenceParameters(tempListValues, i, '-');
                    break;
                }
            }
        }
        return Double.parseDouble(tempListValues.get(0));
    }

    private void checkIsPresenceParameters(List<String> tempListValues, int i, char ch) {
        double prev = Double.parseDouble(tempListValues.get(i - 1));
        double next = Double.parseDouble(tempListValues.get(i + 1));

        if (ch == '^') {
            insertCalculationNumbers(Math.pow(prev, next), i, tempListValues);
        } else if (ch == '*') {
            insertCalculationNumbers(prev * next, i, tempListValues);
        } else if (ch == '/') {
            insertCalculationNumbers(prev / next, i, tempListValues);
        } else if (ch == '+') {
            insertCalculationNumbers(prev + next, i, tempListValues);
        } else if (ch == '-') {
            insertCalculationNumbers(prev - next, i, tempListValues);
        }
    }

    private void insertCalculationNumbers(double d, int i, List<String> tempListValues) {
        tempListValues.add(i - 1, Double.toString(d));
        tempListValues.remove(i);
        tempListValues.remove(i);
        tempListValues.remove(i);
        count--;
        count--;
    }

    private void groupToOneList(List<String> tempListValues) {
        for (int i = 0; i < tempListValues.size(); i++) {
            String temp = tempListValues.get(i);
            if (letters.contains(temp.charAt(0)) || temp.charAt(0) == '-' && letters.contains(temp.charAt(1))) {
                if (variables.containsKey(temp)) {
                    tempListValues.add(i, variables.get(temp).toString());
                } else {
                    Double d = variables.get(temp.substring(1));
                    if (d > 0 ){
                        tempListValues.add(i, "-" + variables.get(temp.substring(1)).toString());
                    } else {
                        tempListValues.add(i, variables.get(temp.substring(1)).toString().substring(1));
                    }
                }
                tempListValues.remove(i + 1);
            }
        }
    }

    public Map<String, Double> parseVariables(String[] args) {
        StringBuilder tempKey = new StringBuilder();
        StringBuilder tempValue = new StringBuilder();
        char[] tempChFormula;
        for (int i = 1; i < args.length; i++) {
            tempChFormula = removeSpaces(args[i]).toString().toCharArray();

            for (int j = 0; j < tempChFormula.length; j++) {

                if (tempChFormula.length < 3 || !letters.contains(tempChFormula[0])
                        || !numbers.contains(tempChFormula[tempChFormula.length - 1])) {
                    throwException("Wrong input parameters!!!");
                } else if (j == 0 && letters.contains(tempChFormula[j]) || positiveVariable) {
                    positiveVariable = true;
                    tempKey.append(tempChFormula[j]);
                    if (tempChFormula[j + 1] == '=') {
                        positiveVariable = false;
                    }
                } else if (j > 1 && tempChFormula[j - 1] == '=' && numbers.contains(tempChFormula[j])
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

    public void fondValues(String args) {
        StringBuilder newForm = removeSpaces(args);
        char[] formCh = newForm.toString().toCharArray();

        if (formCh.length != 0) {
            for (int i = 0; i < formCh.length; i++) {
                fondNegativeNumber(i, formCh);
                fondPositiveNumber(i, formCh);
                fondNegativeVariable(i, formCh);
                fondPositiveVariable(i, formCh);
                fondOperator(i, formCh);
                checkErrorInFormula(i, formCh);
            }
        }
    }

    private void fondNegativeNumber(int i, char[] formCh) {
        if (i == 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^'
                || negativeNumber
        ) {
            negativeNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !numbers.contains(formCh[i + 1]) && formCh[i + 1] != '.'
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                negativeNumber = false;
                a = 1;
                listValues.add("-" + numberStr.toString());
                count++;
                numberStr.setLength(0);
            }
        }
    }

    private void fondPositiveNumber(int i, char[] formCh) {
        if (i == 0 && numbers.contains(formCh[i])
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2])
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2])
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '^'
                || positiveNumber
        ) {
            positiveNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !numbers.contains(formCh[i + 1]) && formCh[i + 1] != '.'
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                positiveNumber = false;
                a = 1;
                listValues.add(numberStr.toString());
                count++;
                numberStr.setLength(0);
            }
        }
    }

    private void fondNegativeVariable(int i, char[] formCh) {
        if (i == 1 && letters.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^'
                || negativeVariable
        ) {
            negativeVariable = true;
            numberStr.append(formCh[i]);

            if (checkIsFinishedVariable(i, formCh)) {
                negativeVariable = false;
                listValues.add("-" + numberStr.toString());
                count++;
                numberStr.setLength(0);
            }
        }
    }

    private void fondPositiveVariable(int i, char[] formCh) {
        if (i == 0 && letters.contains(formCh[i])
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 1 && letters.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2])
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2])
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^'
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '^'
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

    private void fondOperator(int i, char[] formCh) {
        if (i >= 1 && i <= formCh.length - 1 && operatorsFull.contains(formCh[i]) && !operatorsFull.contains(formCh[i - 1])) {
            listValues.add(numberStr.append(formCh[i]).toString());
            count++;
            numberStr.setLength(0);
        }
    }

    private void checkErrorInFormula(int i, char[] formCh) {
        if (i == 0 && formCh[i] == '*'
                || i == 0 && formCh[i] == '/'
                || i == 0 && formCh[i] == '^'
                || i == formCh.length - 1 && operatorsFull.contains(formCh[i])
                || i >= 1 && formCh[i] == '-' && operatorsFull.contains(formCh[i + 1])
                || i >= 1 && formCh[i] == '-' && formCh[i + 1] == '-'
                || i >= 1 && operators.contains(formCh[i]) && operators.contains(formCh[i + 1])
                || i >= 1 && operatorsFull.contains(formCh[i]) && operatorsFull.contains(formCh[i + 1]) && operatorsFull.contains(formCh[i + 2])
        ) {
            throwException("Wrong operation!!!");
        }
    }

    private boolean checkIsFinishedVariable(int i, char[] formCh) {
        return i < formCh.length - 1 && operators.contains(formCh[i + 1])
                || i < formCh.length - 1 && formCh[i + 1] == '-'
                || i == formCh.length - 1 && letters.contains(formCh[i])
                || i == formCh.length - 1 && numbers.contains(formCh[i])
                || i == formCh.length - 1 && formCh[i] == '_'
                || i == formCh.length - 1 && formCh[i] == '$';
    }

    private void checkDot(char ch) {
        if (ch == '.' && a == 2) {
            throwException("Wrong number!!!");
        }
        if (ch == '.' && a == 1) {
            a = 2;
        }
    }

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

    private void throwException(String s) {
        System.out.println(s);
        throw new NumberFormatException();
    }
}
