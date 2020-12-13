package com.shpp.p2p.cs.yfurd.assignment6.assignment6part3;

public class fg {
    public static void main(String[] args) {
        int[][] array = new int[3][3];

        array[0][0] = 1;
        array[0][1] = 2;
        array[0][2] = 3;

        array[1][0] = 4;
        array[1][1] = 5;
        array[1][2] = 6;

//        System.out.println(array.length);

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(array[j][i] + " ");
            }
            System.out.println();
        }
    }
}
