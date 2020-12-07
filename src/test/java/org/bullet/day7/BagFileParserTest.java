package org.bullet.day7;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BagFileParserTest {
    @Test
    public void shouldBeAbleToParseLines() {
        BagFileParser parser = new BagFileParser();
        Map<String, List<Capacity>> capacityMap = parser.buildCapacityMap("testFileDay7Part1.txt");

        assertEquals(List.of(
                new Capacity(3, "bright white"),
                new Capacity(4, "muted yellow")
        ), capacityMap.get("dark orange"));

        assertEquals(Collections.emptyList(), capacityMap.get("dotted black"));
    }

    @Test
    public void shouldBeAbleToReverseCapacityMap() {
        BagFileParser parser = new BagFileParser();
        Map<String, List<Capacity>> capacityMap = parser.buildCapacityMap("testFileDay7Part1.txt");
        Map<String, List<String>> reversedMap = parser.reversedCapacityMap(capacityMap);

        assertEquals(List.of("muted yellow", "bright white"), reversedMap.get("shiny gold"));
    }
}
