package org.bullet.day8;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Instruction {
    private final Operation operation;
    private final Integer parameter;
}
