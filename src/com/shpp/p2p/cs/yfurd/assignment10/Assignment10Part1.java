package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;

public class Assignment10Part1 {

    static boolean negativeNumber = false;
    static boolean positiveNumber = false;
    static boolean negativeVariable = false;
    static boolean positiveVariable = false;
    static Set<Character> operators = new HashSet<>(Arrays.asList('+', '*', '/', '^'));
    static Set<Character> operatorsFull = new HashSet<>(Arrays.asList('+', '*', '/', '^', '-'));
    static Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    static Set<Character> letters = new HashSet<>(Arrays.asList('q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T', 'y',
            'Y', 'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's', 'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n', 'N', 'm', 'M'));

    static Map<Integer, String> mapNumber = new LinkedHashMap<>();
    static Map<String, String> tempMap = new LinkedHashMap<>();

    static StringBuilder numberStr = new StringBuilder();
    static char[] formCh;
    static int a = 1;
    static int count = 0;


    public static void main(String[] args) {
        StringBuilder newForm = removeSpaces(args[0]);
        formCh = newForm.toString().toCharArray();
        if (formCh.length != 0) {
            for (int i = 0; i < formCh.length; i++) {

                findNegativeNumber(i);
                findPositiveNumber(i);
                findNegativeVariable(i);
                findPositiveVariable(i);
                findOperator(i);
                checkErrorInFormula(i);
            }
        }
        System.out.println(formCh);////////////////////////////////////////////////////////////////////////////////////
        System.out.println();//////////////////////////////////////////////////////////////////////////////////////////

        writeVariableToDb(args);

        System.out.println(tempMap);///////////////////////////////////////////////////////////////////////////////
        System.out.println(mapNumber);
    }

    private static void writeVariableToDb(String[] args) {
        if (args.length - tempMap.size() == 1) {

            StringBuilder tempKey = new StringBuilder();
            StringBuilder tempValue = new StringBuilder();
            char[] tempChFormula;

            for (int i = 1; i < args.length; i++) {
                tempChFormula = removeSpaces(args[i]).toString().toCharArray();

                for (int j = 0; j < tempChFormula.length; j++) {

                    if (tempChFormula.length < 3
                            || !letters.contains(tempChFormula[0])
                            || !numbers.contains(tempChFormula[tempChFormula.length - 1])
                    ) {
                        System.out.println("Wrong input parameters!!!");
                        throw new NumberFormatException();

                    }

                    if (j == 0 && letters.contains(tempChFormula[j]) || positiveVariable) {
                        positiveVariable = true;
                        tempKey.append(tempChFormula[j]);

                        if (tempChFormula[j + 1] == '=') {
                            positiveVariable = false;
                        }
                    }

                    if (j > 1 && tempChFormula[j - 1] == '=' && numbers.contains(tempChFormula[j])
                            || j > 1 && tempChFormula[j - 1] == '=' && tempChFormula[j] == '-'
                            || positiveNumber
                    ) {
                        positiveNumber = true;
                        checkDot(tempChFormula[j]);
                        tempValue.append(tempChFormula[j]);
                        if (j == tempChFormula.length - 1) {
                            positiveNumber = false;
                            if (tempMap.containsKey("-" + tempKey.toString())) {
                                if (tempValue.charAt(0) == '-') {
                                    tempMap.put(tempKey.toString(), tempValue.deleteCharAt(0).toString());
                                    tempMap.remove("-" + tempKey.toString());
                                } else {
                                    tempMap.put("-" + tempKey.toString(), "-" + tempValue.toString());
                                }
                            } else {
                                tempMap.put(tempKey.toString(), tempValue.toString());
                            }
                            a = 1;
                            tempKey = new StringBuilder();
                            tempValue = new StringBuilder();
                        }
                    }
                }
            }
        } else {
            System.out.println("Wrong input parameters!!!");
            throw new NumberFormatException();
        }
        if (args.length - tempMap.size() != 1) {
            System.out.println("Wrong input parameters!!!");
            throw new NumberFormatException();
        }

        for (int i = 0; i < mapNumber.size(); i++) {
            String temp = mapNumber.get(i);
            StringBuilder tempStr = new StringBuilder(temp);
            if (letters.contains(temp.charAt(0))
                    || temp.length() > 1 && temp.charAt(0) == '-' && letters.contains(temp.charAt(1))
            ) {
                String tempstr;
                if (tempMap.containsKey(tempStr.toString())) {
                    tempstr = tempMap.get(tempStr.toString());
                } else {
                    tempstr = tempMap.get(tempStr.deleteCharAt(0).toString());
                }
                mapNumber.put(i, tempstr);
            }
        }
    }

    private static void checkErrorInFormula(int i) {
        if (i == 0 && formCh[i] == '*'
                || i == 0 && formCh[i] == '/'
                || i == 0 && formCh[i] == '^'
                || i == formCh.length - 1 && operatorsFull.contains(formCh[i])
                || i >= 1 && formCh[i] == '-' && operatorsFull.contains(formCh[i + 1]) && formCh.length > 2
                || i >= 1 && formCh[i] == '-' && formCh[i + 1] == '-'
                || i >= 1 && operators.contains(formCh[i]) && operators.contains(formCh[i + 1]) && formCh.length > 2
                || i >= 1 && operatorsFull.contains(formCh[i]) && operatorsFull.contains(formCh[i + 1]) && operatorsFull.contains(formCh[i + 2]) && formCh.length > 3
        ) {
            System.out.println("Wrong operation!!!");
            throw new NumberFormatException();
        }
    }

    private static void findOperator(int i) {
        if (i >= 1 && i <= formCh.length - 1 && operatorsFull.contains(formCh[i]) && !operatorsFull.contains(formCh[i - 1])) {
            mapNumber.put(count, numberStr.append(formCh[i]).toString());
            count++;
            numberStr = new StringBuilder();
        }
    }

    private static void findPositiveVariable(int i) {
        if (i == 0 && letters.contains(formCh[i])
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '*' && formCh.length > 2
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '/' && formCh.length > 2
                || i >= 1 && letters.contains(formCh[i]) && formCh[i - 1] == '+' && formCh.length > 1
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2]) && formCh.length > 2
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2]) && formCh.length > 2
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^' && formCh.length > 3
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '^' && formCh.length > 2
                || positiveVariable
        ) {
            positiveVariable = true;
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && operators.contains(formCh[i + 1])
                    || i < formCh.length - 1 && formCh[i + 1] == '-'
                    || i == formCh.length - 1 && letters.contains(formCh[i])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
                    || i == formCh.length - 1 && formCh[i] == '_'
                    || i == formCh.length - 1 && formCh[i] == '$'
            ) {
                positiveVariable = false;
                tempMap.put(numberStr.toString(), null);///////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                mapNumber.put(count, numberStr.toString());
                count++;
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findNegativeVariable(int i) {
        if (i == 1 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh.length > 1
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^' && formCh.length > 3
                || negativeVariable
        ) {
            negativeVariable = true;
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && operators.contains(formCh[i + 1])
                    || i < formCh.length - 1 && formCh[i + 1] == '-'
                    || i == formCh.length - 1 && letters.contains(formCh[i])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
                    || i == formCh.length - 1 && formCh[i] == '_'
                    || i == formCh.length - 1 && formCh[i] == '$'
            ) {
                negativeVariable = false;
                tempMap.put("-" + numberStr.toString(), null);///////////////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                mapNumber.put(count, "-" + numberStr.toString());
                count++;
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findPositiveNumber(int i) {
        if (i == 0 && numbers.contains(formCh[i])
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '*' && formCh.length > 2
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '/' && formCh.length > 2
                || i >= 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '+' && formCh.length > 1
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2]) && formCh.length > 2
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2]) && formCh.length > 2
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '_' && formCh.length > 3
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '$' && formCh.length > 3
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '+' && formCh[i - 2] == '^' && formCh.length > 3
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '^' && formCh.length > 2
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
                mapNumber.put(count, numberStr.toString());
                count++;
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findNegativeNumber(int i) {
        if (i == 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh.length > 1
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '*' && formCh.length > 3
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '/' && formCh.length > 3
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '+' && formCh.length > 3
                || i >= 3 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^' && formCh.length > 3
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
                mapNumber.put(count, "-" + numberStr.toString());
                count++;
                numberStr = new StringBuilder();
            }
        }
    }

    private static void checkDot(char ch) {
        if (ch == '.' && a == 2) {
            System.out.println("Wrong number!!!");
            throw new NumberFormatException();
        }
        if (ch == '.' && a == 1) {
            a = 2;
        }
    }

    private static StringBuilder removeSpaces(String str) {
        char[] ch = str.trim().toCharArray();
        StringBuilder newForm = new StringBuilder();
        for (char c : ch) {
            if (c != ' ') {
                newForm.append(c);
            }
        }
        return newForm;
    }
}
