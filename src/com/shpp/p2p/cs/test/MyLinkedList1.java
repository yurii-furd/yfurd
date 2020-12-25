package com.shpp.p2p.cs.test;

public class MyLinkedList1<E> {
    Node<E> firstNode;
    int size = 0;

    public void add(E el) {
        if (firstNode == null) {
            firstNode = new Node<>(el);
        } else {
            Node<E> temp = firstNode;
            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }
            temp.nextNode = new Node<>(el);
        }
        size++;
    }

    public void addFirst(E el) {
        if (firstNode == null) {
            firstNode = new Node<>(el);
        } else {
            Node<E> temp = new Node<>(el);
            temp.nextNode = firstNode;
            firstNode = temp;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void removeFirst() {
        if (firstNode == null) {
            throw new IndexOutOfBoundsException("Wrong input");
        }
        if (firstNode.nextNode == null) {
            firstNode = null;
        } else {
            firstNode = firstNode.nextNode;
        }
        size--;
    }

    public E removeLast() {
        if (firstNode == null) {
            throw new IndexOutOfBoundsException("Wrong input");
        }
        Node<E> temp = firstNode;
        if (firstNode.nextNode == null) {
            firstNode = null;
            size--;
            return temp.element;
        } else {
            while (temp.nextNode.nextNode != null) {
                temp = temp.nextNode;
            }
            E last = temp.nextNode.element;
            temp.nextNode = null;
            size--;
            return last;
        }
    }

    @Override
    public String toString() {
        return "MyLinkedList{" + firstNode + "]";
    }

    class Node<E> {
        private E element;
        private Node<E> nextNode;

        Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{ el " + element + ", nextN " + nextNode + "}";
        }
    }
}
