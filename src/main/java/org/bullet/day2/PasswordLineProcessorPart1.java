package org.bullet.day2;

import org.apache.commons.lang3.StringUtils;

public class PasswordLineProcessorPart1 implements PasswordLineProcessor {
    @Override
    public boolean isValid(String passwordLine) {
        LineItem lineItem = parseLine(passwordLine);
        int occurrences = StringUtils.countMatches(lineItem.getPassword(), lineItem.getRequiredChar());
        return occurrences >= lineItem.getFirstRestriction() && occurrences <= lineItem.getSecondRestriction();
    }
}
