package org.bullet.day9;

import java.util.List;

public class Day9 {
    public static void main(String... args) {
        List<Long> numbers = new XmasFileParser().parseNumbers("inputDay9.txt");
        XmasProcessor processor = new XmasProcessor();
        Long firstInvalidNumber = processor.firstInvalidNumber(numbers, 25);
        System.out.println(String.format("Part 1: %d", firstInvalidNumber));

        System.out.println(String.format("Part 2: %s", processor.addSmallestAndLargest(numbers, processor.findRange(numbers, firstInvalidNumber))));
    }
}
