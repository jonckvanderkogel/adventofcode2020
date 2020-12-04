package org.bullet.day3;

import org.bullet.util.FileProcessing;

import java.io.IOException;

public class TreeFileProcessor {
    public static void main(String... args) throws IOException {
        Pattern pattern = new Pattern(FileProcessing.parseLinesFromFile("inputDay3.txt"));

        long treeCountDown1Right1 = TreeCounter.countTreesOnRoute(pattern, 1, 1);
        System.out.println(String.format("Down 1, right 1: %d", treeCountDown1Right1));

        long treeCountDown1Right3 = TreeCounter.countTreesOnRoute(pattern, 1, 3);
        System.out.println(String.format("Down 1, right 3: %d", treeCountDown1Right3));

        long treeCountDown1Right5 = TreeCounter.countTreesOnRoute(pattern, 1, 5);
        System.out.println(String.format("Down 1, right 5: %d", treeCountDown1Right5));

        long treeCountDown1Right7 = TreeCounter.countTreesOnRoute(pattern, 1, 7);
        System.out.println(String.format("Down 1, right 7: %d", treeCountDown1Right7));

        long treeCountDown2Right1 = TreeCounter.countTreesOnRoute(pattern, 2, 1);
        System.out.println(String.format("Down 2, right 1: %d", treeCountDown2Right1));

        System.out.println(String.format("Final score: %d", treeCountDown1Right1*treeCountDown1Right3*treeCountDown1Right5*treeCountDown1Right7*treeCountDown2Right1));
    }
}
