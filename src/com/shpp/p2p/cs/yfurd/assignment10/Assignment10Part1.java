package com.shpp.p2p.cs.yfurd.assignment10;

import java.util.*;

public class Assignment10Part1 {

    public static void main(String[] args) {
        Set<Character> numbers = new HashSet<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0'));
        Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '^', '.', '(', ')'));

        List<Double> list = new ArrayList<>();

        System.out.println(args[0].trim());

        StringBuilder newForm = removeSpaces(args[0]);

        System.out.println(newForm);
        System.out.println(20 - 10);


        char[] newFormCh = newForm.toString().toCharArray();

        boolean negativeNumber = false;
        StringBuilder numberStr = new StringBuilder();
        double number = 0.0;
        int a = 1;

        if (newFormCh.length != 0) {
            for (int i = 0; i < newFormCh.length; i++) {

                if (i == 1 && newFormCh[i - 1] == '-' && numbers.contains(newFormCh[i]) && newFormCh.length > 2
                        || i >= 3 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '*' && numbers.contains(newFormCh[i]) && newFormCh.length > 4
                        || i >= 3 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '/' && numbers.contains(newFormCh[i]) && newFormCh.length > 4
                        || i >= 2 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '(' && numbers.contains(newFormCh[i]) && newFormCh.length > 3
                        || i >= 2 && newFormCh[i - 1] == '-' && newFormCh[i - 2] == '+' && numbers.contains(newFormCh[i]) && newFormCh.length > 3
                        || negativeNumber
                ) {
                    negativeNumber = true;

                    if (newFormCh[i] == '.' && a == 1) {
                        a = 2;
                    }
                    if (newFormCh[i] == '.' && a == 2) {
                        throw new IllegalArgumentException();
                    }
                    numberStr.append(newFormCh[i]);

                    if (i != newFormCh[newFormCh.length - 1] && !numbers.contains(newFormCh[i + 1])) {
                        negativeNumber = false;
                        a = 1;
                        list.add((number = Double.parseDouble(numberStr.toString())) * -1.0);
                        numberStr = new StringBuilder();
                    }
                }
            }
        }
        System.out.println(list);
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

    private static double calculate(String formula, HashMap<String, Double> variables) {
        return 0;
    }

}
