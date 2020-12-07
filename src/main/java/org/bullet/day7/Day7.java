package org.bullet.day7;

import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String... args) {
        BagFileParser parser = new BagFileParser();
        Map<String, List<Capacity>> capacityMap = parser.buildCapacityMap("inputDay7.txt");
        Map<String, List<String>> reversedMap = parser.reversedCapacityMap(capacityMap);
        CapacityCheck capacityCheck = new CapacityCheck();

        System.out.println(String.format("Output for part 1: %d", capacityCheck.countBagsCapableOfHoldingColor("shiny gold", reversedMap)));

        System.out.println(String.format("Output for part 2: %d", capacityCheck.countBagsCapacity("shiny gold", capacityMap)));
    }
}
