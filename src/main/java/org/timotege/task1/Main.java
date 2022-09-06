package org.timotege.task1;

public class Main {
    public static void main(String[] args) {
        final int N = 5;
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = rand();
            }
        }

        System.out.println("Generated array:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] > maxValue) {
                    maxValue = a[i][j];
                }
                if (a[i][j] < minValue) {
                    minValue = a[i][j];
                }
                sum += a[i][j];
            }
        }

        System.out.println();
        System.out.println("Max value: " + maxValue);
        System.out.println("Min value: " + minValue);
        System.out.println("Average value: " + (double) sum / (N * N));
    }

    public static int rand() {
        int a = 42;
        int b = 132;
        long time = System.nanoTime();
        return (int) (10 + time % (b - a));
    }
}