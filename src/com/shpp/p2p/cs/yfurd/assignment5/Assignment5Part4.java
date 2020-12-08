package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    private static final String PATH = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";

    public void run() {
        ArrayList<String> list;
        list = extractColumn(PATH, 6);
        println(list);
    }

    /**
     * This method reads the information from the file, selects the necessary and transmits it.
     *
     * @param filename    file from which the information will be read.
     * @param columnIndex column from which you want to read the information.
     * @return return the list with the required columns.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {

                ArrayList<String> colonsLine = fieldsIn(line);
                String colonsOfLine = colonsLine.get(columnIndex);
                result.add(colonsOfLine);
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Takes a row with columns and returns a list of columns of one row.
     *
     * @param line information line.
     * @return a list of columns in one row.
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> columnLine = new ArrayList<>();

        StringBuilder column = new StringBuilder();

        char[] oneLineChar = line.toCharArray();
        boolean columnWithQuotes = false;

        // Run along the data line.
        for (int i = 0; i < oneLineChar.length; i++) {

            // If quotation marks are present, change the variable "columnWithPaws" to true
            if (isStartQuotes(i, oneLineChar)) {
                columnWithQuotes = true;
            }

            /* If the column with quotes closes, change the variable "columnWithPaws" to false
            add the read data to the letter */
            if (isCloseQuotes(i, oneLineChar)) {
                columnWithQuotes = false;
            }

            // If quotation marks are present, then read character by character in time.
            if (columnWithQuotes) {
                column.append(oneLineChar[i]);
            }

            /* Read the information if there is no column with quotation marks and the data i
            the element are not equal to quotation marks and commas.*/
            if (!columnWithQuotes && oneLineChar[i] != ',' && oneLineChar[i] != '"') {
                column.append(oneLineChar[i]);
            }

            /* Add information to the list if we are not in the column with quotation marks,
            and this element is equal to a comma*/
            if (!columnWithQuotes && oneLineChar[i] == ',') {
                columnLine.add(column.toString());
                column = new StringBuilder();
            }
        }
        columnLine.add(column.toString());
        return columnLine;
    }

    /**
     * This method checks whether the column starts with quotation marks or not.
     *
     * @param i           iteration on a line with data.
     * @param oneLineChar a line with data.
     * @return returns the result of whether the column with quotation marks begins or not.
     */
    private boolean isStartQuotes(int i, char[] oneLineChar) {
        return i == 1 && oneLineChar[i - 1] == '"'
                || i > 1 && oneLineChar[i - 1] == '"' && oneLineChar[i - 2] == ',';
    }

    /**
     * This method checks to see if the column ends with quotation marks.
     *
     * @param i           iteration on a line with data.
     * @param oneLineChar a line with data.
     * @return the result or the column ends with quotation marks.
     */
    private boolean isCloseQuotes(int i, char[] oneLineChar) {
        return i > 0 && i == oneLineChar.length - 1 && oneLineChar[i] == '"'
                || i < oneLineChar.length - 1 && oneLineChar[i] == '"' && oneLineChar[i + 1] == ',';
    }
}
