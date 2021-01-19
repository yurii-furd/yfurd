package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;

public class Assignment10Part1 {

    static boolean negativeNumber = false;
    static boolean positiveNumber = false;
    static boolean negativeVariable = false;
    static boolean positiveVariable = false;
    static Set<Character> operators = new HashSet<>(Arrays.asList('+', '*', '/', '^', '.', '_', '$'));
    static Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
    static Set<Character> letters = new HashSet<>(Arrays.asList('q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T', 'y', 'Y',
            'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's', 'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
            'j', 'J', 'k', 'K', 'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n', 'N', 'm', 'M'));
    static List<Double> listNumber = new ArrayList<>();
    static HashMap<String, Double> mapVariable = new HashMap<>();
    static List<String> listOperators = new ArrayList<>();
    static StringBuilder numberStr = new StringBuilder();
    static char[] formCh;
    static int a = 1;


    public static void main(String[] args) {
        System.out.println(args[0].trim());
        StringBuilder newForm = removeSpaces(args[0]);
        System.out.println(newForm);
        System.out.println(args.length);

        formCh = newForm.toString().toCharArray();

        if (formCh.length != 0) {
            for (int i = 0; i < formCh.length; i++) {

                findNegativeNumber(i);
                findPositiveNumber(i);
//                findNegativeVariable(i);
                findPositiveVariable(i);
                findOperator(i);
                checkErrorInFormula(i);

            }
        }
        System.out.println(mapVariable.size());
        System.out.println(mapVariable);
        System.out.println(listNumber);
        System.out.println(listOperators);
        System.out.println(args.length + " SSSSSSSSSS");

        if (args.length - mapVariable.size() == 1) {
            StringBuilder temp = new StringBuilder();
            StringBuilder tempValueDouble = new StringBuilder();

            for (int i = 1; i < args.length; i++) {
                StringBuilder param = removeSpaces(args[i]);
                formCh = param.toString().toCharArray();

                for (int j = 0; j < formCh.length; j++) {

                    if (j == 0 && letters.contains(formCh[j]) || positiveVariable) {
                        positiveVariable = true;
                        temp.append(formCh[j]);

                        if (formCh[j + 1] == '=') {
                            positiveVariable = false;
                        }
                    }
                    if (j > 0 && formCh[j - 1] == '=' && numbers.contains(formCh[j]) || positiveNumber){
                        positiveNumber = true;
                        checkDot(formCh[j]);
                        tempValueDouble.append(formCh[j]);
                        if (j == formCh.length - 1){
                            positiveNumber = false;
                            mapVariable.put(temp.toString(), Double.parseDouble(tempValueDouble.toString()));
                            a = 1;
                            temp = new StringBuilder();
                            tempValueDouble = new StringBuilder();
                        }
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        System.out.println(mapVariable);
    }

    private static void checkErrorInFormula(int i) {
        if (!numbers.contains(formCh[i]) && !operators.contains(formCh[i]) && !letters.contains(formCh[i]) && formCh[i] != '-'
                || operators.contains(formCh[i]) && operators.contains(formCh[i + 1]) && !positiveVariable && !negativeVariable
        ) {
            throw new IllegalArgumentException();
        }
    }

    private static void findOperator(int i) {
        if (i >= 1 && i <= formCh.length - 1 && operators.contains(formCh[i]) && !negativeNumber && !negativeVariable
                || i >= 1 && i <= formCh.length - 1 && formCh[i] == '-' && !negativeNumber && !negativeVariable) {
            listOperators.add(numberStr.append(formCh[i]).toString());
            numberStr = new StringBuilder();
        }
    }

    private static void findPositiveVariable(int i) {
        if (i == 0 && letters.contains(formCh[i])
                || i >= 1 && letters.contains(formCh[i]) && formCh[i - 1] == '+' && formCh.length > 2
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2]) && formCh.length > 3
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2]) && formCh.length > 3
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '*' && formCh.length > 3
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '/' && formCh.length > 3
                || i >= 3 && letters.contains(formCh[i]) && formCh[i - 1] == '-' && formCh[i - 2] == '^' && formCh.length > 4
                || i >= 2 && letters.contains(formCh[i]) && formCh[i - 1] == '^' && formCh.length > 3
                || positiveVariable
        ) {
            positiveVariable = true;
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && operators.contains(formCh[i + 1])
                    || i < formCh.length - 1 && formCh[i + 1] == '-'
                    || i == formCh.length - 1 && letters.contains(formCh[i])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                positiveVariable = false;
                mapVariable.put(numberStr.toString(), null);
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findNegativeVariable(int i) {
        if (i == 0 && formCh[i] == '-' && letters.contains(formCh[i + 1]) && formCh.length > 2
                || i >= 2 && formCh[i] == '-' && formCh[i - 1] == '*' && letters.contains(formCh[i + 1]) && formCh.length > 4
                || i >= 2 && formCh[i] == '-' && formCh[i - 1] == '/' && letters.contains(formCh[i + 1]) && formCh.length > 4
                || i >= 1 && formCh[i] == '-' && formCh[i - 1] == '+' && letters.contains(formCh[i + 1]) && formCh.length > 3
                || i >= 2 && formCh[i] == '-' && formCh[i - 1] == '^' && letters.contains(formCh[i + 1]) && formCh.length > 4
                || negativeVariable
        ) {
            negativeVariable = true;
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && operators.contains(formCh[i + 1])
                    || i == formCh.length - 1 && letters.contains(formCh[i])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                negativeVariable = false;
                mapVariable.put(numberStr.toString(), null);
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findPositiveNumber(int i) {
        if (i == 0 && numbers.contains(formCh[i])
                || i >= 1 && numbers.contains(formCh[i]) && formCh[i - 1] == '+' && formCh.length > 2
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && numbers.contains(formCh[i - 2]) && formCh.length > 3
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '-' && letters.contains(formCh[i - 2]) && formCh.length > 3
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '*' && formCh.length > 3
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '/' && formCh.length > 3
                || i >= 2 && numbers.contains(formCh[i]) && formCh[i - 1] == '^' && formCh.length > 3
                || positiveNumber
        ) {
            positiveNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !numbers.contains(formCh[i + 1])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                positiveNumber = false;
                a = 1;
                listNumber.add((Double.parseDouble(numberStr.toString())));
                numberStr = new StringBuilder();
            }
        }
    }

    private static void findNegativeNumber(int i) {
        if (i == 1 && formCh[i - 1] == '-' && numbers.contains(formCh[i]) && formCh.length > 2
                || i >= 3 && formCh[i - 1] == '-' && formCh[i - 2] == '*' && numbers.contains(formCh[i]) && formCh.length > 4
                || i >= 3 && formCh[i - 1] == '-' && formCh[i - 2] == '/' && numbers.contains(formCh[i]) && formCh.length > 4
                || i >= 2 && formCh[i - 1] == '-' && formCh[i - 2] == '+' && numbers.contains(formCh[i]) && formCh.length > 3
                || i >= 3 && formCh[i - 1] == '-' && formCh[i - 2] == '^' && numbers.contains(formCh[i]) && formCh.length > 4
                || negativeNumber
        ) {
            negativeNumber = true;
            checkDot(formCh[i]);
            numberStr.append(formCh[i]);

            if (i < formCh.length - 1 && !numbers.contains(formCh[i + 1])
                    || i == formCh.length - 1 && numbers.contains(formCh[i])
            ) {
                negativeNumber = false;
                a = 1;
                listNumber.add((Double.parseDouble(numberStr.toString())) * -1.0);
                numberStr = new StringBuilder();
            }
        }
    }

    private static void checkDot(char ch) {
        if (ch == '.' && a == 1) {
            a = 2;
        }
        if (ch == '.' && a == 2) {
            throw new IllegalArgumentException();
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
