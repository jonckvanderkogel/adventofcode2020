package org.bullet.day5;

import static org.bullet.day5.TailCalls.done;

public class Partitioning {
    public static int determineSeatNumber(String partitioningString) {
        String rowEncoding = partitioningString.substring(0,7);
        String columnEncoding = partitioningString.substring(7);

        Integer row = processEncodedPartitioningString(rowEncoding, new Range(0, 127)).invoke();
        Integer column = processEncodedPartitioningString(columnEncoding, new Range(0, 7)).invoke();

        return row * 8 + column;
    }

    public static TailCall<Integer> processEncodedPartitioningString(final String encodedPartitioning, final Range range) {
        if (range.getLowerBoundary() == range.getUpperBoundary() || encodedPartitioning.length() == 0) {
            if (range.getLowerBoundary() != range.getUpperBoundary()) {
                throw new RuntimeException("Something went wrong wince we no longer have encoded instructions but upperbound is not equal to lowerbound");
            }
            return done(range.getLowerBoundary());
        } else {
            SECTION section = SECTION.parseSection(encodedPartitioning.substring(0, 1));
            String tail = encodedPartitioning.substring(1);

            return switch(section) {
                case FRONT, LEFT -> processEncodedPartitioningString(tail, keepLowerHalf(range));
                case BACK, RIGHT -> processEncodedPartitioningString(tail, keepUpperHalf(range));
                case INVALID -> throw new RuntimeException("Invalid instruction");
            };
        }
    }

    /*
    Start by considering the whole range, rows 0 through 127.
    F means to take the lower half, keeping rows 0 through 63.
    B means to take the upper half, keeping rows 32 through 63.
    F means to take the lower half, keeping rows 32 through 47.
    B means to take the upper half, keeping rows 40 through 47.
    B keeps rows 44 through 47.
    F keeps rows 44 through 45.
    The final F keeps the lower of the two, row 44.
     */
    private static Range keepLowerHalf(final Range range) {
        int lowerBound = range.getLowerBoundary();
        int upperBound = range.getUpperBoundary();
        int newUpperBound = upperBound - (int) Math.ceil((double)(upperBound - lowerBound) / 2);

        return new Range(lowerBound, newUpperBound);
    }

    private static Range keepUpperHalf(final Range range) {
        int lowerBound = range.getLowerBoundary();
        int upperBound = range.getUpperBoundary();
        int newLowerBound = lowerBound + (int) Math.ceil((double)(upperBound - lowerBound) / 2);

        return new Range(newLowerBound, upperBound);
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
}
