package org.timotege.task2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{5, 6, 3, 2, 5, 1, 4, 9};
        mergeSort(a, a.length);

        int[] sortedArray = new int[]{1, 2, 3, 4, 5, 5, 6, 9};
        assert (Arrays.equals(a, sortedArray));

        int[] arrayWihDuplicates = new int[] {3, 3, 3, 1, 2, 1, 2, 1, 2};
        int[] res = new int[] {1, 1, 1, 2, 2, 2, 3, 3, 3};
        mergeSort(arrayWihDuplicates, arrayWihDuplicates.length);

        assert (Arrays.equals(res, arrayWihDuplicates));
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = a[i];
        }

        for (int i = mid; i < n; i++) {
            right[i - mid] = a[i];
        }
        mergeSort(left, mid);
        mergeSort(right, n - mid);

        merge(a, left, right, mid, n - mid);
    }

    public static void merge(int[] a, int[] left, int[] right, int l, int r) {
        int i = 0, j = 0, k = 0;
        while (i < l && j < r) {
            if (left[i] <= right[j])
                a[k++] = left[i++];
            else
                a[k++] = right[j++];
        }
        while (i < l) {
            a[k++] = left[i++];
        }
        while (j < r) {
            a[k++] = right[j++];
        }
    }
}