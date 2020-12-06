package org.bullet.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessing {
    public static Stream<String> streamLinesFromFile(String fileName) throws IOException {
        String filePath = FileProcessing.class.getClassLoader().getResource(fileName).getPath();
        return Files.lines(Paths.get(filePath));
    }

    public static List<String> parseLinesFromFile(String fileName) throws IOException {
        return streamLinesFromFile(fileName)
                .collect(Collectors.toList());
    }

    public static List<List<String>> combineLines(List<String> lines) {
        List<List<String>> combinedLines = new ArrayList<>();
        List<String> linesBelongingToEachOther = new ArrayList<>();
        for (String line : lines) {
            if (line.length() != 0) {
                linesBelongingToEachOther.addAll(Arrays.asList(line.split(" ")));
            } else {
                combinedLines.add(linesBelongingToEachOther);
                linesBelongingToEachOther = new ArrayList<>();
            }
        }
        combinedLines.add(linesBelongingToEachOther);
        return combinedLines;
    }
}
