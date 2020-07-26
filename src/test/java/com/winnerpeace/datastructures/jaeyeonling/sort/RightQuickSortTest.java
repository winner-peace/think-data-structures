package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 분할정복법 (Divide and Conquer)의 방법으로 전체 데이터를 기준 값을 중심으로 왼쪽 부분과 오른쪽 부분을 분할한 다음 정렬한다.
 * RightQuickSort는 맨 오른쪽을 기준으로 한다.
 * <p>
 * Pivot을 기준으로 여러가지 QuickSort가 존재한다.
 * LeftQuickSort, RightQuickSort, MiddleQuickSort, RandomQuickSort, DoubleQuickSort, ...
 */
class RightQuickSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        sort(values, 0, values.length - 1);

        return values;
    }

    private void sort(final int[] values,
                      final int left,
                      final int right) {
        if (left >= right) {
            return;
        }

        final int pivot = partition(values, left, right);

        sort(values, left, pivot - 1);
        sort(values, pivot + 1, right);
    }

    private int partition(final int[] values,
                          final int left,
                          final int right) {
        final int pivot = values[right];

        int newPivot = left - 1;
        for (int i = left; i < right; i++) {
            if (values[i] <= pivot) {
                swap(values, ++newPivot, i);
            }
        }

        swap(values, ++newPivot, right);

        return newPivot;
    }
}
