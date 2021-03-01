package com.shpp.p2p.cs.yfurd.assignment14;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UnArchive implements Assignment14 {

    public void unArchive(File pathInput, File pathOutput) {

        try (FileInputStream fileInputStream = new FileInputStream(pathInput);
             FileOutputStream fileOutputStream = new FileOutputStream(pathOutput, true)) {

            byte[] bufferFileInput = new byte[fileInputStream.available()];
            fileInputStream.read(bufferFileInput, 0, bufferFileInput.length);

            //size table
            int tableSize = getTableSize(bufferFileInput);

            //table
            int needBits = numberBits(tableSize / 2);
            Map<String, String> map = new HashMap<>();
            table(bufferFileInput, tableSize, map, needBits);

            //date
            date(fileOutputStream, bufferFileInput, tableSize, needBits, map);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method reads the first four bytes from the input file recognizes
     * the data and returns the length of the table
     *
     * @param bufferFileInput the array in which the input file is written.
     * @return the length of the table in bytes.
     */
    private int getTableSize(byte[] bufferFileInput) {
        byte[] sizeTable = new byte[TABLE_SIZE_BYTES];
        System.arraycopy(bufferFileInput, 0, sizeTable, 0, TABLE_SIZE_BYTES);
        StringBuilder s = new StringBuilder();
        for (byte b : sizeTable) {
            if (b != 0) {
                String temp = (Integer.toBinaryString(b & 0b1111_1111));
                s.append(chekBites(BITS_IN_BYTE, temp, false));
            }
        }
        return Integer.parseInt(s.toString(), 2);
    }

    /**
     * This method reads the data table from the input file, and writes this data to the database, 
     * where the key is the original byte and the value is new.
     *
     * @param bufferFileInput the array in which the input file is written.
     * @param tableSize table length in bytes.
     * @param map list unique bytes with new values byte.
     * @param needBits requires the number of bits to uniquely create an individual byte.
     */
    private void table(byte[] bufferFileInput, int tableSize, Map<String, String> map, int needBits) {
        byte[] table = new byte[tableSize];
        int a1 = 0;
        for (int i = TABLE_SIZE_BYTES + SIZE_DATE_BYTES; i < (TABLE_SIZE_BYTES + SIZE_DATE_BYTES + tableSize); i++) {
            table[a1] = bufferFileInput[i];
            a1++;
        }

        for (int i = 1; i < table.length; i = i + 2) {
            String key = chekBites(needBits, Integer.toBinaryString(table[i] & 0b1111_1111), false);
            String value = Integer.toBinaryString(table[i - 1] & 0b1111_1111);
            map.put(key, value);
        }
    }

    /**
     * This method retrieves the data and replaces some bits with others through the map
     * 
     * @param fileOutputStream the array in which the input file is written.
     * @param bufferFileInput the array in which the input file is written.
     * @param tableSize table length in bytes.
     * @param needBits requires the number of bits to uniquely create an individual byte.
     * @param map list unique bytes with new values byte.
     */
    private void date(FileOutputStream fileOutputStream, byte[] bufferFileInput, int tableSize, int needBits, Map<String, String> map) throws IOException {
        StringBuilder date = new StringBuilder();
        for (int i = TABLE_SIZE_BYTES + SIZE_DATE_BYTES + tableSize; i < bufferFileInput.length; i++) {
            String temp = Integer.toBinaryString(bufferFileInput[i] & 0b1111_1111);
            date.append(chekBites(BITS_IN_BYTE, temp, false));
        }

        if (date.length() % needBits != 0) {
            date = new StringBuilder(deleteNull(needBits, date.toString()));
        }

        StringBuilder newDate = new StringBuilder();
        for (int i = 0; i < date.length() / needBits; i++) {
            int startInd = i * needBits;
            int endInd = startInd + needBits;
            String temp = date.substring(startInd, endInd);

            if (map.containsKey(temp)) {
                newDate.append(map.get(temp));
                fileOutputStream.write(Integer.parseInt(map.get(temp), 2));
            }
        }
    }

    /**
     * This method removes unnecessary information from the read data.
     *
     * @param needBits requires the number of bits to uniquely create an individual byte.
     * @param date the data is read from the input file.
     * @return data without unnecessary zeros
     */
    private String deleteNull(int needBits, String date) {
        StringBuilder str = new StringBuilder(date);

        int i = str.length() % needBits;
        while (i != 0) {
            str.deleteCharAt(str.length() - 1);
            i--;
        }
        date = str.toString();
        return date;
    }
}
