package org.bullet.day2;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public interface PasswordLineProcessor {
    Pattern pattern = Pattern.compile("([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)");

    boolean isValid(String passwordLine);

    default Optional<LineItem> parseLine(String passwordLine) {
        Matcher m = pattern.matcher(passwordLine);
        if (m.find()) {
            return Optional.of(new LineItem(parseInt(m.group(1)), parseInt(m.group(2)), m.group(3), m.group(4)));
        } else {
            return Optional.empty();
        }
    }
}
