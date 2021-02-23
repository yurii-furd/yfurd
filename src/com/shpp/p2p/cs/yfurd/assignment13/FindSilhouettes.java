package com.shpp.p2p.cs.yfurd.assignment13;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FindSilhouettes {
    final static Set colorBlack = groupColor();
    static int countSilhouettes = 0;
    static boolean[][] isVisited;
    static BufferedImage image;

    /**
     * This method reads the file, runs through each pixel and finds the silhouette.
     *
     * @param path file path (photo).
     */
    public void readFile(String path) {
        File file = new File(path);
        try {
            image = ImageIO.read(file);
            isVisited = new boolean[image.getHeight()][image.getWidth()];

            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    Color color = new Color(image.getRGB(j, i));
                    if (colorBlack.contains(color) && !isVisited[i][j]) {
                        countSilhouettes++;
                        foundSilhouettes(i, j);
                    } else {
                        isVisited[i][j] = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" -> " + countSilhouettes);
        countSilhouettes = 0;
    }

    /**
     * This method recognizes silhouettes in the input image.
     *
     * @param i     y coordinate of x.
     * @param j     x coordinate of x.
     */
    private void foundSilhouettes(int i, int j) {
        Color color = new Color(image.getRGB(j, i));
        Queue<Coordinates> queue = new LinkedList<>();
        int count = 1;
        queue.add(new Coordinates(i, j));

        while (!queue.isEmpty()) {
            Coordinates element = queue.element();
            if (
                    !isVisited[element.getX()][element.getY()]
                            && element.getX() > 0
                            && element.getY() > 0
                            && element.getX() < image.getHeight()
                            && element.getY() < image.getWidth()
                            && colorBlack.contains(color)
            ) {
                isVisited[element.getX()][element.getY()] = true;
                count++;

                count = findNextPixel(element.getX(), element.getY() + 1, count, queue);
                count = findNextPixel(element.getX() + 1, element.getY(), count, queue);
                count = findNextPixel(element.getX(), element.getY() - 1, count, queue);
                count = findNextPixel(element.getX() - 1, element.getY(), count, queue);
            }
            queue.poll();
        }
        checkSize(count);
    }

    /**
     * This check method means that the required pixel is for the input coordinates.
     *
     * @param x  coordinate of x.
     * @param y  coordinate of y.
     * @param count counts how many pixels has found about
     * @param queue in which there are pixels belonging to the silhouette
     * @return the number of pixels of the found object
     */
    private int findNextPixel(int x, int y, int count, Queue queue) {
        if (!isVisited[x][y] && colorBlack.contains(new Color(image.getRGB(y, x)))) {
            queue.add(new Coordinates(x, y));
            count++;
        }
            return count;
    }

    /**
     * This method checks for the size of the input silhouette,
     * if it is less than 20 pixels, then this object is not considered a silhouette
     *
     * @param count the sum of pixels of one object.
     */
    private void checkSize(int count) {
        if (count < 20) {
            countSilhouettes--;
        }
    }

    /**
     * This method makes similar colors in one sheet.
     *
     * @return letter of similar colors.
     */
    private static Set groupColor() {
        Set<Color> set = new HashSet<>();
        for (int i = 0; i <= 127; i++) {
            for (int j = 0; j <= 127; j++) {
                for (int k = 0; k < 127; k++) {
                    set.add(new Color(i, j, k));
                }
            }
        }
        return set;
    }
}
