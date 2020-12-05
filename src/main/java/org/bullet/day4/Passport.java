package org.bullet.day4;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class Passport {
    // byr (Birth Year) - four digits; at least 1920 and at most 2002.
    @NotNull
    @Min(1920)
    @Max(2002)
    private Integer byr;

    // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    @NotNull
    @Min(2010)
    @Max(2020)
    private Integer iyr;

    // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    @NotNull
    @Min(2020)
    @Max(2030)
    private Integer eyr;

    // hgt (Height) - a number followed by either cm or in:
    // If cm, the number must be at least 150 and at most 193.
    // If in, the number must be at least 59 and at most 76.
    @NotNull
    @Valid
    private Height hgt;

    // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    @Pattern(regexp = "#[a-f0-9]{6}")
    @NotNull
    private String hcl;

    // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    @NotNull
    @Valid
    private EyeColor ecl;

    // pid (Passport ID) - a nine-digit number, including leading zeroes.
    @Pattern(regexp = "[0-9]{9}")
    @NotNull
    private String pid;

    // cid (Country ID) - ignored, missing or not.
    // Only cid is optional
    private String cid;
}
