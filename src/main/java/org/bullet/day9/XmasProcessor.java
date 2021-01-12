package org.bullet.day9;

import io.vavr.Tuple2;
import org.bullet.util.TailCall;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.bullet.util.TailCalls.done;

public class XmasProcessor {

    public Long firstInvalidNumber(List<Long> numbers, Integer preambleSize) {
        Tuple2<Integer, Boolean> result = IntStream
                .range(preambleSize, numbers.size())
                .boxed()
                .map(i -> new Tuple2<>(i, isPositionValid(numbers, i, preambleSize)))
                .dropWhile(Tuple2::_2)
                .findFirst()
                .get();

        return numbers.get(result._1());
    }

    private Boolean isPositionValid(List<Long> numbers, Integer index, Integer preambleSize) {
        List<Long> subList = numbers.subList(index - preambleSize, index);
        Long toCheck = numbers.get(index);

        return IntStream
                .range(0, preambleSize - 1)
                .boxed()
                .flatMap(i -> IntStream.range(i, preambleSize)
                        .boxed()
                        .map(j -> toCheck.equals(subList.get(i) + subList.get(j)))
                )
                .filter(v -> v)
                .findFirst()
                .orElseGet(() -> false);
    }

    public Optional<Long> addSmallestAndLargest(List<Long> numbers, Tuple2<Integer,Integer> range) {
        Optional<Long> min = IntStream
                .rangeClosed(range._1(), range._2())
                .mapToLong(i -> numbers.get(i))
                .min()
                .stream()
                .boxed()
                .findFirst();

        Optional<Long> max = IntStream
                .rangeClosed(range._1(), range._2())
                .mapToLong(i -> numbers.get(i))
                .max()
                .stream()
                .boxed()
                .findFirst();

        return min
                .flatMap(mi -> max
                        .map(ma -> mi + ma)
                );
    }

    public Tuple2<Integer,Integer> findRange(List<Long> numbers, Long targetValue) {
        return findRangeRecursively(numbers,new Tuple2<>(0,1), targetValue).invoke();
    }

    private TailCall<Tuple2<Integer,Integer>> findRangeRecursively(List<Long> numbers, Tuple2<Integer,Integer> currentRange, Long targetValue) {
        Long currentAddition = addRange(numbers, currentRange);
        if (currentAddition.equals(targetValue)) {
            return done(currentRange);
        } else {
            if (currentAddition < targetValue) {
                return () -> findRangeRecursively(numbers, new Tuple2<>(currentRange._1(), currentRange._2() + 1), targetValue);
            } else {
                return () -> findRangeRecursively(numbers, new Tuple2<>(currentRange._1() + 1, currentRange._2()), targetValue);
            }
        }
    }

    private Long addRange(List<Long> numbers, Tuple2<Integer,Integer> range) {
        return IntStream
                .rangeClosed(range._1(), range._2())
                .mapToLong(numbers::get)
                .sum();
    }
}
