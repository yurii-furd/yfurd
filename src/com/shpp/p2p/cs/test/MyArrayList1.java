package com.shpp.p2p.cs.test;

import java.util.Arrays;

public class MyArrayList1<E> {
    private Object[] arr;

    MyArrayList1() {
        this.arr = new Object[0];
    }

    void add(E e) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = e;
    }

    void delete(int index) {
        if (index > arr.length) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else {
            Object[] temp = new Object[arr.length - 1];
            for (int i = 0; i < index; i++) {
                temp[i] = arr[i];
            }
            for (int i = index + 1; i < arr.length; i++) {
                temp[i - 1] = arr[i];
            }
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