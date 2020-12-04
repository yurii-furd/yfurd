package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    public void run() {
        String path1 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";
        String path2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";
        extractColumn(path1, 2);
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
//        String path1 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";
//        String path2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";

        ArrayList<String> list;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                list = fieldsIn(str);

                for (
                        String s :
                        list) {
                    System.out.println(s);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> list = new ArrayList<>();

        String str = "";

        char[] ch = line.toCharArray();

        boolean columnWithPaws = false;

        for (int i = 0; i < ch.length; i++) {

            if (i == ch[ch.length - 1] && ch[ch.length - 1] == ',' || i == 0 && ch[i] == ',') {
                str = "пуста колонка ";
                list.add(str);
                str = "";
            }

            if (ch[i] == ',' && i != ch.length - 1 && ch[i + 1] == ',') {
                str = "пуста колонка посередині ";
                list.add(str);
                str = "";
            }


            if (i == 1 && ch[i - 1] == '"' ||
                    i > 1 && ch[i - 1] == '"' && ch[i - 2] == ',' ||
                    columnWithPaws == true
            ) {
                columnWithPaws = true;
                if (ch[i] != '"' && columnWithPaws == true) {
                    str += ch[i];
                }
                if (ch[i] == '"' && ch[i] == ch[ch.length - 1] || ch[i] == '"' && ch[i + 1] == ',') {
                    list.add(str);
                    str = "";
                    columnWithPaws = false;
                }

            }



        }

        return list;
    }
}
