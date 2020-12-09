package org.bullet.day9;

import org.bullet.util.FileProcessing;

import java.util.List;
import java.util.stream.Collectors;

public class XmasFileParser {
    public List<Long> parseNumbers(String fileName) {
        return FileProcessing.parseLinesFromFile(fileName)
                .stream()
                .map(l -> Long.parseLong(l))
                .collect(Collectors.toList());
    }
}
