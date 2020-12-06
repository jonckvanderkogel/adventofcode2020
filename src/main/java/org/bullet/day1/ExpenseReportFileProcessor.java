package org.bullet.day1;

import io.vavr.Tuple2;
import io.vavr.Tuple3;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.bullet.util.FileProcessing.streamLinesFromFile;

public class ExpenseReportFileProcessor {
    public static void main(String... args) throws IOException {
        List<Integer> numbers = streamLinesFromFile("inputDay1.txt")
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Tuple2<Integer,Integer> tuple = ExpenseReportCalculator.findPairFromList(numbers, 2020);
        System.out.println(tuple);
        System.out.println(String.format("Answer to part1: %d", tuple._1() * tuple._2()));

        Tuple3<Integer, Integer, Integer> triple = ExpenseReportCalculator.findTripleFromList(numbers, 2020);
        System.out.println(triple);
        long multipliedTriple = Long.valueOf(triple._1()) * Long.valueOf(triple._2()) * Long.valueOf(triple._3());
        System.out.println(String.format("Answer to part2: %d", multipliedTriple));
    }
}
