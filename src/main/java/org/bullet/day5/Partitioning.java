package org.bullet.day5;

import org.bullet.util.Range;
import org.bullet.util.TailCall;

import static org.bullet.util.TailCalls.done;

public class Partitioning {
    public static int determineSeatNumber(String partitioningString) {
        String rowEncoding = partitioningString.substring(0,7);
        String columnEncoding = partitioningString.substring(7);

        Integer row = processEncodedPartitioningString(rowEncoding, new Range(0, 127)).invoke();
        Integer column = processEncodedPartitioningString(columnEncoding, new Range(0, 7)).invoke();

        return row * 8 + column;
    }

    public static TailCall<Integer> processEncodedPartitioningString(final String encodedPartitioning, final Range range) {
        if (range.getLowerBound() == range.getUpperBound() || encodedPartitioning.length() == 0) {
            if (range.getLowerBound() != range.getUpperBound()) {
                throw new RuntimeException("Something went wrong wince we no longer have encoded instructions but upperbound is not equal to lowerbound");
            }
            return done(range.getLowerBound());
        } else {
            SECTION section = SECTION.parseSection(encodedPartitioning.substring(0, 1));
            String tail = encodedPartitioning.substring(1);

            return switch(section) {
                case FRONT, LEFT -> () -> processEncodedPartitioningString(tail, keepLowerHalf(range));
                case BACK, RIGHT -> () -> processEncodedPartitioningString(tail, keepUpperHalf(range));
                case INVALID -> throw new RuntimeException("Invalid instruction");
            };
        }
    }

    // F means "front", B means "back", L means "left", and R means "right"
    private enum SECTION {
        FRONT, BACK, LEFT, RIGHT, INVALID;

        public static SECTION parseSection(String sectionString) {
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
