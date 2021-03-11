package com.shpp.p2p.cs.yfurd.assignment16;

import java.util.Arrays;

public class MyArrayList<E> {
    private E[] array;

    public MyArrayList() {
        array = (E[]) new Object[0];
    }

    private void add(E el) {
        E[] temp;
        if (array.length == 0) {
            temp = (E[]) new Object[1];
            temp[0] = el;
        } else {
            temp = (E[]) new Object[array.length + 1];
            System.arraycopy(array, 0, temp, 0, array.length);
            temp[temp.length - 1] = el;
        }
        array = temp;
    }

    private void delete(int index) {
        if (index > array.length && index < 0) {
            throwException();
        } else {
            E[] temp =(E[]) new Object[array.length - 1];
            System.arraycopy(array, 0, temp, 0, index);
            System.arraycopy(array, index + 1, temp, index, temp.length - index);
            array = temp;
        }
    }

    private E getElement (int index){
        if (index > array.length && index < 0) {
            throwException();
        } else {
            return array[index];
        }
        return null;
    }

    private void update(int index, E el){
        if (index > array.length && index < 0) {
            throwException();
        } else {
            array[index] = el;
        }
    }

    private boolean contains(E el) {
        for (E e : array) {
            if (e == el) {
                return true;
            }
        }
        return false;
    }

    private void clear(){
        E[] temp = (E[]) new Object[0];
        array = temp;
    }

    private int size(){
        return array.length;
    }

    private void throwException() {
        throw new ArrayIndexOutOfBoundsException("Wrong index. Index should be less " + (array.length - 1));
    }

    @Override
    public String toString() {
        return "MyArrayList" + Arrays.toString(array);
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.delete(3);
        System.out.println(list.getElement(1));
        list.update(0, 1112);
        list.clear();
        System.out.println(list);
    }
}
