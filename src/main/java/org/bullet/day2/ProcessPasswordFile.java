package org.bullet.day2;

import static org.bullet.util.FileProcessing.streamLinesFromFile;

public class ProcessPasswordFile {

    public static void main(String... args) {
        ProcessPasswordFile processPasswordFile = new ProcessPasswordFile();

        System.out.println(processPasswordFile.countValidPasswords(new PasswordLineProcessorPart1()));

        System.out.println(processPasswordFile.countValidPasswords(new PasswordLineProcessorPart2()));
    }

    public long countValidPasswords(PasswordLineProcessor p) {
        return streamLinesFromFile("inputDay2.txt")
                .filter(p::isValid)
                .count();
    }
}
