package org.bullet.day3;

import org.bullet.util.FileProcessing;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreeCounterTest {
    @Test
    public void testTreeCounter() throws IOException {
        Pattern pattern = new Pattern(FileProcessing.parseLinesFromFile("testFileDay3Part1.txt"));

        long treeCountDown1Right1 = TreeCounter.countTreesOnRoute(pattern, 1, 1);
        assertEquals(2, treeCountDown1Right1);

        long treeCountDown1Right3 = TreeCounter.countTreesOnRoute(pattern, 1, 3);
        assertEquals(7, treeCountDown1Right3);

        long treeCountDown1Right5 = TreeCounter.countTreesOnRoute(pattern, 1, 5);
        assertEquals(3, treeCountDown1Right5);

        long treeCountDown1Right7 = TreeCounter.countTreesOnRoute(pattern, 1, 7);
        assertEquals(4, treeCountDown1Right7);

        long treeCountDown2Right1 = TreeCounter.countTreesOnRoute(pattern, 2, 1);
        assertEquals(2, treeCountDown2Right1);

        assertEquals(336, treeCountDown1Right1*treeCountDown1Right3*treeCountDown1Right5*treeCountDown1Right7*treeCountDown2Right1);
    }
}
