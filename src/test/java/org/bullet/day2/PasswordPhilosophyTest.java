package org.bullet.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordPhilosophyTest {
    private String line1 = "1-3 a: abcde";
    private String line2 = "1-3 b: cdefg";
    private String line3 = "2-9 c: ccccccccc";

    @Test
    public void testPasswordProcessorPart1() {
        PasswordLineProcessor p = new PasswordLineProcessorPart1();

        assertTrue(p.isValid(line1));
        assertFalse(p.isValid(line2));
        assertTrue(p.isValid(line3));
    }

    @Test
    public void testPasswordProcessorPart2() {
        PasswordLineProcessor p = new PasswordLineProcessorPart2();

        assertTrue(p.isValid(line1));
        assertFalse(p.isValid(line2));
        assertFalse(p.isValid(line3));
    }
}
