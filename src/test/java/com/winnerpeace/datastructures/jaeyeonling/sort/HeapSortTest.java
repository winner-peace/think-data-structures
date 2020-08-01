package com.winnerpeace.datastructures.jaeyeonling.sort;

/**
 * 힙 자료구조의 힙 생성 알고리즘을 통해 힙의 형태를 유지하여 정렬한다.
 */
class HeapSortTest extends SortTest {

    @Override
    int[] sort(final int[] values) {
        for (int i = 1; i < values.length; i++) {
            int child = i;
            while (child != 0) {
                final int root = (child - 1) / 2;
                if (values[root] < values[child]) {
                    swap(values, root, child);
                }

                child = root;
            }

        }

        for (int i = values.length - 1; i >= 0; i--) {
            swap(values, 0, i);

            int root = 0;
            int child = 1;
            while (child < i) {
                child = 2 * root + 1;

                if (child < i - 1 && values[child] < values[child + 1]) {
                    child++;
                }
                if (child < i && values[root] < values[child]) {
                    swap(values, root, child);
                }

                root = child;
            }
        }

        return values;
    }
}
