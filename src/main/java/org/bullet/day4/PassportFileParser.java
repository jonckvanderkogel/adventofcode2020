package org.bullet.day4;

import io.vavr.Tuple2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.bullet.util.FileProcessing.combineLines;
import static org.bullet.util.FileProcessing.parseLinesFromFile;

public class PassportFileParser {
    private static Pattern HEIGHT_PATTERN = Pattern.compile("([0-9]*)([a-z]*)");

    public static List<List<Tuple2<String, Object>>> parseFile(String fileName) {
        List<String> lines = parseLinesFromFile(fileName);

        return splitFieldsIntoTuples(combineLines(lines));
    }

    private static List<List<Tuple2<String, Object>>> splitFieldsIntoTuples(List<List<String>> combinedLines) {
        return combinedLines
                .stream()
                .map(
                        passportFields -> passportFields
                                .stream()
                                .map(field -> {
                                    String[] splitField = field.split(":");
                                    return new Tuple2<>(splitField[0], castToCrrectType(splitField[0], splitField[1]));
                                })
                                .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    /*
    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
     */
    private static Object castToCrrectType(String fieldName, String value) {
        return switch(fieldName) {
            case "byr", "iyr", "eyr" -> Integer.parseInt(value);
            case "hgt" -> parseHeight(value);
            case "ecl" -> parseEyeColor(value);
            default -> value;
        };
    }

    private static Height parseHeight(String heightString) {
        Matcher m = HEIGHT_PATTERN.matcher(heightString);
        if (m.find()) {
            Integer measurement = Integer.parseInt(m.group(1));
            Height.Unit unit = Height.Unit.parseUnit(m.group(2).toUpperCase());

            return new Height(measurement, unit);
        } else {
            throw new RuntimeException(String.format("Invalid height string: %s", heightString));
        }
    }

    private static EyeColor parseEyeColor(String eyeColorString) {
        return new EyeColor(EyeColor.Color.parseColor(eyeColorString));
    }
}
