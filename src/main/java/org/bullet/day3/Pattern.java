package org.bullet.day3;

import lombok.Getter;

import java.util.List;
import java.util.stream.IntStream;

@Getter
public class Pattern {
    private boolean[][] pattern;

    public Pattern(List<String> patternString) {
        pattern = parsePatternString(patternString);
    }

    public int patternWidth() {
        return pattern[0].length;
    }

    public int patternHeight() {
        return pattern.length;
    }

    private boolean[][] parsePatternString(List<String> patternString) {
        boolean[][] matrix = new boolean[patternString.size()][patternString.get(0).length()];

        IntStream.range(0, patternString.size())
                .boxed()
                .forEach(i -> matrix[i] = convertCharArrayToIntArray(patternString.get(i).toCharArray()));

        return matrix;
    }

    private boolean[] convertCharArrayToIntArray(char[] charArray) {
        boolean[] booleans = new boolean[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            booleans[i] = charArray[i] == '#';
        }

        return booleans;
    }
}
