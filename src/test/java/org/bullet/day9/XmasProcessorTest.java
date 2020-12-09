package org.bullet.day9;

import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmasProcessorTest {
    @Test
    public void shouldBeAbleToFindInvalidNumber() {
        List<Long> numbers = new XmasFileParser().parseNumbers("testFileDay9Part1.txt");
        XmasProcessor processor = new XmasProcessor();
        assertEquals(127, processor.firstInvalidNumber(numbers, 5));
    }

    @Test
    public void shouldBeAbleToFindRange() {
        List<Long> numbers = new XmasFileParser().parseNumbers("testFileDay9Part1.txt");
        XmasProcessor processor = new XmasProcessor();
        Tuple2<Integer,Integer> range = processor.findRange(numbers, 127L);
        assertEquals(new Tuple2<>(2,5), range);

        assertEquals(62, processor.addSmallestAndLargest(numbers, range));
    }
}
