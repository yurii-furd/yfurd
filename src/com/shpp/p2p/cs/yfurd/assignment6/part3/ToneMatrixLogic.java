package com.shpp.p2p.cs.yfurd.assignment6.part3;

public class ToneMatrixLogic {
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
        for (int i = 0; i < toneMatrix.length; i++) {
            if (toneMatrix[i][column]) {
                for (int j = 0; j < samples[i].length; j++) {
                    result[j] += samples[i][j];
                }
            }
        }
        double max = result[0];
        for (int i = 1; i < result.length; i++) {
            if (Math.abs(max) < Math.abs(result[i])) {
                max = result[i];
            }
        }
        if (max != 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= max;
            }
        }
        return result;
    }
}
