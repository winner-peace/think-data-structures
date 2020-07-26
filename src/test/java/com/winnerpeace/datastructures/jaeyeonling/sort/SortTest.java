package com.winnerpeace.datastructures.jaeyeonling.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
abstract class SortTest {

    abstract int[] sort(final int[] values);

    @RepeatedTest(value = 100)
    void test() {
        // given
        final int[] values = Stream.generate(() -> ThreadLocalRandom.current().nextInt(10000))
                .limit(ThreadLocalRandom.current().nextInt(10, 100))
                .mapToInt(value -> value)
                .toArray();

        // when
        final int[] sortedValues = sort(values);

        // then
        assertThat(sortedValues).hasSameSizeAs(values);
        assertThat(sortedValues).containsAnyOf(values);
        assertThat(sortedValues).isSorted();
    }

    void swap(final int[] values,
              final int aIndex,
              final int bIndex) {
        final int temp = values[aIndex];
        values[aIndex] = values[bIndex];
        values[bIndex] = temp;
    }
}
