package com.shpp.p2p.cs.yfurd.assignment15;

import java.io.File;
import java.io.IOException;

public class Input {

    Archive arch = new Archive();
    UnArchive unArch = new UnArchive();

    public void inputDate() {
        inputDate("src/com/shpp/p2p/cs/yfurd/assignment14/test/smoke.bmp");
    }

    public void inputDate(String path) {
        if (isArchive(path)) {
            StringBuilder output = new StringBuilder(path);
            for (int i = 0; i < 4; i++) {
                output.deleteCharAt(output.length() - 1);
            }
            File file = new File(output.toString());
            checkFileExist(file);
            unArch.unArchive(new File(path), file);
        } else {
            File file = new File(path.concat(".par"));
            checkFileExist(file);
            arch.archive(new File(path), file);
        }
    }

    public void inputDate(String path1, String path2){
        if (isArchive(path1)){
            unArch.unArchive(new File(path1), new File(path2));
        } else {
            arch.archive(new File(path1), new File(path2));
        }
    }

    public void inputDate(String flag, String path1, String path2) {
        if (flag.equals("-a")){
            arch.archive(new File(path1), new File(path2));
        } else if (flag.equals("-u")){
            unArch.unArchive(new File(path1), new File(path2));
        } else {
            System.out.println("Wrong flag");
            System.exit(1);
        }
    }

    /**
     * This method recognizes whether the archive came in or not.
     *
     * @param path input file.
     * @return true if the input file is an archive, or does not lie if the input file is not an archive.
     */
    private boolean isArchive(String path) {
        return path.length() > 4
                && path.charAt(path.length() - 1) == 'r'
                && path.charAt(path.length() - 2) == 'a'
                && path.charAt(path.length() - 3) == 'p'
                && path.charAt(path.length() - 4) == '.';
    }

    /**
     * This method checks if there is a file in which you are going to write information,
     * if so, it does not overwrite it, but simply terminates the program
     *
     * @param file this method looks at whether the file you are going to burn exists or not.
     */
    private void checkFileExist(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Output file exist! Delete file -> " + file);
            System.exit(1);
        }
    }
}
