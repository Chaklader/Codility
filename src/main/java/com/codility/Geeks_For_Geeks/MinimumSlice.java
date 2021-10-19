package com.codility.Geeks_For_Geeks;

import java.util.Arrays;

/**
 * Created by Chaklader on 7/15/18.
 */
public class MinimumSlice {


    /*
     * solution - a
     * */
    public static int solution(int[] A) {

        int N = A.length;
        int[] P = new int[N + 1];

        int min = 20000;
        int dif = 0;

        P[0] = 0;

        for (int i = 1; i < P.length; i++) {
            P[i] = P[i - 1] + A[i - 1];
        }

        Arrays.sort(P);

        for (int i = 1; i < P.length; i++) {

            dif = P[i] - P[i - 1];

            if (dif < min) {
                min = dif;
            }
        }

        return min;
    }


    /*
     * solution - b
     * */
    public static int solution1(int A[]) {

        int N = A.length;

        if (N == 0 || N > 1000000) {
            return 0;
        }

        int minTillHere = A[0];
        int minSoFar = A[0];

        int i;

        for (i = 1; i < N; i++) {
            minTillHere = min(A[i], minTillHere + A[i]);
            minSoFar = min(minSoFar, minTillHere);
        }

        return minSoFar;
    }

    public static int min(int a, int b) {
        return (a >= b) ? b : a;
    }


    public static void main(String[] args) {

        int[] A = {2, -4, 6, -3, 9};

        // |(−4) + 6 + (−3)| = 1 <- minimal absolute sum
        System.out.println(solution(A));
    }
}
