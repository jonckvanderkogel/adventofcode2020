package org.bullet.day8;

import java.util.ArrayList;
import java.util.List;

public class GameConsole {
    private Integer accumulator = 0;
    private Integer instructionPosition = 0;

    public void mutateAccumulator(Integer value) {
        accumulator += value;
    }

    public void mutateInstructionPosition(Integer value) {
        instructionPosition += value;
    }

    public Integer runProgram(List<Instruction> instructions) {
        List<Integer> performedInstructions = new ArrayList<>();
        while (!performedInstructions.contains(instructionPosition) || instructionPosition >= instructions.size()) {
            Instruction instruction = instructions.get(instructionPosition);
            performedInstructions.add(instructionPosition);
            instruction.getOperation().run(this, instruction.getParameter());
        }

        return accumulator;
    }
}
