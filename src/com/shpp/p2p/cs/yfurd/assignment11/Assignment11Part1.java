package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.*;

public class Assignment11Part1 {
    //"2*a^2+7*b^-3+5*c +b + 3*d"  "a= 2" "b = -2" "c=1" "d=1"
    //
    //"1+( 2+ 3*(4+ 5- sin(4 5 *cos (a)  ))) /7" "a= 2"
    //
    //"-sin(-1*(1+2)) + (2*(- a$ +9- 39*(4+5 -(SIN(45*(-cos(a)))))))/7" "a = -1" "a$ =5"
    //
    //-(1+2*3/4^5+(-6*7/(cos(8)^9+sin(tan(atan(sin(10)^11)/12)*13)+14-15*16))^17-18+(-19^(-20))*(-21)+22^23+tan(24)-sqrt(25)-26+27^28/29-30)/31^a+sqrt(sqrt(625)) a=36
    public static void main(String[] args) {
        Assignment11Part1 part1 = new Assignment11Part1();
        part1.fondValues(args[0]);
        System.out.println(listValues);

        part1.parseVariables(args);
        System.out.println(part1.variables);

        part1.groupToOneList(listValues, part1.variables);
        System.out.println(part1.calculateValue(part1.variables));
    }

    final Set<Character> operators = new HashSet<>(Arrays.asList('+', '*', '/', '^'));
    final Set<Character> operatorsFull = new HashSet<>(Arrays.asList('+', '*', '/', '^', '-'));
    final Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    final Set<Character> letters = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a',
            's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'));
    final Set<String> math = new HashSet<>(Arrays.asList("sin", "cos", "tan", "atan", "log10", "sqrt"));

    boolean negativeNumber = false;
    boolean positiveNumber = false;
    boolean negativeVariable = false;
    boolean positiveVariable = false;

    public static List<String> listValues = new LinkedList<>();
    Map<String, Double> variables = new HashMap<>();

    List<String> tempListValues;

    int count = 0;
    int open = 0;
    int close = 0;
    StringBuilder numberStr = new StringBuilder();

    /**
     * Calculation of the input expression.
     *
     * @param variables list of variables and values.
     * @return result of the input expression.
     */
    public double calculateValue(Map<String, Double> variables) {
        tempListValues = listValues;
        groupToOneList(tempListValues, variables);

        for (int i = 0; i < tempListValues.size(); i++) {
            String s = tempListValues.get(i);

            if (Objects.equals(s, "(")) {
                lookIsClosed(i);

            }
        }
        return Double.parseDouble(tempListValues.get(0));
    }

    private void lookIsClosed(int i) {
        for (int j = i + 1; j < tempListValues.size(); j++) {
            String s1 = tempListValues.get(j);
            if (Objects.equals(s1, "(")) {
                break;
            } else if (Objects.equals(s1, ")")) {
                calculate(i, j);
            }
        }
    }

    private void calculate(int i, int j) {
        int a = 1;
        List<String> listTemp = new ArrayList<>();
        for (int k = i + 1; k < j; k++) {
            listTemp.add(tempListValues.get(k));
            a++;
        }
        double res = calculateTempList(listTemp);

        for (int k = 0; k <= a; k++) {
            tempListValues.remove(i);
        }
        tempListValues.add(i, Double.toString(res));

        System.out.println(listTemp);
        System.out.println(tempListValues);

        calculateValue(variables);

    }

    public double calculateTempList(List<String> listTemp) {
        while (listTemp.size() != 1) {
            for (int i = 0; i < listTemp.size(); i++) {
                String s = listTemp.get(i);

                if (Objects.equals(s, "sin") ) {
                    double d = Math.sin(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "cos")) {
                    double d = Math.cos(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "tan")) {
                    double d = Math.tan(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "atan")) {
                    double d = Math.atan(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "log10")) {
                    double d = Math.log10(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "sqrt")) {
                    double d = Math.sqrt(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d, i + 1);
                    break;
                } else if (Objects.equals(s, "^") && isNotMath(listTemp)) {
                    calculate(listTemp, i, '^');
                    break;
                } else if (Objects.equals(s, "*") && !listTemp.contains("^") && isNotMath(listTemp)) {
                    calculate(listTemp, i, '*');
                    break;
                } else if (Objects.equals(s, "/") && !listTemp.contains("^") && !listTemp.contains("*") && isNotMath(listTemp)) {
                    calculate(listTemp, i, '/');
                    break;
                } else if (Objects.equals(s, "+") && !listTemp.contains("^") && !listTemp.contains("*") && !listTemp.contains("/") && isNotMath(listTemp)) {
                    calculate(listTemp, i, '+');
                    break;
                } else if (Objects.equals(s, "-") && !listTemp.contains("^") && !listTemp.contains("*") && !listTemp.contains("/") && !listTemp.contains("+") && isNotMath(listTemp)) {
                    calculate(listTemp, i, '-');
                    break;
                }
            }
        }
        return Double.parseDouble(listTemp.get(0));
    }

    private boolean isNotMath(List<String> listTemp) {
        return !listTemp.contains("sin") && !listTemp.contains("cos") && !listTemp.contains("tan") && !listTemp.contains("atan") && !listTemp.contains("log10") && !listTemp.contains("sqrt");
    }

    private void calculationMath(List<String> listTemp, int i, double d, int i2) {
        listTemp.add(i, Double.toString(d));
        listTemp.remove(i2);
        listTemp.remove(i2);
    }

    /**
     * This method, depending on the input parameter ch, somehow calculates the expression
     *
     * @param listTemp list of broken operators and operands.
     * @param i        the current position in the array of spells.
     * @param ch       the name of the operation to be performed with the two operands.
     */
    private void calculate(List<String> listTemp, int i, char ch) {
        double prev = Double.parseDouble(listTemp.get(i - 1));
        double next = Double.parseDouble(listTemp.get(i + 1));

        switch (ch) {
            case '^':
                insertCalculationNumbers(Math.pow(prev, next), i, listTemp);
                break;
            case '*':
                insertCalculationNumbers(prev * next, i, listTemp);
                break;
            case '/':
                insertCalculationNumbers(prev / next, i, listTemp);
                break;
            case '+':
                insertCalculationNumbers(prev + next, i, listTemp);
                break;
            case '-':
                insertCalculationNumbers(prev - next, i, listTemp);
                break;
        }
    }

    /**
     * Insert the calculated result into the sheet, and delete the operation and two operands.
     *
     * @param d        the calculated result of two numbers.
     * @param i        the current position in the array of spells.
     * @param listTemp list of broken operators and operands.
     */
    private void insertCalculationNumbers(double d, int i, List<String> listTemp) {
        listTemp.add(i - 1, Double.toString(d));
        listTemp.remove(i);
        listTemp.remove(i);
        listTemp.remove(i);

    }
//sqrt(25)-(-26)

    /**
     * This method replaces non-numerical media in the main list.
     *
     * @param tempListValues list of broken operators and operands.
     * @param tempVariables  list of broken operators and operands.
     */
    private void groupToOneList(List<String> tempListValues, Map<String, Double> tempVariables) {
        for (int i = 0; i < tempListValues.size(); i++) {
            String temp = tempListValues.get(i);

            if (!math.contains(temp)) {
                if (letters.contains(temp.charAt(0))) {
                    if (tempVariables.containsKey(temp)) {
                        tempListValues.add(i, tempVariables.get(temp).toString());
                    }
                    tempListValues.remove(i + 1);
                }
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
                        || j > 1 && tempChFormula[j - 1] == '=' && tempChFormula[j] == '-' && numbers.contains(tempChFormula[j + 1])
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
     * Looks for variables in the text string.
     *
     * @param args input information.
     */
    public void fondValues(String args) {
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
    }

    /**
     * Looks for negative numbers in the text string.
     *
     * @param i      the current position in the array of spells.
     * @param formCh the formula is divided into an array of spells.
     */
    private void fondNegativeNumber(int i, char[] formCh) {
        if (i == 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '('
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
        if (i == 0 && numbers.contains(formCh[i])
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == ')'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '('
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '+'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '*'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '/'
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2])
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2])
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_'
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$'
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
        if (i == 1 && letters.contains(formCh[i]) && formCh[i - 1] == '-'
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '('
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
        if (i == 0 && letters.contains(formCh[i])
                || i >= 1 && letters.contains(formCh[i]) && formCh[i - 1] == '('
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
        if (i >= 1 && i <= formCh.length - 1 && operatorsFull.contains(formCh[i]) && formCh[i - 1] != '(' && formCh[i - 1] != ')' && formCh[i - 1] != '^'
                || i > 0 && operatorsFull.contains(formCh[i]) && formCh[i - 1] == ')'
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
        if (i == 0 && operators.contains(formCh[i])
                || i == formCh.length - 1 && operatorsFull.contains(formCh[i])
                || i >= 0 && formCh[i] == '(' && formCh[i + 1] == ')'
                || i >= 0 && operatorsFull.contains(formCh[i]) && formCh[i + 1] == ')'
                || i >= 1 && formCh[i] == '-' && operatorsFull.contains(formCh[i + 1])
                || i >= 1 && formCh[i] == '(' && operators.contains(formCh[i + 1])
                || i >= 1 && operators.contains(formCh[i]) && operators.contains(formCh[i + 1])
                || i >= 1 && operatorsFull.contains(formCh[i]) && operatorsFull.contains(formCh[i + 1]) && operatorsFull.contains(formCh[i + 2])
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
        return i < formCh.length - 1 && operatorsFull.contains(formCh[i + 1])
                || i < formCh.length - 1 && formCh[i + 1] == '('
                || i < formCh.length - 1 && formCh[i + 1] == ')'
                || i == formCh.length - 1 && letters.contains(formCh[i])
                || i == formCh.length - 1 && numbers.contains(formCh[i])
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
