package com.shpp.p2p.cs.yfurd.assignment16;

import java.util.NoSuchElementException;

public class MyQueue<E> {
    Node<E> firstNode;
    Node<E> endNode;
    int size;

    //повертає але не видаляє елемент з початку черги, якщо черга пуста то NoSuchElementException
    private E element(){
        if (size == 0){
            throw new NoSuchElementException();
        } else {
            return firstNode.el;
        }
    }

    //добавляє елемент в кінець черги
    private boolean offer(E e){
        if (size == 0){
            firstNode = new Node<>(e);
            endNode = firstNode;
        } else {
            Node<E> temp = new Node<>(e);
            endNode.nextNode = temp;
            endNode = temp;
        }
        size++;
        return true;
    }

    //повертає без видалення елемент з початку черги якщо пуста то повертає нуль
    private E peek(){
        if (size == 0){
            return null;
        } else {
            return firstNode.el;
        }
    }

    //повертає з видаленням елемент з початку черги, якщо пуста то повертає нуль
    private E pool(){
        if (size == 0){
            return null;
        } else {
            Node<E> temp = firstNode;
            firstNode = firstNode.nextNode;
            return temp.el;
        }
    }

    //повертає елемент з початку черги і видаляє його, якщо черга пуста то NoSuchElementException
    private E remove(){
        if (size == 0){
            throw new NoSuchElementException();
        } else {
            Node<E> temp = firstNode;
            firstNode = firstNode.nextNode;
            return temp.el;
        }
    }

    private static class Node<E> {
        private E el;
        private Node<E> nextNode;

        public Node(E el) {
            this.el = el;
        }

        @Override
        public String toString() {
            return "Node{el=" + el + ", next=" + nextNode + '}';
        }
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "firstNode=" + firstNode +
                '}';
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(12);
        queue.offer(1255);
        queue.offer(1);
        queue.offer(15555555);
        System.out.println(queue);
    }
}
