package org.bullet.day6;

import org.bullet.util.FileProcessing;

import java.io.IOException;
import java.util.List;

public class FormProcessor {
    public static void main(String... args) throws IOException {
        List<String> lines = FileProcessing.parseLinesFromFile("inputDay6.txt");
        List<List<String>> combinedLines = FileProcessing.combineLines(lines);

        System.out.println(String.format("Part 1: %s", DeclarationFormCounter.countAnswersWhereAPersonInTheGroupAnsweredYes(combinedLines)));

        System.out.println(String.format("Part 2: %s", DeclarationFormCounter.countAnswersWhereEveryPersonInTheGroupAnsweredYes(combinedLines)));
    }
}
