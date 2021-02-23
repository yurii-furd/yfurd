package com.shpp.p2p.cs.yfurd.assignment14;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Assignment14Part1 {

    /*виклик_вашої_програми poem.txt
    виклик_вашої_програми poem.txt arvhied_poem.par
    виклик_вашої_програми poem.txt.par
    виклик_вашої_програми poem.txt.par unarvhived_poem.txt
    виклик_вашої_програми poem archived
    виклик_вашої_програми -u archived unarchived
    виклик_вашої_програми -a archive.par archived_twice.par
*/

    public void inputDate() {
    }

    public void inputDate(String path) {
    }

    public void inputDate(String flag, String path1, String path2) {
    }


    public static void main(String[] args) {
//        File file = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/smoke.bmp");
//        File fileNew = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/smokeNew.bmp");

        File file = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/test.txt");
        File fileNew = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/testNew.txt");
//
//        File file = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/abc.txt");
//        File fileNew = new File("src/com/shpp/p2p/cs/yfurd/assignment14/test/abcNew.txt");



        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileOutputStream fileOutputStream = new FileOutputStream(fileNew, true)) {

            byte[] bufferFileInput = new byte[fileInputStream.available()];
            fileInputStream.read(bufferFileInput, 0, bufferFileInput.length);

            Set<Byte> unSet = new HashSet<>();

            for (byte b : bufferFileInput) {
                unSet.add(b);
            }

            //size table
            fileOutputStream.write(unSet.size() * 2);
//            fileOutputStream.write((byte) file.length());

            int i = 0;
            Map<Byte, String> unMap = new HashMap<>();
            for (Byte b : unSet) {
//                table key
                fileOutputStream.write(b);
//                table value
                fileOutputStream.write(i);

                unMap.put(b, Integer.toBinaryString(i));
                i++;
            }

            int bits = numberBits(unSet.size());
            String s = "";
            for (Byte b: bufferFileInput) {
                String a = unMap.get(b);
                s += chekBites(bits, a);
            }

            System.out.println(s);

//            fileOutputStream.write(s.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String chekBites(int bits, String a) {
        while (bits != a.length()){
            a = "0" + a;
        }
        return a;
    }

    private static int numberBits(int size) {
        if (size == 1) {
            return 1;
        } else if (size >= 2 && size <= 4) {
            return 2;
        } else if (size >= 5 && size <= 8) {
            return 3;
        } else if (size >= 9 && size <= 16) {
            return 4;
        } else if (size >= 17 && size <= 32) {
            return 5;
        } else if (size >= 33 && size <= 64) {
            return 6;
        } else if (size >= 65 && size <= 128) {
            return 7;
        } else if (size >= 129 && size <= 256) {
            return 8;
        }
        return 0;
    }
}
