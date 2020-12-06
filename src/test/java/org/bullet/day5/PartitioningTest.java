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
        assertEquals(567, Partitioning.determineSeatNumber("BFFFBBFRRR"));
        assertEquals(119, Partitioning.determineSeatNumber("FFFBBBFRRR"));
        assertEquals(820, Partitioning.determineSeatNumber("BBFFBBFRLL"));
    }
}
