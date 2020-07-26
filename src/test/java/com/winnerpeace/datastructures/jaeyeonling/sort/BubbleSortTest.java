package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 인접한 두 값을 비교하여 정렬한다.
 */
class BubbleSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    swap(values, j, j + 1);
                }
            }
        }

        return values;
    }
}