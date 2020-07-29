package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 모든 숫자를 다 나눈 다음에 병합하는 방식으로 정렬한다.
 */
class MergeSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        sort(values, 0, values.length - 1);

        return values;
    }

    void sort(final int[] values,
              final int left,
              final int right) {
        if (left >= right) {
            return;
        }

        // Note: (left + right) / 2
        final int middle = left + (right - left) / 2;

        sort(values, left, middle);
        sort(values, middle + 1, right);

        merge(values, left, middle, right);
    }

    private void merge(final int[] values,
                       final int left,
                       final int middle,
                       final int right) {
        final int[] temp = new int[values.length];

        int leftIndex = left;
        int rightIndex = middle + 1;
        int currentIndex = left;

        while (leftIndex <= middle && rightIndex <= right) {
            if (values[leftIndex] <= values[rightIndex]) {
                temp[currentIndex++] = values[leftIndex++];
            } else {
                temp[currentIndex++] = values[rightIndex++];
            }
        }

        while (leftIndex <= middle) {
            temp[currentIndex++] = values[leftIndex++];
        }
        while (rightIndex <= right) {
            temp[currentIndex++] = values[rightIndex++];
        }

        if (currentIndex >= 0) {
            System.arraycopy(temp, 0, values, 0, currentIndex);
        }
    }
}
