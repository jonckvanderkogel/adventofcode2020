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
}
