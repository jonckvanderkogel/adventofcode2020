package org.bullet.day2;

public class PasswordLineProcessorPart2 implements PasswordLineProcessor {
    @Override
    public boolean isValid(String passwordLine) {
        return parseLine(passwordLine)
                .map(li ->
                        charAtRequiredPosition(
                                li.getFirstRestriction() - 1,
                                li.getPassword(),
                                li.getRequiredChar()) ^
                                charAtRequiredPosition(
                                        li.getSecondRestriction() - 1,
                                        li.getPassword(),
                                        li.getRequiredChar())
                )
                .orElseGet(() -> false);
    }

    private boolean charAtRequiredPosition(int charPosition, String password, String requiredChar) {
        return requiredChar.equals(String.valueOf(password.charAt(charPosition)));
    }
}
