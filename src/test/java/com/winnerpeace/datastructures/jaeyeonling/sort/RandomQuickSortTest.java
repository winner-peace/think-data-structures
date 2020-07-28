package com.winnerpeace.datastructures.jaeyeonling.sort;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 분할정복법 (Divide and Conquer)의 방법으로 전체 데이터를 기준 값을 중심으로 왼쪽 부분과 오른쪽 부분을 분할한 다음 정렬한다.
 * RandomQuickSort는 랜덤한 위치를 기준으로 한다.
 * <p>
 * Pivot을 기준으로 여러가지 QuickSort가 존재한다.
 * LeftQuickSort, RightQuickSort, MiddleQuickSort, RandomQuickSort, DoubleQuickSort, ...
 */
class RandomQuickSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        sort(values, 0, values.length - 1);

        return values;
    }

    private void sort(final int[] values,
                      final int left,
                      final int right) {
        final int pivot = partition(values, left, right);

        if (left < pivot - 1) {
            sort(values, left, pivot - 1);
        }
        if (pivot < right) {
            sort(values, pivot, right);
        }
    }

    private int partition(final int[] values,
                          final int left,
                          final int right) {
        final int pivot = values[ThreadLocalRandom.current().nextInt(left, right)];

        int start = left;
        int end = right;
        while (start <= end) {
            while (values[start] < pivot) {
                ++start;
            }
            while (values[end] > pivot) {
                --end;
            }

            if (start <= end) {
                swap(values, start++, end--);
            }
        }

        return start;
    }
}
