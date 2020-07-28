package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 분할정복법 (Divide and Conquer)의 방법으로 전체 데이터를 기준 값을 중심으로 왼쪽 부분과 오른쪽 부분을 분할한 다음 정렬한다.
 * RightQuickSort는 중앙을 기준으로 한다.
 * <p>
 * Pivot을 기준으로 여러가지 QuickSort가 존재한다.
 * LeftQuickSort, RightQuickSort, MiddleQuickSort, RandomQuickSort, DoubleQuickSort, ...
 */
class MiddleQuickSortTest extends SortTest {

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
        // Note: (left + right) / 2
        final int middle = left + (right - left) / 2;
        final int pivot = values[middle];

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
