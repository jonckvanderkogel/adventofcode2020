package org.bullet.day5;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.bullet.util.FileProcessing;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitioningFileProcessor {
    public static void main(String... args) {
        FileProcessing.streamLinesFromFile("inputDay5.txt")
                .map(Partitioning::determineSeatNumber)
                .flatMap(Optional::stream)
                .mapToInt(i -> i)
                .max()
                .ifPresent(i -> System.out.println(String.format("Max seat number: %d", i)));


        // the minimum element is 49, max 806
        List
                .ofAll(IntStream.rangeClosed(49, 806).boxed())
                .zip(FileProcessing.streamLinesFromFile("inputDay5.txt")
                        .map(Partitioning::determineSeatNumber)
                        .flatMap(Optional::stream)
                        .sorted()
                        .collect(Collectors.toList())
                )
                .takeWhile(tuple -> tuple._1().equals(tuple._2()))
                .maxBy(Comparator.comparingInt(Tuple2::_1))
                .map(t -> t._1() + 1)
                .peek(s -> System.out.println(String.format("Your seat number: %d", s)));
    }
}
