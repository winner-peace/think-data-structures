package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 모든 데이터를 앞에서부터 차례로 비교하여 자신의 위치에 삽입을 통해 정렬한다.
 */
class InsertionSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        for (int i = 1; i < values.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (values[j] > values[j + 1]) {
                    swap(values, j, j + 1);
                }
            }
        }

        return values;
    }
}
