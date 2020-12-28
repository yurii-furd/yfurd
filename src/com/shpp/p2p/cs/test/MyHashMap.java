package com.shpp.p2p.cs.test;

import java.util.Arrays;

public class MyHashMap<K, V> {
    Node<K, V>[] hashTable;
    int size = 0;

    MyHashMap() {
        this.hashTable = new Node[16];
    }

    public static void main(String[] args) {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        map.put(5, 56);
        map.put(15, 57);
        map.put(6, 655);
        map.put(21, 57);
        map.put(20, 57);
        System.out.println(map.get(6));
        System.out.println(map.size());
        System.out.println(map);
    }

    private int hash(K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    private void put(K key, V value) {
        int hash = hash(key);
        Node<K, V> node = hashTable[hash];
        if (node == null) {
            Node<K, V> kvNode = new Node<>(key, value, hash);
            hashTable[hash] = kvNode;
            size++;
        } else {
            while (node.nextNode != null && !key.equals(node.key)) {
                node = node.nextNode;
            }
            if (key.equals(node.key)) {
                node.value = value;
            } else {
                node.nextNode = new Node<>(key, value, hash);
                size++;
            }
        }
    }

    private V get(K key) {
        int hash = hash(key);
        Node<K, V> kvNode = hashTable[hash];
        if (kvNode == null) {
            return null;
        } else {
            while (kvNode.nextNode != null || !key.equals(kvNode.key)) {
                kvNode = kvNode.nextNode;
            }
            return kvNode == null ? null : kvNode.value;
        }
    }

    private int size() {
        return size;
    }

    class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> nextNode;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "hash=" + hash +
                    ", key=" + key +
                    ", value=" + value +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "hashTable=" + Arrays.toString(hashTable) +
                ", size=" + size +
                '}';
    }
}
