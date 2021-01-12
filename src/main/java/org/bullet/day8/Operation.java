package org.bullet.day8;

import java.util.function.BiConsumer;

public enum Operation {
    ACC((g,p) -> {
        g.mutateAccumulator(p);
        g.mutateInstructionPosition(1);
    }),
    JMP(GameConsole::mutateInstructionPosition),
    NOP((g,p) -> g.mutateInstructionPosition(1)),
    INVALID((g,p) -> System.out.println(String.format("Cannot run invalid instruction: %s, %s", g, p)));

    private BiConsumer<GameConsole,Integer> execute;

    public void run(GameConsole g, Integer p) {
        execute.accept(g, p);
    }

    Operation(BiConsumer<GameConsole,Integer> execute) {
        this.execute = execute;
    }

    public static Operation parse(String operationString) {
        return switch(operationString.toLowerCase()) {
            case "acc" -> ACC;
            case "jmp" -> JMP;
            case "nop" -> NOP;
            default -> INVALID;
        };
    }
}
