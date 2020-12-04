package org.bullet.day3;

public class TreeCounter {
    public static long countTreesOnRoute(Pattern pattern, int stepDown, int stepRight) {
        boolean[][] matrix = pattern.getPattern();
        long counter = 0;
        for (int i = 0 + stepDown, j = 1; i < pattern.patternHeight(); i = i + stepDown, j++) {
            if (matrix[i][j * stepRight % pattern.patternWidth()]) {
                counter++;
            }
        }

        return counter;
    }
}
