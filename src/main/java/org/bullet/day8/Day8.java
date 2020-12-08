package org.bullet.day8;

import java.util.List;

public class Day8 {
    public static void main(String... args) {
        List<Instruction> instructions = new InstructionFileParser().parseInstructions("inputDay8.txt");
        GameConsole gameConsole = new GameConsole();
        System.out.println(gameConsole.runProgram(instructions));
    }
}
