package com.shpp.p2p.cs.yfurd.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {
    private String name;
    private List<Integer> ranks = new ArrayList<>();

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] result = line.split(" ");
        for (int x = 0; x < result.length; x++) {
            if (x != 0) {
                ranks.add(Integer.parseInt(result[x]));
            } else {
                name = result[x];
            }
        }
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return ranks.get(decade - 1);
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        return name + " " + ranks;
    }

//    public static void main(String[] args) {
//        String s = "Sam 58 69 99 131 168 236 278 380 467 408 466 997";
//        NameSurferEntry nameSurferEntry = new NameSurferEntry(s);
//        System.out.println(nameSurferEntry.getName());
//        System.out.println(nameSurferEntry.toString());
//        System.out.println(nameSurferEntry.getRank(5));
//
//    }
}

