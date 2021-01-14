package com.shpp.p2p.cs.test;

public class Test {

    public static void main(String[] args) {

        String stringProgLib1 = "proglib.io";        // 1
        String stringProgLib2 = new String("proglib.io"); // 2
        System.out.print(stringProgLib1 == stringProgLib2); // 3
        stringProgLib2.intern(); // 4
        System.out.print(stringProgLib1 == stringProgLib2); // 5
        stringProgLib2 = stringProgLib2.intern(); // 6
        System.out.print(stringProgLib1 == stringProgLib2); // 7
    }
}
