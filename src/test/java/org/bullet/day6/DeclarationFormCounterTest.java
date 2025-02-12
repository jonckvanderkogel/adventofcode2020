package org.bullet.day6;

import org.bullet.util.FileProcessing;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeclarationFormCounterTest {
    @Test
    public void shouldBeAbleToCountAnswersWhereAPersonInTheGroupAnsweredYes() {
        List<String> lines = FileProcessing.parseLinesFromFile("testFileDay6Part1.txt");
        List<List<String>> combinedLines = FileProcessing.combineLines(lines);

        assertEquals(11, DeclarationFormCounter.countAnswersWhereAPersonInTheGroupAnsweredYes(combinedLines));
    }

    @Test
    public void shouldBeAbleToCountAnswersWhereEveryPersonInTheGroupAnsweredYes() {
        List<String> lines = FileProcessing.parseLinesFromFile("testFileDay6Part1.txt");
        List<List<String>> combinedLines = FileProcessing.combineLines(lines);

        assertEquals(6, DeclarationFormCounter.countAnswersWhereEveryPersonInTheGroupAnsweredYes(combinedLines));
    }
}
