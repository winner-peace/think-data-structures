package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 주어진 데이터의 최소값을 찾아 위치를 변경하며 정렬한다.
 */
class SelectionSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        for (int i = 0; i < values.length; i++) {
            int minIndex = i;

            for (int j = i; j < values.length; j++) {
                if (values[minIndex] > values[j]) {
                    minIndex = j;
                }
            }

            if (minIndex == i) {
                continue;
            }

            swap(values, i, minIndex);
        }

        return values;
    }
}
