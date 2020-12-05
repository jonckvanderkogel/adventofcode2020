package org.bullet.day4;

import lombok.RequiredArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

// amb blu brn gry grn hzl oth
@RequiredArgsConstructor
public class EyeColor {
    @NotNull
    private final COLOR color;

    @AssertTrue
    private boolean isColorOk() {
        return switch(color) {
            case AMB,BLU,BRN,GRY,GRN,HZL,OTH -> true;
            case INVALID -> false;
        };
    }

    public enum COLOR {
        AMB,BLU,BRN,GRY,GRN,HZL,OTH,INVALID;

        public static EyeColor.COLOR parseColor(String colorString) {
            return switch(colorString.toLowerCase()) {
                case "amb" -> AMB;
                case "blu" -> BLU;
                case "brn" -> BRN;
                case "gry" -> GRY;
                case "grn" -> GRN;
                case "hzl" -> HZL;
                case "oth" -> OTH;
                default -> INVALID;
            };
        }
    }
}
