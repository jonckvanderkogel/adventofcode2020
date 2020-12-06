package org.bullet.day5;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class Range {
    private final int lowerBoundary;
    private final int upperBoundary;
}
