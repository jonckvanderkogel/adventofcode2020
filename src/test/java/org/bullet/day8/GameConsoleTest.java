package org.bullet.day8;

import io.vavr.Tuple2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameConsoleTest {
    @Test
    public void shouldRunInstructions() {
        List<Instruction> instructions = new InstructionFileParser().parseInstructions("testFileDay8Part1.txt");
        GameConsole gameConsole = new GameConsole();
        Tuple2<Integer,Boolean> result = gameConsole.runProgram(instructions);
        Assertions.assertEquals(5, result._1());
        Assertions.assertTrue(result._2());
    }

    @Test
    public void shouldBeAbleToCorrectProgram() {
        List<Instruction> instructions = new InstructionFileParser().parseInstructions("testFileDay8Part1.txt");
        GameConsole gameConsole = new GameConsole();
        Tuple2<Integer,Boolean> result = gameConsole.correctProgram(instructions);
        Assertions.assertEquals(8, result._1());
        Assertions.assertFalse(result._2());
    }
}
