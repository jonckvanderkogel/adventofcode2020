package org.bullet.day4;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
public class Height {
    @NotNull
    private final Integer measurement;

    @NotNull
    private final UNIT unit;

    // hgt (Height) - a number followed by either cm or in:
    // If cm, the number must be at least 150 and at most 193.
    // If in, the number must be at least 59 and at most 76.
    @AssertTrue
    private boolean isHeightOk() {
        return switch(unit) {
            case CM -> measurement >= 150 && measurement <= 193;
            case IN -> measurement >= 59 && measurement <= 76;
            case INVALID -> false;
        };
    }

    public enum UNIT {
        CM, IN, INVALID;

        public static UNIT parseUnit(String unitString) {
            return switch(unitString.toLowerCase()) {
                case "cm" -> CM;
                case "in" -> IN;
                default -> INVALID;
            };
        }
    }
}
