package org.bullet.day3;

import org.bullet.util.FileProcessing;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatternTest {

    @Test
    public void testPatternCorrectlyRead() throws IOException {
        Pattern pattern = new Pattern(FileProcessing.parseLinesFromFile("testFileDay3Part1.txt"));

        // we should have a tree at 0,2 and 10,10
        assertTrue(pattern.getPattern()[0][2]);
        assertTrue(pattern.getPattern()[10][10]);
    }

    @Test
    public void testMod() throws IOException {
        Pattern pattern = new Pattern(FileProcessing.parseLinesFromFile("testFileDay3Part1.txt"));
        boolean[][] matrix = pattern.getPattern();
        for (int i=1; i<pattern.patternHeight(); i++) {
            System.out.println(String.format("%d %d", i, i*5%pattern.patternWidth()));
            System.out.println(String.format("Hit tree: %b", matrix[i][i*5%pattern.patternWidth()]));
        }
        // 1 3
        // 2 6
        // 3 9
        // 4 1
        // 5 4
        // 6 7
        // 7 10
    }
}
