package com.shpp.p2p.cs.test;

public class MyLinkedList2<E> {
    Node<E> firstNode;
    Node<E> lastNode;
    int size = 0;

    public void addFirst(E el) {
        if (size == 0 && lastNode == null) {
            firstNode = new Node<>(el);
            firstNode.prevNode = null;
            firstNode.nextNode = null;
            lastNode = firstNode;
        } else if (size == 1 && lastNode != null) {
            firstNode = new Node<>(el);
            firstNode.prevNode = null;
            firstNode.nextNode = lastNode;
            lastNode.prevNode = firstNode;
        } else {
            Node<E> next = firstNode;
            firstNode = new Node<>(el);
            firstNode.prevNode = null;
            firstNode.nextNode = next;
            next.prevNode = firstNode;
        }
        size++;
    }

    public void addLast(E el) {
        if (size == 0 && firstNode == null) {
            lastNode = new Node<>(el);
            lastNode.nextNode = null;
            lastNode.prevNode = null;
            firstNode = lastNode;
        } else if (size == 1 && firstNode != null) {
            lastNode = new Node<>(el);
            lastNode.nextNode = null;
            lastNode.prevNode = firstNode;
            firstNode.nextNode = lastNode;
        } else {
            Node<E> prev = lastNode;
            lastNode = new Node<>(el);
            lastNode.nextNode = null;
            lastNode.prevNode = prev;
            prev.nextNode = lastNode;
        }
        size++;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else if (size == 1) {
            Node<E> temp = firstNode;
            firstNode = null;
            lastNode = null;
            size--;
            return temp.element;
        } else {
            Node<E> temp = firstNode;
            firstNode = null;
            firstNode = temp.nextNode;
            size--;
            return temp.element;
        }
    }

    public E removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else if (size == 1) {
            Node<E> temp = lastNode;
            firstNode = null;
            lastNode = null;
            size--;
            return temp.element;
        } else {
            Node<E> temp = lastNode;
            lastNode = null;
            lastNode = temp.prevNode;
            size--;
            return temp.element;
        }
    }

    public E getFirst() {
        return firstNode.element;
    }

    public E getLast() {
        return lastNode.element;
    }

    @Override
    public String toString() {
        if (firstNode != null) {
            Node<E> temp = firstNode;
            StringBuilder s = new StringBuilder();
            while (temp.nextNode != null) {
                s.append(temp.element).append(", ");
                temp = temp.nextNode;
            }
            s.append(temp.element);
            return "[" + s + "]";
        } else {
            return "[]";
        }
    }

    public static void main(String[] args) {
        MyLinkedList2<Integer> list2 = new MyLinkedList2<>();
        list2.addLast(5);
        list2.addLast(45);
        list2.addLast(8);

        System.out.println(list2.firstNode);
        System.out.println(list2.lastNode);

//        list2.removeLast();
//        list2.removeLast();
//        list2.removeLast();

//        list2.removeFirst();
//        list2.removeFirst();
//        list2.removeFirst();

        System.out.println(list2.size);
        System.out.println(list2);
    }

    class Node<E> {
        private E element;
        private Node<E> nextNode;
        private Node<E> prevNode;

        Node(E element) {
            this.element = element;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element + '}';
        }
    }
}
