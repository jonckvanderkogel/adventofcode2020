package org.bullet.day5;

import org.bullet.util.Range;
import org.bullet.util.TailCall;

import java.util.Optional;

import static org.bullet.util.TailCalls.done;

public class Partitioning {
    public static Optional<Integer> determineSeatNumber(String partitioningString) {
        String rowEncoding = partitioningString.substring(0,7);
        String columnEncoding = partitioningString.substring(7);

        Optional<Integer> row = processEncodedPartitioningString(rowEncoding, new Range(0, 127)).invoke();
        Optional<Integer> column = processEncodedPartitioningString(columnEncoding, new Range(0, 7)).invoke();

        return row
                .flatMap(r -> column
                        .map(c -> r * 8 + c)
                );
    }

    public static TailCall<Optional<Integer>> processEncodedPartitioningString(final String encodedPartitioning, final Range range) {
        if (range.getLowerBound() == range.getUpperBound() || encodedPartitioning.length() == 0) {
            return done(Optional.of(range.getLowerBound()));
        } else {
            Section section = Section.parseSection(encodedPartitioning.substring(0, 1));
            String tail = encodedPartitioning.substring(1);

            return switch(section) {
                case FRONT, LEFT -> () -> processEncodedPartitioningString(tail, keepLowerHalf(range));
                case BACK, RIGHT -> () -> processEncodedPartitioningString(tail, keepUpperHalf(range));
                case INVALID -> done(Optional.empty());
            };
        }
    }

    // F means "front", B means "back", L means "left", and R means "right"
    private enum Section {
        FRONT, BACK, LEFT, RIGHT, INVALID;

        public static Section parseSection(String sectionString) {
            return switch(sectionString.toUpperCase()) {
                case "F" -> FRONT;
                case "B" -> BACK;
                case "L" -> LEFT;
                case "R" -> RIGHT;
                default -> INVALID;
            };
        }
    }

    public static Range keepLowerHalf(final Range range) {
        int lowerBound = range.getLowerBound();
        int upperBound = range.getUpperBound();
        int newUpperBound = upperBound - (int) Math.ceil((double)(upperBound - lowerBound) / 2);

        return new Range(lowerBound, newUpperBound);
    }

    public static Range keepUpperHalf(final Range range) {
        int lowerBound = range.getLowerBound();
        int upperBound = range.getUpperBound();
        int newLowerBound = lowerBound + (int) Math.ceil((double)(upperBound - lowerBound) / 2);

        return new Range(newLowerBound, upperBound);
    }
}
