package org.bullet.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PassportFileParserTest {
    @Test
    public void passportFileParserShouldBeAbleToParseTextFile() throws IOException {
        var passportData = PassportFileParser.parseFile("testFileDay4Part1.txt");

        var passports = passportData
                .stream()
                .map(PassportFactory::createPassport)
                .collect(Collectors.toList());

        PassportValidator validator = new PassportValidator();

        assertEquals(4, passports.size());
        assertTrue(validator.isValidPassport(passports.get(0)));
        assertFalse(validator.isValidPassport(passports.get(1)));
        assertTrue(validator.isValidPassport(passports.get(2)));
        assertFalse(validator.isValidPassport(passports.get(3)));
    }
}
