package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    public void run() {
        ArrayList<String> list;
        String path1 = "home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";
        String path2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";
        list = extractColumn(path1, 5);

        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                System.out.print(list.get(i) + ".");
            } else {
                System.out.print(list.get(i) + ", ");
            }
        }
        System.out.println("]");
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> list;
        ArrayList<String> list2 = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                list = fieldsIn(str);
                String s = list.get(columnIndex - 1);
                list2.add(s);
            }
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println("Wrong file path!");
            return null;
        }
        return list2;
    }

    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> list = new ArrayList<>();

        String str = "";
        char[] ch = line.toCharArray();
        boolean columnWithPaws = false;
        boolean columnWithoutPaws = false;

        for (int i = 0; i < ch.length; i++) {

            if (i == 1 && ch[i - 1] == '"' ||
                    i > 1 && ch[i - 1] == '"' && ch[i - 2] == ',' ||
                    columnWithPaws) {
                columnWithPaws = true;
                if (ch[i] != '"' && columnWithPaws) {
                    str += ch[i];
                }
                if (ch[i] == '"' && ch[i] == ch[ch.length - 1] || ch[i] == '"' && ch[i + 1] == ',') {
                    list.add(str);
                    str = "";
                    columnWithPaws = false;
                }
            }


            if (i == 0 && ch[i] != '"' && ch[i] != ',' ||
                    i > 1 && ch[i] != '"' && ch[i] != ',' && !columnWithPaws ||
                    columnWithoutPaws) {

                columnWithoutPaws = true;
                if (ch[i] != ',') {
                    str += ch[i];
                }
                if (ch[i] == ',' || i == ch.length - 1) {
                    list.add(str);
                    str = "";
                    columnWithoutPaws = false;
                }
            }

            if (i == ch.length - 1 && ch[ch.length - 1] == ',' || i == 0 && ch[i] == ',') {
                str = " ";
                list.add(str);
                str = "";
            }

            if (ch[i] == ',' && i != ch.length - 1 && ch[i + 1] == ',') {
                str = " ";
                list.add(str);
                str = "";
            }
        }
        return list;
    }
}
