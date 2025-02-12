package org.bullet.day7;

import io.vavr.Tuple2;
import org.bullet.util.FileProcessing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BagFileParser {
    private final static Pattern KEY_PATTERN = Pattern.compile("([a-z]* [a-z]*)(?: bags)");
    private final static Pattern CAPACITY_PATTERN = Pattern.compile("([0-9]*) ([a-z]* [a-z]*)");

    public Map<String, List<Capacity>> buildCapacityMap(String fileName) {
        return FileProcessing.streamLinesFromFile(fileName)
                .map(this::parseLine)
                .flatMap(Optional::stream)
                .collect(Collectors.toMap(
                        Tuple2::_1, Tuple2::_2
                ));
    }

    public Map<String, List<String>> reversedCapacityMap(Map<String, List<Capacity>> capacityMap) {
        return capacityMap
                .entrySet()
                .stream()
                .flatMap(entry -> entry.getValue()
                        .stream()
                        .map(capacity -> new Tuple2<>(capacity.getColor(), entry.getKey()))
                )
                .collect(Collectors.groupingBy(Tuple2::_1, Collectors.mapping(Tuple2::_2, Collectors.toList())));
    }

    private Optional<Tuple2<String, List<Capacity>>> parseLine(String line) {
        String[] bags = line.split(" contain ");
        Matcher keyMatcher = KEY_PATTERN.matcher(bags[0]);
        Matcher capacityMatcher = CAPACITY_PATTERN.matcher(bags[1]);
        if (keyMatcher.find()) {
            String keyColor = keyMatcher.group(1);
            List<Capacity> capacityList = new ArrayList<>();
            while (capacityMatcher.find()) {
                if (!capacityMatcher.group(1).isEmpty()) {
                    Capacity capacity = new Capacity(Integer.parseInt(capacityMatcher.group(1)), capacityMatcher.group(2));
                    capacityList.add(capacity);
                }
            }
            return Optional.of(new Tuple2<>(keyColor, capacityList));
        } else {
            return Optional.empty();
        }
    }
}
