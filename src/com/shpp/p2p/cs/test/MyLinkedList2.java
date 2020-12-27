package com.shpp.p2p.cs.test;

public class MyLinkedList2<E> {
    Node<E> firstNode;
    Node<E> lastNode;
    int size = 0;

    private void addFirst(E el) {
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

    private void addLast(E el) {
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

    private void add(E el) {
        addLast(el);
    }

    private void addIndex(E el, int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException("Wrong index");
        } else if (index == 0) {
            Node<E> temp = firstNode;
            Node newNode = new Node(el);
            temp.prevNode = newNode;
            newNode.nextNode = temp;
            firstNode = newNode;
            size++;
        } else if (index == size) {
            Node<E> temp = lastNode;
            Node<E> newNode = new Node<>(el);
            temp.nextNode = newNode;
            newNode.prevNode = temp;
            lastNode = newNode;
        } else {
            Node<E> temp = firstNode;
            for (int i = 0; i < index; i++) {
                temp = temp.nextNode;
            }
            Node<E> newNode = new Node<>(el);
            newNode.nextNode = temp.nextNode;
            newNode.prevNode = temp.prevNode;
            temp.prevNode.nextNode = newNode;
            temp.nextNode.prevNode = newNode;
            size++;
        }
    }

    private E removeFirst() {
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

    private E removeLast() {
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

    private E getFirst() {
        return firstNode.element;
    }

    private E getLast() {
        return lastNode.element;
    }

    private int size() {
        return size;
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
        list2.add(8888);
        list2.addIndex(8888888, 4);

        System.out.println(list2.firstNode);
        System.out.println(list2.lastNode);

//        list2.removeLast();
//        list2.removeLast();
//        list2.removeLast();

//        list2.removeFirst();
//        list2.removeFirst();
//        list2.removeFirst();

        System.out.println(list2.size());
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
