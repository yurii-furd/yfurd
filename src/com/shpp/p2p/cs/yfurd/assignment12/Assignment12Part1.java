package com.shpp.p2p.cs.yfurd.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Assignment12Part1 {
    //    final static String path = "src/com/shpp/p2p/cs/yfurd/assignment12/test/1.png";
//        final static String path2 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/2.png";
//    final static String path3 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/3.png";
//    final static String path4 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/4.png";
//    final static String path5 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/5.png";
//        final static String path6 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/6.png";
//    final static String path7 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/7.png";
//    final static String path8 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/8.png";
//    final static String path9 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/9.png";
//    final static String path10 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/10.png";
//    final static String path11 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/11.png";
//    final static String path12 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/12.png";
//    final static String path13 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/13.png";
//    final static String path14 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/14.jpg";
    final static String path15 = "src/com/shpp/p2p/cs/yfurd/assignment12/test/14.png";
//    final static String test = "src/com/shpp/p2p/cs/yfurd/assignment12/test/test.png";

    public static void main(String[] args) {
        File file = new File(path15);

        try {
            BufferedImage image = ImageIO.read(file);

            boolean[][] isTr = new boolean[image.getWidth()][image.getHeight()];
//            int[][] isTr = new int[image.getWidth()][image.getHeight()];

            Color color1 = new Color(image.getRGB(0, 0), true);
            Map<Color, Color> map = new HashMap<>();

//            System.out.println(image.getWidth());
//            System.out.println(image.getHeight());

            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {

                    Color color = new Color(image.getRGB(i, j), true);

                    if (!map.containsKey(color)){
                        map.put(color, color);
                    }

                    isTr[i][j] = color1.getAlpha() + 100 < color.getAlpha() || color1.getAlpha() - 100 > color.getAlpha();
//                    if (color1.getAlpha() + 5 < color.getAlpha() || color1.getAlpha() - 5 > color.getAlpha()) {
//                        isTr[i][j] = 1;
//                    } else {
//                        isTr[i][j] = 0;
//                    }

                }
            }

            System.out.println(map);
            System.out.println(map.size());


//            for (int i = 0; i < isTr.length; i++) {
//                for (int j = 0; j < isTr[0].length; j++) {
//                    System.out.print(isTr[i][j] + " ");
//                }
//                System.out.println();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
