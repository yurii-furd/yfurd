package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;
//"(-   1   +   (-valujk6459l6e)   * ((  2  - 2  ^(-  10)) + 50) - a)/-2 *a"
public class Assignment10Part1 {

    public static void main(String[] args) {
        Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
        Set<Character> letters = new HashSet<>(Arrays.asList('q', 'Q', 'w', 'W', 'e', 'E', 'r', 'R', 't', 'T', 'y', 'Y',
                'u', 'U', 'i', 'I', 'o', 'O', 'p', 'P', 'a', 'A', 's', 'S', 'd', 'D', 'f', 'F', 'g', 'G', 'h', 'H',
                'j', 'J', 'k', 'K', 'l', 'L', 'z', 'Z', 'x', 'X', 'c', 'C', 'v', 'V', 'b', 'B', 'n', 'N', 'm', 'M'));

        Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '^', '.', '(', ')'));

        List<Double> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();

        System.out.println(args[0].trim());

        StringBuilder newForm = removeSpaces(args[0]);

        System.out.println(newForm);

        char[] newFormCh = newForm.toString().toCharArray();

        boolean negativeNumber = false;
        boolean positiveNumber = false;
        boolean negativeVariable = false;
        boolean positiveVariable = false;

        StringBuilder numberStr = new StringBuilder();
        int a = 1;

        if (newFormCh.length != 0) {
            for (int i = 0; i < newFormCh.length; i++) {

                if (i == 1 && newFormCh[i - 1] == '-' && numbers.contains(newFormCh[i]) && newFormCh.length > 2
                        || i >= 3 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '*' && numbers.contains(newFormCh[i]) && newFormCh.length > 4
                        || i >= 3 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '/' && numbers.contains(newFormCh[i]) && newFormCh.length > 4
                        || i >= 2 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '+' && numbers.contains(newFormCh[i]) && newFormCh.length > 3
                        || negativeNumber
                ) {
                    negativeNumber = true;
                    checkDot(newFormCh[i], a);
                    numberStr.append(newFormCh[i]);

                    if (i < newFormCh.length - 1 && !numbers.contains(newFormCh[i + 1])
                            || i == newFormCh.length - 1 && numbers.contains(newFormCh[i])
                    ) {
                        negativeNumber = false;
                        a = 1;
                        list.add((Double.parseDouble(numberStr.toString())) * -1.0);
                        numberStr = new StringBuilder();
                    }
                }

                if (i == 0 && numbers.contains(newFormCh[i])
                        || i >= 1 && numbers.contains(newFormCh[i]) && newFormCh[i - 1] == '+' && newFormCh.length > 2
                        || i >= 2 && numbers.contains(newFormCh[i]) && newFormCh[i - 1] == '-' && numbers.contains(newFormCh[i - 2]) && newFormCh.length > 3
                        || i >= 2 && numbers.contains(newFormCh[i]) && newFormCh[i - 1] == '-' && letters.contains(newFormCh[i - 2]) && newFormCh.length > 3
                        || i >= 2 && numbers.contains(newFormCh[i]) && newFormCh[i - 1] == '*' && newFormCh.length > 3
                        || i >= 2 && numbers.contains(newFormCh[i]) && newFormCh[i - 1] == '/' && newFormCh.length > 3
                        || positiveNumber
                ) {
                    positiveNumber = true;
                    checkDot(newFormCh[i], a);
                    numberStr.append(newFormCh[i]);

                    if (i < newFormCh.length - 1 && !numbers.contains(newFormCh[i + 1])
                            || i == newFormCh.length - 1 && numbers.contains(newFormCh[i])
                    ) {
                        positiveNumber = false;
                        a = 1;
                        list.add((Double.parseDouble(numberStr.toString())));
                        numberStr = new StringBuilder();
                    }
                }

                if (i == 0 && newFormCh[i] == '-' && letters.contains(newFormCh[i + 1]) && newFormCh.length > 2
                        || i >= 2 && newFormCh[i] == '-' && newFormCh[i - 1] == '*' && letters.contains(newFormCh[i + 1]) && newFormCh.length > 4
                        || i >= 2 && newFormCh[i] == '-' && newFormCh[i - 1] == '/' && letters.contains(newFormCh[i + 1]) && newFormCh.length > 4
                        || i >= 1 && newFormCh[i] == '-' && newFormCh[i - 1] == '+' && letters.contains(newFormCh[i + 1]) && newFormCh.length > 3
                        || negativeVariable
                ) {
                    negativeVariable = true;
                    numberStr.append(newFormCh[i]);

                    if (i < newFormCh.length - 1 && operators.contains(newFormCh[i + 1])
                    || i == newFormCh.length - 1 && letters.contains(newFormCh[i])
                    || i == newFormCh.length - 1 && numbers.contains(newFormCh[i])
                    ) {
                        negativeVariable = false;
                        list1.add(numberStr.toString());
                        numberStr = new StringBuilder();
                    }
                }

                if (i == 0 && letters.contains(newFormCh[i])
                        || i >= 1 && letters.contains(newFormCh[i]) && newFormCh[i - 1] == '+' && newFormCh.length > 2
                        || i >= 2 && letters.contains(newFormCh[i]) && newFormCh[i - 1] == '-' && letters.contains(newFormCh[i - 2]) && newFormCh.length > 3
                        || i >= 2 && letters.contains(newFormCh[i]) && newFormCh[i - 1] == '-' && numbers.contains(newFormCh[i - 2]) && newFormCh.length > 3
                        || i >= 2 && letters.contains(newFormCh[i]) && newFormCh[i - 1] == '*' && newFormCh.length > 3
                        || i >= 2 && letters.contains(newFormCh[i]) && newFormCh[i - 1] == '/' && newFormCh.length > 3
                        || positiveVariable
                ) {
                    positiveVariable = true;
                    numberStr.append(newFormCh[i]);

                    if (i < newFormCh.length - 1 && operators.contains(newFormCh[i + 1])
                            || i == newFormCh.length - 1 && letters.contains(newFormCh[i])
                            || i == newFormCh.length - 1 && numbers.contains(newFormCh[i])) {
                        positiveVariable = false;
                        list1.add(numberStr.toString());
                        numberStr = new StringBuilder();
                    }
                }
            }
        }
        System.out.println(list);
        System.out.println(list1);
    }

    private static void checkDot(char ch, int a) {
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
