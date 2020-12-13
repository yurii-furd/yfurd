package com.shpp.p2p.cs.yfurd.assignment6.assignment6part3;

public class ToneMatrixLogic {
    private static final int TONE_MATRIX_HEIGHT = 16;

    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];

        //toneMatrix[row][col]
        double sumSamples = 0.0;
        for (int i = 0; i < TONE_MATRIX_HEIGHT; i++) {
            if (toneMatrix[i][column]) {
                sumSamples += samples[i][column];
            }
            if (i == TONE_MATRIX_HEIGHT - 1) {
                result[i] = sumSamples;
                sumSamples = 0.0;
            }
        }
//        System.out.println(result[0]);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println(result);
//        }
        System.out.println(result);

        return result;
    }
}
