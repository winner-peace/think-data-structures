package com.winnerpeace.datastructures.ohtaeg.sort.merge;

import java.util.Stack;

public class Merge {
    public static void main(String[] args) {
        int[] arr = new int[]{29, 14, 10, 37, 13, 10, 1}; // 10 13 14 29 37
        Merge.sort(arr, 0, arr.length - 1);
        print(arr);

        System.out.println("== non recursive using stack ==");

        int[] arr2 = new int[]{29, 14, 10, 37, 13, 10, 1};
        arr2 = Merge.sortUsingStack(arr2);
        print(arr2);
    }

    // recursive
    private static void sort(final int[] arr, final int begin, final int end) {
        if (begin < end) {
            int half = (begin + end) / 2;
            sort(arr, begin, half); // 전반부
            sort(arr, half + 1, end); // 후반부
            merge(arr, begin, half, end); // merge
        }
    }

    // merge for recursive
    private static void merge(final int[] arr, final int begin, final int half, final int end) {
        int[] temp = new int[arr.length];
        int index = begin;
        int left = begin;
        int right = half + 1;

        while (left <= half && right <= end) {
            if (arr[left] < arr[right]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[right++];
            }
        }

        while (left <= half) {
            temp[index++] = arr[left++];
        }

        while (right <= end) {
            temp[index++] = arr[right++];
        }

        for (int i=begin; i<index; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * non - recursive using stack
     * 1. 왼쪽 스택에 크기가 1개남을때까지 분할하여 머지한다.
     * 왼쪽에 한개만 남는다는건 결과가 배열이 완성 되었거나, 배열내 원소가 하나밖에 없었다는 것.
     */
    private static int[] sortUsingStack(final int[] arr) {
        Stack<int[]> stack = new Stack<>();
        for (int element : arr) {
            stack.push(new int[]{ element });
        }
        while (stack.size() >= 2) {
            stack.push(merge(stack.pop(), stack.pop()));
        }
        return stack.pop();
    }

    // 기존 merge 방법과 똑같다. 파라미터에 따른 다른 모습일 뿐.
    public static int[] merge(int[] leftPartition, int[] rightPartition) {
        int[] temp = new int[leftPartition.length + rightPartition.length];
        int left = 0;
        int right = 0;
        int index = 0;

        while (left < leftPartition.length && right < rightPartition.length) {
            if (leftPartition[left] < rightPartition[right]) {
                temp[index++] = leftPartition[left++];
            } else {
                temp[index++] = rightPartition[right++];
            }

        }

        while (left < leftPartition.length) {
            temp[index++] = leftPartition[left++];
        }

        while (right < rightPartition.length) {
            temp[index++] = rightPartition[right++];
        }

        return temp;
    }

    private static void print(final int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
