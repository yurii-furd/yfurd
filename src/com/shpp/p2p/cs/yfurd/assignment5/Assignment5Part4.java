package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    public void run() {
        String path2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";
        extractColumn(path2, 2);
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex) {
//        String path1 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";
//        String path2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";

        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                list = fieldsIn(str);

                String strList = list.get(0);
                char[] ch = strList.toCharArray();
                String newStr = "";

                int a = 0;
                for (int i = 0; i < ch.length - 1; i++) {

                    if (a == 2){
                        newStr += ch[i];
                    }

                    if (ch[i] == '"' && a == 1){
                        a = 2;
                    }

                    if (ch[i] == ','){
                        a = 1;
                    }

                }
                System.out.println(newStr);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> list = new ArrayList<>();
        list.add(line);
        return list;
    }

}
