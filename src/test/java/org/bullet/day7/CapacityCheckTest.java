package org.bullet.day7;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapacityCheckTest {
    @Test
    public void shouldBeAbleToCountCapacityForColor() {
        BagFileParser parser = new BagFileParser();
        Map<String, List<String>> reversedMap = parser.reversedCapacityMap(parser.buildCapacityMap("testFileDay7Part1.txt"));

        assertEquals(4, new CapacityCheck().countBagsCapableOfHoldingColor("shiny gold", reversedMap));
    }

    @Test
    public void shouldBeAbleToCalculateCapacity() {
        BagFileParser parser = new BagFileParser();
        Map<String, List<Capacity>> capacityMap1 = parser.buildCapacityMap("testFileDay7Part1.txt");
        Map<String, List<Capacity>> capacityMap2 = parser.buildCapacityMap("testFileDay7Part2.txt");
        CapacityCheck capacityCheck = new CapacityCheck();

        assertEquals(32, capacityCheck.countBagsCapacity("shiny gold", capacityMap1));
        assertEquals(126, capacityCheck.countBagsCapacity("shiny gold", capacityMap2));
    }
}
