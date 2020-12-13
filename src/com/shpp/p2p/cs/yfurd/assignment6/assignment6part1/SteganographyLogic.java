package com.shpp.p2p.cs.yfurd.assignment6.assignment6part1;

import acm.graphics.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        boolean[][] pixelBlackOrWhite = new boolean[pixelArray.length][pixelArray[0].length];
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[i].length; j++) {
                int red = GImage.getRed(pixelArray[i][j]);
                pixelBlackOrWhite[i][j] = red % 2 == 1;
            }
        }
        return pixelBlackOrWhite;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        int[][] pixelArray = source.getPixelArray();
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < message[i].length; j++) {
                int red = GImage.getRed(pixelArray[i][j]);
                //if message[i][j] is true -> black, else -> false
                boolean isBlack = message[i][j];
                red = checkAndSetRed(red, isBlack);
                pixelArray[i][j] = GImage.createRGBPixel(
                        red,
                        GImage.getGreen(pixelArray[i][j]),
                        GImage.getBlue(pixelArray[i][j]),
                        GImage.getAlpha(pixelArray[i][j])
                );
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Depending on the input pixel color, this method makes the color even or not even.
     *
     * @param red     red value in the pixel.
     * @param isBlack contains black or red.
     * @return editable red pixel particle.
     */
    private static int checkAndSetRed(int red, boolean isBlack) {
        if (isBlack) {
            return red | 1;
        } else {
            return red & 254;
        }
    }
}

