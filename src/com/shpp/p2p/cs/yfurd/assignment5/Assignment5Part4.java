package com.shpp.p2p.cs.yfurd.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {

    private static final String PATH1 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/car.csv";
    private static final String PATH2 = "/home/yurii/development/projects/yfurd/src/com/shpp/p2p/cs/yfurd/assignment5/food-origins.csv";

    public void run() {
        ArrayList<String> list;
        list = extractColumn(PATH1, 0);
        println(list);
    }

    /**
     * This method reads the information from the file, selects the necessary and transmits it.
     *
     * @param filename file from which the information will be read.
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
     * @param line information line.
     * @return a list of columns in one row.
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> colonsLine = new ArrayList<>();

        StringBuilder column = new StringBuilder();

        char[] oneLineChar = line.toCharArray();
        boolean columnWithPaws = false;

        // Run along the data line.
        for (int i = 0; i < oneLineChar.length; i++) {

            // If quotation marks are present, change the variable "columnWithPaws" to true
            if (isStartPaws(i, oneLineChar)) {
                columnWithPaws = true;
            }

            /* If the column with quotes closes, change the variable "columnWithPaws" to false
            add the read data to the letter */
            if (isClosePaws(i, oneLineChar)) {
                columnWithPaws = false;
                colonsLine.add(column.toString());
                column = new StringBuilder();
            }

            // If quotation marks are present, then read character by character in time.
            if (columnWithPaws) {
                column.append(oneLineChar[i]);
            }

            /* Read the information if there is no column with quotation marks and the data i
            the element are not equal to quotation marks and commas.*/
            if (!columnWithPaws && oneLineChar[i] != ',' && oneLineChar[i] != '"') {
                column.append(oneLineChar[i]);
            }

            /* Add information to the list if we are not in the column with quotation marks,
            and this element is equal to a comma*/
            if (!columnWithPaws && oneLineChar[i] == ',') {
                if (column.length() > 0) {
                    colonsLine.add(column.toString());
                    column = new StringBuilder();
                }
            }

            /* Checks to see if there is an empty column at the beginning, middle, or end of the line
            and add to list*/
            if (isEmptyColumns(i, oneLineChar)) {
                colonsLine.add(column.toString());
                column = new StringBuilder();
            }
        }

        // If the column contains some information then add it to the list
        if (column.length() > 0) {
            colonsLine.add(column.toString());
        }
        return colonsLine;
    }

    /**
     * This method checks whether the column starts with quotation marks or not.
     *
     * @param i           iteration on a line with data.
     * @param oneLineChar a line with data.
     * @return returns the result of whether the column with quotation marks begins or not.
     */
    private boolean isStartPaws(int i, char[] oneLineChar) {
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
    private boolean isClosePaws(int i, char[] oneLineChar) {
        return i > 0 && i == oneLineChar.length - 1 && oneLineChar[i] == '"'
                || i < oneLineChar.length - 1 && oneLineChar[i] == '"' && oneLineChar[i + 1] == ',';
    }

    /**
     * This method checks to see if there is an empty column at the beginning, middle, or end of the line.
     *
     * @param i           iteration on a line with data.
     * @param oneLineChar a line with data.
     * @return the result if there is an empty column in the row.
     */
    private boolean isEmptyColumns(int i, char[] oneLineChar) {
        return i == 0 && oneLineChar[i] == ','
                || i == oneLineChar.length - 1 && oneLineChar[i] == ','
                || i > 0 && i < oneLineChar.length - 1 && oneLineChar[i] == ',' && oneLineChar[i + 1] == ',';
    }
}
