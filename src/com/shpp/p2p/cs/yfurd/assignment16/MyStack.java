package com.shpp.p2p.cs.yfurd.assignment16;

public class MyStack<E> {
    private Node<E> firstNode;
    private int size = 0;

    /**
     * Adds an element to the top of the stack.
     * @param e the value you want to add to the stack.
     */
    private void push(E e) {
        if (firstNode == null) {
            firstNode = new Node<>(e);
        } else {
            Node<E> temp = new Node<>(e);
            temp.nextNode = firstNode;
            firstNode = temp;
        }
        size++;
    }

    /**
     * This method removes the top element from the stack.
     * @return returns it.
     */
    private E pop() {
        if (size == 0) {
            throw new IllegalArgumentException();
        } else {
            Node<E> temp = firstNode;
            firstNode = temp.nextNode;
            size--;
            return temp.el;
        }
    }

    /**
     * This method returns the top element in the stack.
     * @return the value you want to add to the stack.
     */
    private E peek(){
        return firstNode.el;
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
        return "MyStack{firstNode=" + firstNode + '}';
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(5);
        stack.push(555);
        stack.push(555888);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
