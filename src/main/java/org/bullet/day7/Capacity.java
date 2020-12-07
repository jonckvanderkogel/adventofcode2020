package org.bullet.day7;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@RequiredArgsConstructor
public class Capacity {
    @EqualsAndHashCode.Exclude
    private final Integer count;
    private final String color;
}
