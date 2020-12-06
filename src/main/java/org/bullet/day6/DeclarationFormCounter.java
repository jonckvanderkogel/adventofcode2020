package org.bullet.day6;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeclarationFormCounter {

    public static long countAnswersWhereEveryPersonInTheGroupAnsweredYes(List<List<String>> input) {
        return input
                .stream()
                .map(DeclarationFormCounter::countCommonCharactersInGroup)
                .mapToLong(l -> l)
                .sum();
    }

    private static long countCommonCharactersInGroup(List<String> group) {
        List<String> letters = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");

        long count = letters
                .stream()
                .filter(c -> group
                        .stream()
                        .filter(s -> s.contains(c))
                        .count() == group.size()
                )
                .count();

        return count;
    }

    public static int countAnswersWhereAPersonInTheGroupAnsweredYes(List<List<String>> input) {
        return input
                .stream()
                .map(DeclarationFormCounter::combineGroup)
                .map(DeclarationFormCounter::countUniqueAnswersPerGroup)
                .mapToInt(i -> i)
                .sum();
    }

    private static String combineGroup(List<String> lines) {
        return lines
                .stream()
                .reduce("", String::concat);
    }

    private static int countUniqueAnswersPerGroup(String groupAnswers) {
        Set<String> answerSet = new HashSet<>();
        for (char c : groupAnswers.toCharArray()) {
            answerSet.add(String.valueOf(c));
        }

        return answerSet.size();
    }
}
