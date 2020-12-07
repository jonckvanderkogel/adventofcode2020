package org.bullet.day4;

import java.util.stream.Collectors;

public class PassportFileProcessor {
    public static void main(String... args) {
        var passportData = PassportFileParser.parseFile("inputDay4.txt");

        var passports = passportData
                .stream()
                .map(PassportFactory::createPassport)
                .collect(Collectors.toList());

        PassportValidator validator = new PassportValidator();

        long validPassportCount = passports
                .stream()
                .filter(validator::isValidPassport)
                .count();

        System.out.println(String.format("Valid passports: %d", validPassportCount));
    }
}
