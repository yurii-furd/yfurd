package com.shpp.p2p.cs.yfurd.assignment15;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Archive implements Assignment15 {

    final static List<Integer> codedElements = new ArrayList<>();

    public void archive(File pathInput, File pathOutput) {

        try (FileInputStream inputStream = new FileInputStream(pathInput);
             FileOutputStream fileOutputStream = new FileOutputStream(pathOutput, true)) {

            byte[] inputDate = new byte[inputStream.available()];
            inputStream.read(inputDate, 0, inputDate.length);

            //the weight of each input byte
            Map<Integer, Integer> map = new HashMap<>();
            weightInputBytes(inputDate, map);

            //coding tree
            Node node = buildTree(map);

            //packing of coding tree
            StringBuilder str = new StringBuilder();
            str = treeToBites(node.getRoot(), str);

            // 1. write to output file size tree
            String lengthTree = Integer.toBinaryString(str.length());
            StringBuilder lengthTreeBytes = addZeroToByte(new StringBuilder(lengthTree), true);
            int[] lengthTreeMas = prepareDataForWrite(lengthTreeBytes);
            if (lengthTreeMas.length == 1) {
                fileOutputStream.write(0);
            }
            for (int lengthTreeMa : lengthTreeMas) {
                fileOutputStream.write(lengthTreeMa);
            }

            // 2. write to output file structure tree
            str = addZeroToByte(str, false);
            int[] mas = prepareDataForWrite(str);
            for (int el : mas) {
                fileOutputStream.write(el);
            }

            // 3. write to output file coded elements.
            for (Integer codedElement : codedElements) {
//                System.out.println(codedElement);
                fileOutputStream.write(codedElement);
            }

            // 4. write to output file date.
            System.out.println(str);

            StringBuilder a = new StringBuilder();
            Node node1 = node.getRoot();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] prepareDataForWrite(StringBuilder str) {
        int[] mas = new int[str.length() / BITS_IN_BYTE];
        for (int i = 0; i < str.length() / BITS_IN_BYTE; i++) {
            int start = i * BITS_IN_BYTE;
            int end = start + BITS_IN_BYTE;
            String s = str.substring(start, end);
            mas[i] = Integer.parseInt(s, 2);
        }
        return mas;
    }

    private StringBuilder addZeroToByte(StringBuilder str, boolean start) {
        if (str.length() % BITS_IN_BYTE != 0 && start) {
            while (str.length() % BITS_IN_BYTE != 0) {
                str.insert(0, "0");
            }
        } else {
            while (str.length() % BITS_IN_BYTE != 0) {
                str.append("0");
            }
        }
        return str;
    }


    private StringBuilder treeToBites(Node node, StringBuilder str) {
        if (!node.isVisit()) {
            if (node.getElement() == 0) {
                str.append(1);
            } else {
                str.append(0);
                codedElements.add(node.getElement());
            }
            node.setVisit(true);

            if (node.getLeftNode() != null) {
                treeToBites(node.getLeftNode(), str);
            }

            if (node.getRightNode() != null) {
                treeToBites(node.getRightNode(), str);
            }

        }
        return str;
    }

    private Node buildTree(Map<Integer, Integer> map) {
        List<Node> list = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue(), 0));
        }

        Node node = new Node();
        while (list.size() != 1) {
            Node min1 = findMin(list);
            Node min2 = findMin(list);
            list.add(node.addTree(min1, min2));
        }
        return node;
    }

    private void weightInputBytes(byte[] inputDate, Map<Integer, Integer> map) {
        for (int i : inputDate) {
            i = i & 0b1111_1111;
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                int value = map.get(i);
                map.put(i, value + 1);
            }
        }
    }

    private Node findMin(List<Node> list) {
        int num = 0;
        int min = 256;
        for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            int weight = node.getWeight();
            if (weight < min) {
                min = weight;
                num = i;
            }
        }
        Node node = list.get(num);
        list.remove(num);
        return node;
    }
}