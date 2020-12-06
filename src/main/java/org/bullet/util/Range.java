package org.bullet.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@RequiredArgsConstructor
@Getter
public class Range {
    private final int lowerBound;
    private final int upperBound;
}
