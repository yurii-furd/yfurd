package com.shpp.p2p.cs.test;

import java.util.Arrays;

public class MyArrayList2<E> {
    private E[] arr;


    MyArrayList2() {
        arr = (E[]) new Object[0];
    }

    void add(E e) {
        E[] temp = Arrays.copyOf(arr, arr.length + 1);
        temp[temp.length - 1] = e;
        arr = temp;
    }

    void delete(int index) {
        if (index > arr.length) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else {
            E[] temp = (E[]) new Object[arr.length - 1];
            System.arraycopy(arr, 0, temp, 0, index);
            int countElement = arr.length - index - 1;
            System.arraycopy(arr, index + 1, temp, index, countElement);
            arr = temp;
        }
    }

    void update(int index, E e) {
        arr[index] = e;
    }

    int size() {
        return arr.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
