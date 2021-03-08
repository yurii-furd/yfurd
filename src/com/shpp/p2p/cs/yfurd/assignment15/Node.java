package com.shpp.p2p.cs.yfurd.assignment15;

public class Node {
    private int element;
    private int weight;
    private boolean visit;
    private boolean visitTwo;
    private Node leftNode;
    private Node rightNode;
    private Node root;

    public Node() {
    }

    public Node(int element, int weight, int coding) {
        this.element = element;
        this.weight = weight;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isVisit() {
        return visit;
    }

    public void setVisit(boolean visit) {
        this.visit = visit;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isVisitTwo() {
        return visitTwo; }

    public void setVisitTwo(boolean visitTwo) {
        this.visitTwo = visitTwo; }

    public Node addTree(Node node1, Node node2) {
            root = new Node();
            root.setLeftNode(node1);
            root.setRightNode(node2);
            root.setWeight(node1.getWeight() + node2.getWeight());
            return root;
    }

    @Override
    public String toString() {
        return "Node{" +
                "element=" + element +
                ", weight=" + weight +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }
}