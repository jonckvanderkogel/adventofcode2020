package org.bullet.day8;

import org.bullet.util.FileProcessing;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InstructionFileParser {
    private static Pattern INSTRUCTION_PATTERN = Pattern.compile("(acc|nop|jmp) ([+\\-][0-9]+)");

    public List<Instruction> parseInstructions(String fileName) {
        return FileProcessing
                .streamLinesFromFile(fileName)
                .map(this::parseLine)
                .collect(Collectors.toList());
    }

    private Instruction parseLine(String line) {
        Matcher m = INSTRUCTION_PATTERN.matcher(line);
        m.find();
        return new Instruction(Operation.parse(m.group(1)), Integer.parseInt(m.group(2)));
    }
}
