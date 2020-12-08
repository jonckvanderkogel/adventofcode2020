package org.bullet.day8;

import java.util.List;

public class Day8 {
    public static void main(String... args) {
        List<Instruction> instructions = new InstructionFileParser().parseInstructions("inputDay8.txt");
        GameConsole gameConsole = new GameConsole();
        System.out.println(String.format("Part 1: %s", gameConsole.runProgram(instructions)));

        System.out.println(String.format("Part 2: %s", gameConsole.correctProgram(instructions)));
    }
}
