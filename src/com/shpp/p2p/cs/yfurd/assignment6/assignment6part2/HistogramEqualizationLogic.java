package com.shpp.p2p.cs.yfurd.assignment6.assignment6part2;

import java.util.ArrayList;
import java.util.List;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 256;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        List<Integer> list = new ArrayList<>();
        int[] histogram = new int[MAX_LUMINANCE];

        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                int luminancesValue = luminances[i][j];
                list.add(luminancesValue);
            }
        }

        int sum = 0;
        for (int i = 0; i < histogram.length; i++) {
            for (Integer value : list) {
                if (value == i) {
                    sum++;
                }
            }
            histogram[i] = sum;
            sum = 0;
        }
        System.out.println(histogram.length);
        return histogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] totalHistogram = new int[histogram.length];

        histogram[0] = totalHistogram[0];
        for (int i = 1; i < histogram.length; i++) {
//            if (histogram[i] + histogram[i - 1] <= histogram[histogram.length-1]) {
//                totalHistogram[i] = histogram[i] + totalHistogram[i];
                totalHistogram[i] = histogram[i] + histogram[i - 1];
                histogram[i] += histogram[i - 1];
//            } else {
//                totalHistogram[i] = histogram[i];
//            }
        }
        return totalHistogram;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        int sum = 0;
        for (int i = 0; i < luminances.length; i++) {
            for (int j = 0; j < luminances[i].length; j++) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        /* TODO: Implement this method! */
        return null;
    }
}
