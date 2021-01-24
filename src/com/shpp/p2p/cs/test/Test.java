package com.shpp.p2p.cs.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<String> myList = new LinkedList<>(Arrays.asList("1", "2", "3", "4"));
        System.out.println(myList);
        for (int i = 0; i < myList.size(); i++) {
            if (i == 2){
                myList.remove(2);
            }
        }
        System.out.println(myList);
    }
}
