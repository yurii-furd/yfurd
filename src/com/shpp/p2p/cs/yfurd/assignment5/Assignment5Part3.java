package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment5Part3 extends TextProgram {

    public void run() {
        while (true) {
            String s = readLine("Enter three letters: ");
            s = checkInput(s);
            println(s + " -> " + findWord(s));
        }
    }

    private String checkInput(String s) {
        char[] ch = s.toCharArray();

        if (ch.length == 3) {
            return s;
        } else {
            System.out.println("Incorrect input");
            run();
        }
        return s;
    }

    private String findWord(String s) {
        String path = "/home/yurii/Завантаження/en-dictionary.txt";

        String word = null;

        char[] letters = s.toCharArray();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            while (true) {
                String str;
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                char[] ofStr = str.toCharArray();

                int a = 0;

                for (char c : ofStr) {

                    if (c == letters[2] && a == 2) {
                        a = 3;
                    }
                    if (c == letters[1] && a == 1) {
                        a = 2;
                    }
                    if (c == letters[0] && a == 0) {
                        a = 1;
                    }
                }
                if (a == 3) {
                    word += str + " ";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (word == null) {
            word = "there are no results";
        }
        return word;
    }
}
