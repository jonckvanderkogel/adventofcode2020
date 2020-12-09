package org.bullet.day9;

import io.vavr.Tuple2;
import org.bullet.util.TailCall;

import java.util.List;
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
        for (int i=0; i<preambleSize-1; i++) {
            for (int j=i+1; j<preambleSize; j++) {
                if (toCheck.equals(subList.get(i) + subList.get(j))) {
                    return true;
                }
            }
        }

        return false;
    }

    public Long addSmallestAndLargest(List<Long> numbers, Tuple2<Integer,Integer> range) {
        Long min = IntStream
                .rangeClosed(range._1(), range._2())
                .mapToLong(i -> numbers.get(i))
                .min()
                .getAsLong();

        Long max = IntStream
                .rangeClosed(range._1(), range._2())
                .mapToLong(i -> numbers.get(i))
                .max()
                .getAsLong();

        return min + max;
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
