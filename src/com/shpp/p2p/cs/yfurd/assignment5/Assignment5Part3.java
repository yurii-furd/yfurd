package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment5Part3 extends TextProgram {

    public void run() {
        while (true) {
            String s = readLine("Enter three letters: ");
            println(s + " -> " + findWord(s));
        }
    }

    private String findWord(String s) {
        String path = "/home/yurii/Завантаження/en-dictionary (копія).txt";

        String word = null;

        char[] letters = s.toCharArray();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {

            while (true) {
                String str = bufferedReader.readLine();
                if (str == null){
                    break;
                }
                char[] ofStr = str.toCharArray();

                int a = 0;

                for (int i = 0; i < ofStr.length; i++) {

                    if (ofStr[i] == letters[2] && a == 2) {
                        a = 3;
                    }
                    if (ofStr[i] == letters[1] && a == 1) {
                        a = 2;
                    }
                    if (ofStr[i] == letters[0] && a == 0) {
                        a = 1;
                    }
                }
                if (a == 3) {
                    word = str + " ";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (word == null){
            word = "there are no results";
        }
        return word.toString();
    }
}
