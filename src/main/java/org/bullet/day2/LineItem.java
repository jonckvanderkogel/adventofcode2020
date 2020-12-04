package org.bullet.day2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LineItem {
    private final int firstRestriction;
    private final int secondRestriction;
    private final String requiredChar;
    private final String password;
}
