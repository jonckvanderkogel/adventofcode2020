package org.bullet.day4;

import io.vavr.Tuple2;
import org.bullet.util.FileProcessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PassportFileParser {
    public static List<List<Tuple2<String, String>>> parseFile(String fileName) throws IOException {
        List<String> lines = FileProcessing.parseLinesFromFile(fileName);

        return splitFieldsIntoTuples(combineLines(lines));
    }

    private static List<List<String>> combineLines(List<String> lines) {
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

    private static List<List<Tuple2<String, String>>> splitFieldsIntoTuples(List<List<String>> combinedLines) {
        return combinedLines
                .stream()
                .map(
                        passportFields -> passportFields
                                .stream()
                                .map(field -> {
                                    String[] splitField = field.split(":");
                                    return new Tuple2<>(splitField[0], splitField[1]);
                                })
                                .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }
}
