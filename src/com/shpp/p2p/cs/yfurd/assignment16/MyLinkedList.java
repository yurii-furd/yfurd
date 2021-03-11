package com.shpp.p2p.cs.yfurd.assignment16;

import java.util.LinkedList;

public class MyLinkedList<E> {
    private Node<E> firstNode;
    private int size = 0;

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(15);
        list.add(155);
        list.addFirst(12);
        list.addLast(1555555);
        list.deleteLast();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.contains(12));
    }

    private void add(E e) {
        if (firstNode == null) {
            firstNode = new Node<>(e);
        } else {
            Node<E> temp = firstNode;
            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }
            temp.nextNode = new Node<>(e);
        }
        size++;
    }

    private void addFirst(E e) {
        if (firstNode == null) {
            firstNode = new Node<>(e);
        } else {
            Node<E> temp = new Node<>(e);
            temp.nextNode = firstNode;
            firstNode = temp;
        }
        size++;
    }

    private void addLast(E e) {
        if (firstNode == null) {
            firstNode = new Node<>(e);
        } else {
            Node<E> temp = firstNode;
            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }
            temp.nextNode = new Node<>(e);
        }
        size++;
    }

    private void deleteFirst() {
        if (firstNode == null) {
            throw new IllegalArgumentException();
        } else if (firstNode.nextNode == null) {
            firstNode = null;
            size--;
        } else {
            Node<E> temp = firstNode.nextNode;
            firstNode = temp;
            size--;
        }
    }

    private void deleteLast() {
        if (firstNode == null) {
            throw new IllegalArgumentException();
        } else if (firstNode.nextNode == null) {
            firstNode = null;
            size--;
        } else {
            Node<E> temp = firstNode;
            while (temp.nextNode.nextNode != null) {
                temp = temp.nextNode;
            }
            temp.nextNode = null;
            size--;
        }
    }

    private boolean contains(E e) {
        Node<E> temp = firstNode;
        if (temp.el == e) {
            return true;
        } else {
            while (temp.nextNode != null) {
                temp = temp.nextNode;
                if (temp.el == e) {
                    return true;
                }
            }
        }
        return false;
    }

    private void clear() {
        firstNode.nextNode = null;
        firstNode = null;
        size = 0;
    }

    private int size() {
        return size;
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
        return "MyLinkedList{firstNode=" + firstNode + '}';
    }
}
