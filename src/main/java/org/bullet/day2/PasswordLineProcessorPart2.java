package org.bullet.day2;

public class PasswordLineProcessorPart2 implements PasswordLineProcessor {
    @Override
    public boolean isValid(String passwordLine) {
        LineItem lineItem = parseLine(passwordLine);
        return charAtRequiredPosition(lineItem.getFirstRestriction() - 1, lineItem.getPassword(), lineItem.getRequiredChar()) ^
                charAtRequiredPosition(lineItem.getSecondRestriction() - 1, lineItem.getPassword(), lineItem.getRequiredChar());
    }

    private boolean charAtRequiredPosition(int charPosition, String password, String requiredChar) {
        return requiredChar.equals(String.valueOf(password.charAt(charPosition)));
    }
}
