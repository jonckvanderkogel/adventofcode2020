package org.bullet.day8;

import io.vavr.Tuple2;
import org.bullet.util.TailCall;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.bullet.day8.Operation.*;
import static org.bullet.util.TailCalls.done;

public class GameConsole {
    private Integer accumulator = 0;
    private Integer instructionPosition = 0;

    public void mutateAccumulator(Integer value) {
        accumulator += value;
    }

    public void mutateInstructionPosition(Integer value) {
        instructionPosition += value;
    }

    public Tuple2<Integer,Boolean> correctProgram(List<Instruction> instructions) {
        List<Integer> jmpNopPositions = gatherJmpNopPostions(instructions);
        return testVariation(instructions, jmpNopPositions).invoke();
    }

    private TailCall<Tuple2<Integer,Boolean>> testVariation(List<Instruction> instructions, List<Integer> jmpNopPositions) {
        GameConsole simulation = new GameConsole();
        Integer swap = jmpNopPositions.get(0);
        swapInstruction(instructions, swap);
        Tuple2<Integer,Boolean> result = simulation.runProgram(instructions);
        if (!result._2()) {
            return done(result);
        } else {
            swapInstruction(instructions, swap);
            List<Integer> tail = jmpNopPositions.subList(1, jmpNopPositions.size());
            return () -> testVariation(instructions, tail);
        }
    }

    private void swapInstruction(List<Instruction> instructions, Integer swap) {
        Instruction instruction = instructions.get(swap);
        instructions.set(swap, instruction.getOperation().equals(JMP) ? new Instruction(NOP, instruction.getParameter()) : new Instruction(JMP, instruction.getParameter()));
    }

    private  List<Integer> gatherJmpNopPostions(List<Instruction> instructions) {
        return IntStream
                .range(0, instructions.size())
                .filter(i -> {
                    Operation operation = instructions.get(i).getOperation();
                    return operation.equals(JMP) || operation.equals(NOP);
                })
                .boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    public Tuple2<Integer,Boolean> runProgram(List<Instruction> instructions) {
        List<Integer> performedInstructions = new ArrayList<>();
        while (!performedInstructions.contains(instructionPosition) && instructionPosition < instructions.size()) {
            Instruction instruction = instructions.get(instructionPosition);
            performedInstructions.add(instructionPosition);
            instruction.getOperation().run(this, instruction.getParameter());
        }
        // boolean value sets whether there was a loop or not
        return new Tuple2<>(accumulator, performedInstructions.contains(instructionPosition));
    }
}
