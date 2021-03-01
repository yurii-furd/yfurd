package com.shpp.p2p.cs.yfurd.assignment14;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Archive implements Assignment14 {

    public void archive(File pathInput, File pathOutput) {

        try (FileInputStream fileInputStream = new FileInputStream(pathInput);
             FileOutputStream fileOutputStream = new FileOutputStream(pathOutput, true)) {

            byte[] bufferFileInput = new byte[fileInputStream.available()];
            fileInputStream.read(bufferFileInput, 0, bufferFileInput.length);

            Set<Byte> uniqueSet = new HashSet<>();
            for (byte b : bufferFileInput) {
                uniqueSet.add(b);
            }

            //size table
            sizeTable(fileOutputStream, uniqueSet.size());
            Map<Byte, Integer> map = table(uniqueSet);

            //date new file
            String dateNewFile = sizeDate(bufferFileInput, fileOutputStream, uniqueSet.size(), map);

            //table
            for (byte value : uniqueSet) {
                fileOutputStream.write(value);
                int key = map.get(value);
                fileOutputStream.write(key);
            }

            //date
            date(fileOutputStream, dateNewFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes the length of the table in bits to the source file.
     *
     * @param fileOutputStream output data stream.
     */
    private void sizeTable(FileOutputStream fileOutputStream, int size) throws IOException {

        String sizeTableBits = Integer.toBinaryString(size * 2);
        String finalSizeTableBits = chekBites(TABLE_SIZE_BITS, sizeTableBits, false);
        int[] sizeTable = filePreparation(finalSizeTableBits);
        for (int bytes : sizeTable) {
            fileOutputStream.write(Integer.parseInt(bytes + ""));
        }
    }

    /**
     * This method creates a database of unique bytes, where the key is the unique bytes from the input file,
     * and the values are their new values.
     *
     * @param uniqueSet it is list with unique bytes.
     * @return list of converted unique bytes.
     */
    private Map<Byte, Integer> table(Set<Byte> uniqueSet) {
        int i = 0;
        Map<Byte, Integer> unMap = new HashMap<>();
        for (Byte b : uniqueSet) {
            unMap.put(b, i);
            i++;
        }
        return unMap;
    }

    /**
     * This method writes to the source file the length of the new data in bits
     *
     * @param bufferFileInput  the array in which the input file is written.
     * @param fileOutputStream output data stream.
     * @param size             list unique bytes.
     * @param map              list unique bytes with new values byte.
     * @return line of a new file
     */
    private String sizeDate(byte[] bufferFileInput, FileOutputStream fileOutputStream, int size, Map<Byte, Integer> map) throws IOException {

        int bitsInByte = numberBits(size);
        StringBuilder newFile = new StringBuilder();

        for (Byte b : bufferFileInput) {
            int value = map.get(b);
            String temp = Integer.toBinaryString(value);
            newFile.append(chekBites(bitsInByte, temp, false));
        }

        String sizeDate = Integer.toBinaryString(newFile.length());
        String chekBites = chekBites(SIZE_DATE_BITS, sizeDate, false);
        int[] arraySizeDate = filePreparation(chekBites);

        for (int b : arraySizeDate) {
            fileOutputStream.write(Integer.parseInt(b + ""));
        }
        return newFile.toString();
    }

    /**
     * This method writes a new combination of bits to the source file.
     *
     * @param fileOutputStream output data stream.
     * @param dateNewFile      source file with new bytes
     */
    private void date(FileOutputStream fileOutputStream, String dateNewFile) throws IOException {
        int[] arrayOutputFile;
        int length = dateNewFile.length() % BITS_IN_BYTE;

        if (length != 0) {
            String chekBites = chekBites(dateNewFile.length() + BITS_IN_BYTE - length, dateNewFile, true);
            arrayOutputFile = filePreparation(chekBites);
        } else {
            arrayOutputFile = filePreparation(dateNewFile);
        }

        for (int b : arrayOutputFile) {
            fileOutputStream.write(Integer.parseInt(b + ""));
        }
    }

    /**
     * This method forms an array of source data.
     *
     * @param outputFile source file without missing bits.
     * @return the generated array of data from the source file.
     */
    private int[] filePreparation(String outputFile) {

        int size = outputFile.length() / SIZE_DATE_BYTES;
        int[] array = new int[size];
        int count = 0;

        for (int i = 0; i < outputFile.length() / SIZE_DATE_BYTES; i++) {
            int startIdx = i * SIZE_DATE_BYTES;
            int endIdx = startIdx + SIZE_DATE_BYTES;
            String bitsInByte = outputFile.substring(startIdx, endIdx);
            array[count] = Integer.parseInt(bitsInByte, 2);
            count++;
        }
        return array;
    }
}
