package org.bullet.day1;

import io.vavr.Tuple2;
import io.vavr.Tuple3;

import java.util.List;
import java.util.stream.Collectors;

import static org.bullet.util.FileProcessing.streamLinesFromFile;

public class ExpenseReportFileProcessor {
    public static void main(String... args) {
        List<Integer> numbers = streamLinesFromFile("inputDay1.txt")
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Tuple2<Integer,Integer> tuple = ExpenseReportCalculator.findPairFromList(numbers, 2020);
        System.out.println(tuple);
        System.out.println(String.format("Answer to part1: %d", tuple._1() * tuple._2()));

        ExpenseReportCalculator.findTripleFromList(numbers, 2020)
                .ifPresent(v -> {
                    System.out.println(v);
                    long multipliedTriple = Long.valueOf(v._1()) * Long.valueOf(v._2()) * Long.valueOf(v._3());
                    System.out.println(String.format("Answer to part2: %d", multipliedTriple));
                });
    }
}
