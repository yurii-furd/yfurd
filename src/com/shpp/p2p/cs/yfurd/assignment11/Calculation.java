package com.shpp.p2p.cs.yfurd.assignment11;

import java.util.*;

public class Calculation {

    final Set<String> math = new HashSet<>(Arrays.asList("sin", "cos", "tan", "atan", "log10", "sqrt"));
    List<String> tempListValues;
    Map<String, Double> variables;

    /**
     * Calculation of the input expression.
     *
     * @param variables list of variables and values.
     * @return result of the input expression.
     */
    public double calculateValue(List<String> listValues, Map<String, Double> variables) {
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

        calculateValue(tempListValues, variables);

    }

    public double calculateTempList(List<String> listTemp) {
        while (listTemp.size() != 1) {
            for (int i = 0; i < listTemp.size(); i++) {
                String s = listTemp.get(i);

                if (Objects.equals(s, "sin")) {
                    double d = Math.sin(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
                    break;
                } else if (Objects.equals(s, "cos")) {
                    double d = Math.cos(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
                    break;
                } else if (Objects.equals(s, "tan")) {
                    double d = Math.tan(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
                    break;
                } else if (Objects.equals(s, "atan")) {
                    double d = Math.atan(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
                    break;
                } else if (Objects.equals(s, "log10")) {
                    double d = Math.log10(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
                    break;
                } else if (Objects.equals(s, "sqrt")) {
                    double d = Math.sqrt(Double.parseDouble(listTemp.get(i + 1)));
                    calculationMath(listTemp, i, d);
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
     * Insert the calculated result into the sheet, and delete the operation and one operands.
     *
     * @param listTemp list of broken operators and operands.
     * @param i        the current position in the array of spells.
     * @param d        the calculated result of two numbers.
     */
    private void calculationMath(List<String> listTemp, int i, double d) {
        listTemp.add(i, Double.toString(d));
        listTemp.remove(i + 1);
        listTemp.remove(i + 1);
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
                if (Character.isLetter(temp.charAt(0))) {
                    if (tempVariables.containsKey(temp)) {
                        tempListValues.add(i, tempVariables.get(temp).toString());
                    }
                    tempListValues.remove(i + 1);
                }
            }
        }
    }
}
