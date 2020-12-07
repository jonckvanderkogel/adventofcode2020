package org.bullet.day7;

import lombok.Value;
import org.bullet.util.TailCall;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.bullet.util.TailCalls.done;
@Value
public class CapacityCheck {
    public int countBagsCapableOfHoldingColor(String color, Map<String, List<String>> reversedMap) {
        return countBagsCapableOfHoldingColorRecursively(Set.of(color), Set.of(color), reversedMap, 0).invoke();
    }

    public Integer countBagsCapacity(String color, Map<String, List<Capacity>> capacityMap) {
        return countBagsCapacityRecursively(new Capacity(1, color), capacityMap);
    }

    private Integer countBagsCapacityRecursively(final Capacity capacity, final Map<String, List<Capacity>> capacityMap) {
        return capacityMap
                .get(capacity.getColor())
                .stream()
                .mapToInt(inside -> inside.getCount() + inside.getCount() * countBagsCapacityRecursively(inside, capacityMap))
                .sum();
    }

    private TailCall<Integer> countBagsCapableOfHoldingColorRecursively(Set<String> colors, Set<String> coveredColors, Map<String, List<String>> reversedMap, Integer count) {
        if (colors.isEmpty()) {
            return done(count);
        } else {
            Set<String> newColors = colors
                    .stream()
                    .filter(reversedMap::containsKey)
                    .flatMap(color -> reversedMap.get(color).stream())
                    .filter(color -> !coveredColors.contains(color))
                    .collect(Collectors.toSet());

            Set<String> updatedCoveredColors = Stream
                    .concat(coveredColors.stream(), newColors.stream())
                    .collect(Collectors.toSet());

            return () -> countBagsCapableOfHoldingColorRecursively(newColors, updatedCoveredColors, reversedMap, count + newColors.size());
        }
    }
}
