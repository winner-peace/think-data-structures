package com.winnerpeace.datastructures.ohtaeg.sort.quick;

import java.util.Stack;

public class Quick {
    public static void main(String[] args) {
        int[] arr = {29, 14, 17, 13, 10, 1};
        Quick.sort(arr, 0, arr.length - 1);
        print(arr);

        System.out.println("=== non recursive using stack ===");

        int[] arr2 = {29, 14, 17, 13, 10, 1};
        Quick.sortUsingStack(arr2, 0, arr2.length - 1);
        print(arr2);
    }

    private static void sort(final int[] arr, final int begin, final int end) {
        if (begin < end) {
            int pivot = partition(arr, begin, end);
            sort(arr, begin, pivot - 1);
            sort(arr, pivot, end);
        }
    }

    // non - recursive
    private static void sortUsingStack(final int[] arr, final int begin, final int end) {
        Stack<Integer> stack = new Stack<>();
        int low = begin;
        int high = end;
        int pivot;

        stack.push(low);
        stack.push(high);

        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();

            if (low < high) {
                pivot = partition(arr, low, high);
                // 전반부
                // if (pivot > low) {
                stack.push(low);
                stack.push(pivot - 1);

                // 후반부
                // if (pivot <= high) {
                stack.push(pivot);
                stack.push(high);
            }
        }
    }

    private static int partition(final int[] arr, final int begin, final int end) {
        int pivotValue = arr[(begin + end) / 2]; // median
        int left = begin;
        int right = end;

        // 두 인덱스가 지나칠때까지 반복
        while (left <= right) {

            // 전반부쪽에서 피벗보다 작으면 무시하고 인덱스를 오른쪽으로 ->
            while (arr[left] < pivotValue) {
                left++;
            }

            // 후반부쪽에서 피벗보다 크면 무시하고 인덱스를 왼쪽으로 <-
            while (pivotValue < arr[right]) {
                right--;
            }

            // 두 인덱스가 지나치지 않았으면 값을 바꾼 후 인덱스 이동
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return left; // 후반부 첫번째 index return
    }

    private static void swap(final int[] arr, final int left, final int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void print(final int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
