package org.bullet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartitioningTest {
    @Test
    public void testDetermineSeatNumber() {
        /*
        BFFFBBFRRR: row 70, column 7, seat ID 567.
        FFFBBBFRRR: row 14, column 7, seat ID 119.
        BBFFBBFRLL: row 102, column 4, seat ID 820.
         */
        Partitioning.determineSeatNumber("BFFFBBFRRR")
                .ifPresent(s -> assertEquals(567, s));

        Partitioning.determineSeatNumber("FFFBBBFRRR")
                .ifPresent(s -> assertEquals(119, s));

        Partitioning.determineSeatNumber("BBFFBBFRLL")
                .ifPresent(s -> assertEquals(820, s));
    }
}
