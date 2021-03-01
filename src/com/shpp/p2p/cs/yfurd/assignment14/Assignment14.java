package com.shpp.p2p.cs.yfurd.assignment14;

public interface Assignment14 {
    int SIZE_DATE_BITS = 64;
    int SIZE_DATE_BYTES = 8;
    int TABLE_SIZE_BITS = 32;
    int TABLE_SIZE_BYTES = 4;
    int BITS_IN_BYTE = 8;

    /**
     * This method returns the number of bits required to generate unique bytes
     *
     * @param size number of unique bytes.
     * @return the number of bits required to generate unique bytes.
     */
    default int numberBits(int size) {
        if (size == 1) {
            return 1;
        } else if (size <= 4) {
            return 2;
        } else if (size <= 8) {
            return 3;
        } else if (size <= 16) {
            return 4;
        } else if (size <= 32) {
            return 5;
        } else if (size <= 64) {
            return 6;
        } else if (size <= 128) {
            return 7;
        } else if (size <= 256) {
            return 8;
        }
        return 0;
    }

    /**
     * This method fills with zeros those bytes which are not completely formed.
     *
     * @param bits the required number of bits.
     * @param newBits a new byte with added bits that would.
     * @param isDate shows whether the input data came or not (for example, the table size came).
     * @return returns the correctly formed byte.
     */
    default String chekBites(int bits, String newBits, boolean isDate) {
        StringBuilder newBitsBuilder = new StringBuilder(newBits);
        while (bits != newBitsBuilder.length()) {

            if (isDate) {
                newBitsBuilder.append("0");
            } else {
                newBitsBuilder.insert(0, "0");
            }
        }
        newBits = newBitsBuilder.toString();
        return newBits;
    }
}
