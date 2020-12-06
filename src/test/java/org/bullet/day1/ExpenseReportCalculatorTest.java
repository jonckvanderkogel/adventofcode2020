package org.bullet.day1;

import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.bullet.util.FileProcessing.streamLinesFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpenseReportCalculatorTest {
    @Test
    public void shouldFindPairThatAddsUpTo2020() throws IOException {
        List<Integer> numbers = streamLinesFromFile("testFileDay1Part1.txt")
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Tuple2<Integer,Integer> tuple = ExpenseReportCalculator.findPairFromList(numbers, 2020);
        assertEquals(new Tuple2<>(299, 1721), tuple);
    }
}
