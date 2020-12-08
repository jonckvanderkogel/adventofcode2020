package org.bullet.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameConsoleTest {
    @Test
    public void shouldRunInstructions() {
        List<Instruction> instructions = new InstructionFileParser().parseInstructions("testFileDay8Part1.txt");
        GameConsole gameConsole = new GameConsole();
        Assertions.assertEquals(5, gameConsole.runProgram(instructions));
    }
}
