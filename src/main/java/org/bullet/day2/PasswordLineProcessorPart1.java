package org.bullet.day2;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class PasswordLineProcessorPart1 implements PasswordLineProcessor {
    @Override
    public boolean isValid(String passwordLine) {
        return parseLine(passwordLine)
                .map(li -> createPredicate(
                        StringUtils.countMatches(li.getPassword(), li.getRequiredChar()))
                        .test(li))
                .orElseGet(() -> false);
    }

    private Predicate<LineItem> createPredicate(Integer occurrences) {
        return (li) -> occurrences >= li.getFirstRestriction() && occurrences <= li.getSecondRestriction();
    }
}
