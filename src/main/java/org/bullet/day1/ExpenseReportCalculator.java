package org.bullet.day1;

import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.bullet.util.Range;
import org.bullet.util.TailCall;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.bullet.util.TailCalls.done;

public class ExpenseReportCalculator {
    public static Tuple2<Integer, Integer> findPairFromList(List<Integer> numbers, Integer targetValue) {
        List<Integer> numbersSorted = List
                .copyOf(numbers)
                .stream()
                .sorted()
                .collect(Collectors.toList());

        return findPair(numbersSorted.get(0), targetValue, new Range(1, numbersSorted.size() - 1), numbersSorted).invoke();
    }

    public static Optional<Tuple3<Integer, Integer, Integer>> findTripleFromList(List<Integer> numbers, Integer targetValue) {
        return numbers
                .stream()
                .flatMap(n1 -> numbers
                        .stream()
                        .flatMap(n2 -> numbers
                                .stream()
                                .filter(n3 -> n1 + n2 + n3 == targetValue)
                                .map(n3 -> new Tuple3<>(n1, n2, n3))
                        )
                )
                .findFirst();
    }

    private static TailCall<Tuple2<Integer, Integer>> findPair(final Integer baseNumber, final Integer targetValue, final Range range, final List<Integer> numbers) {
        if (range.getLowerBound() == range.getUpperBound() && baseNumber + numbers.get(range.getLowerBound()) == targetValue) {
            return done(new Tuple2<>(baseNumber, numbers.get(range.getLowerBound())));
        } else {
            if (range.getLowerBound() == range.getUpperBound()) {
                List<Integer> newList = numbers.subList(1, numbers.size());
                Integer newBaseNumber = newList.get(0);
                return () -> findPair(newBaseNumber, targetValue, new Range(1, newList.size()), newList);
            }
            int midIndex = range.getUpperBound() - (int) Math.ceil((double)(range.getUpperBound() - range.getLowerBound()) / 2);
            int projected = baseNumber + numbers.get(midIndex);
            if (projected >= targetValue) {
                return () -> findPair(baseNumber, targetValue, keepLowerHalf(range, numbers), numbers);
            } else {
                return () -> findPair(baseNumber, targetValue, keepUpperHalf(range, numbers), numbers);
            }
        }
    }

    public static Range keepLowerHalf(final Range range, List<Integer> numbers) {
        int newUpperBoundIndex = range.getUpperBound() - (int) Math.ceil((double)(range.getUpperBound() - range.getLowerBound()) / 2);

        return new Range(range.getLowerBound(), newUpperBoundIndex);
    }

    public static Range keepUpperHalf(final Range range, List<Integer> numbers) {
        int newLowerBoundIndex = range.getLowerBound() + (int) Math.ceil((double)(range.getUpperBound() - range.getLowerBound()) / 2);

        return new Range(newLowerBoundIndex, range.getUpperBound());
    }
}
