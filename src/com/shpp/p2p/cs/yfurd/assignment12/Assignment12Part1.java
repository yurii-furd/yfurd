package com.shpp.p2p.cs.yfurd.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Assignment12Part1 {
    final static String path = "src/com/shpp/p2p/cs/yfurd/assignment12/test/1.png";//true 5
    final static String path2 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/2.png";//true 36
    final static String path3 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/3.png";//true 5
    final static String path4 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/4.png";//true 9 + 1  !!
    final static String path5 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/5.png";//true 5 + 1  !!
    final static String path6 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/6.png";//true 8
    final static String path7 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/7.png";//true 17
    final static String path8 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/8.png";//false
    final static String path9 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/9.png";//true 9
    final static String path10 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/10.png";//false
    final static String path11 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/11.png";//false
    final static String path12 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/12.png";//true 36
    final static String path13 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/13.png";//false
    final static String path14 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/14.jpg";//true 77
    final static String path15 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/15.png";//true 77
    final static String path16 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/16.png";
    final static String path17 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/small.png";
    final static Set colorBlack = groupColor();

    public static void main(String[] args) {                                          //-Xss256m
        Assignment12Part1 part1 = new Assignment12Part1();
        File file = new File(path2);
        try {
            BufferedImage image = ImageIO.read(file);
            boolean[][] isVisited = new boolean[image.getHeight()][image.getWidth()];
            int countSilhouettes = 0;
            for (int i = 1; i < image.getHeight() - 1; i++) {
                for (int j = 1; j < image.getWidth() - 1; j++) {
                    Color color = new Color(image.getRGB(j, i));
                    if (colorBlack.contains(color) && !isVisited[i][j]) {
                        countSilhouettes++;
                        part1.foundSilhouettes(i, j, image, isVisited);
                    } else {
                        isVisited[i][j] = true;
                    }
                }
            }
            System.out.println(" -> " + countSilhouettes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method recognizes silhouettes in the input image.
     *
     * @param i y coordinate of x bricks.
     * @param j x coordinate of x bricks.
     * @param image input image.
     * @param isVisited an array in which the visited and unvisited pixels in the image are marked.
     */
    private void foundSilhouettes(int i, int j, BufferedImage image, boolean[][] isVisited) {
        Color color = new Color(image.getRGB(j, i));
        if (!isVisited[i][j] && i > 0 && j > 0 && i < image.getHeight() - 1 && j < image.getWidth() - 1 && colorBlack.contains(color)) {
            isVisited[i][j] = true;
            foundSilhouettes(i + 1, j, image, isVisited);
            foundSilhouettes(i + 1, j - 1, image, isVisited);
            foundSilhouettes(i, j - 1, image, isVisited);
            foundSilhouettes(i - 1, j - 1, image, isVisited);
            foundSilhouettes(i - 1, j, image, isVisited);
            foundSilhouettes(i - 1, j + 1, image, isVisited);
            foundSilhouettes(i, j + 1, image, isVisited);
            foundSilhouettes(i + 1, j + 1, image, isVisited);
        }
        isVisited[i][j] = true;
    }

    /**
     * This method makes similar colors in one sheet.
     *
     * @return letter of similar colors.
     */
    private static Set groupColor() {
        Set<Color> set = new HashSet<>();
        for (int i = 0; i <= 128; i++) {
            for (int j = 0; j <= 128; j++) {
                for (int k = 0; k < 128; k++) {
                    set.add(new Color(i, j, k));
                }
            }
        }
        return set;
    }
}
