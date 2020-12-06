package org.bullet.day5;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import org.bullet.util.FileProcessing;

import java.io.IOException;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PartitioningFileProcessor {
    public static void main(String... args) throws IOException {
        int max = FileProcessing.streamLinesFromFile("inputDay5.txt")
                .map(Partitioning::determineSeatNumber)
                .mapToInt(i -> i)
                .max()
                .orElseThrow(NoSuchElementException::new);

        System.out.println(String.format("Max seat number: %d", max));


        // the minimum element is 49, max 806
        int yourSeatNumber = List
                .ofAll(IntStream.rangeClosed(49, 806).boxed())
                .zip(FileProcessing.streamLinesFromFile("inputDay5.txt")
                        .map(Partitioning::determineSeatNumber)
                        .sorted()
                        .collect(Collectors.toList())
                )
                .takeWhile(tuple -> tuple._1().equals(tuple._2()))
                .maxBy(Comparator.comparingInt(Tuple2::_1))
                .getOrElseThrow(NoSuchElementException::new)
                ._1() + 1;

        System.out.println(String.format("Your seat number: %d", yourSeatNumber));
    }
}
