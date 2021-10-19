package com.codility.L10_Prime_Composite_Num;

/*
* A non-empty array A consisting of N integers is given.

A peak is an array element which is larger than its neighbors. More precisely, it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].

For example, the following array A:

        A[0] = 1
        A[1] = 2
        A[2] = 3
        A[3] = 4
        A[4] = 3
        A[5] = 4
        A[6] = 1
        A[7] = 2
        A[8] = 3
        A[9] = 4
        A[10] = 6
        A[11] = 2

has exactly three peaks: 3, 5, 10.

We want to divide this array into blocks containing the same number of elements. More precisely, we want to choose A number K that will yield the following blocks:

A[0], A[1], ..., A[K − 1],
A[K], A[K + 1], ..., A[2K − 1],
...
A[N − K], A[N − K + 1], ..., A[N − 1].
What's more, every block should contain at least one peak. Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, but only if they have both neighbors (including one in an adjacent blocks).

The goal is to find the maximum number of blocks into which the array A can be divided.

Array A can be divided into blocks as follows:

one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has A peak.
three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has A peak. Notice in particular that the first block (1, 2, 3, 4) has A peak at A[3], because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), because the (1, 2, 3) blocks do not contain A peak. Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].

The maximum number of blocks that array A can be divided into is three.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A consisting of N integers, returns the maximum number of blocks into which A can be divided.

If A cannot be divided into some number of blocks, the function should return 0.

For example, given:

    A[0] = 1
    A[1] = 2
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2
the function should return 3, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [0..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N*log(log(N)));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.*;

/**
 * Created by Chaklader on 6/25/18.
 */
public class Peaks {

    /*
     * check https://en.wikipedia.org/wiki/Divisor_function
     * and Robin's inequality The sum of divisors is O(N *
     * log(log(N)))
     * */

    /*
     * The goal is to find the maximum number of blocks into which the
     * array A can be divided.
     * */

    /*
     * solution - a
     */
    public static int solution(int[] A) {

        int N = A.length;

        List<Integer> peaks = new ArrayList<Integer>();

        for (int i = 1; i < (A.length - 1); i++) {

            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }

        // int[] A = new int[12];

        /*
         * i is the number of elements in the block
         * */
        for (int i = 1; i <= N; i++) {

            int blocks = N / i;

            if (N % i != 0 || blocks > peaks.size()) {
                continue;
            }

            int count = 0;

            for (int p : peaks) {

                /*
                 * no peak till the i-th index, we have peaks at [3, 5, 10] and N = 12
                 * */
                //  p/i <= count mean we have a peak in the range 
                if (p / i > count) {
                    break;
                }

                if (p / i == count) {
                    count++;
                }
            }

            /*
             * as we gradually decreasing the peak numbers, we can return the result
             * whenever we meet the condition and the solution will provide the maximum
             * number of blocks
             * */
            if (count == blocks) {
                return blocks;
            }
        }

        return 0;
    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int N = A.length;

        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < (N - 1); i++) {

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }

        int N = peaks.size();

        for (int i = N; i >= 1; i--) {

            if (N % i != 0) {
                continue;
            }

            int size = N / i;
            int count = 0;

            for (int p : peaks) {

                if (p / size > count) {
                    break;
                }

                if (p / size == count) {
                    count++;
                }
            }

            if (i == count) {
                return count;
            }
        }

        return 0;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int N = A.length;

        if (N < 3) {
            return 0;
        }

        int[] P = new int[N];
        P[0] = 0;

        for (int i = 1; i < N - 1; i++) {

            P[i] = P[i - 1];

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                P[i]++;
            }
        }

        P[N - 1] = P[N - 2];

        if (P[N - 1] == 0) {
            return 0;
        }

        for (int i = P[N - 1]; i > 1; i--) {

            if (N % i == 0) {

                int s = N / i;
                boolean found = true;

                int c = 0;

                /*
                 * the loop is performed only when i is A divisor of A.length each
                 * time it performed divisor value times so in total it's performed
                 * sum(divisors) times which is O(N * log(log(N))) outer loop is
                 * performed O(N) times, so we ignore the cases when i is not A
                 * divisor since it is O(N) and O(N) < O(N * log(log(N)))
                 * */
                for (int j = s - 1; j < N; j += s) {

                    if (P[j] - c == 0) {
                        found = false;
                        break;
                    }

                    c = P[j];
                }

                if (found) {
                    return i;
                }
            }
        }

        return 1;
    }


    /*
     * solution - d
     */
    public int solution3(int[] A) {


        ArrayList<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < A.length - 1; i++) {

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }

        if (peaks.size() == 0) {
            return 0;
        }

        for (int i = 1; i <= A.length; i++) {

            if (A.length % i != 0) {
                continue;
            }

            if (checkSlices(i, peaks, A.length)) {
                return (A.length / i);
            }
        }
        return peaks.size();
    }

    public boolean checkSlices(int sliceLength, ArrayList<Integer> peaks, int total) {

        int count = 0;

        for (int i = 0; i < peaks.size(); i++) {

            if (peaks.get(i) / sliceLength > count) {
                return false;
            }

            if (peaks.get(i) / sliceLength == count) {
                count++;
            }
        }

        if (count != total / sliceLength) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {

        int[] A = new int[12];

        A[0] = 1;
        A[1] = 2;
        A[2] = 3;
        A[3] = 4;
        A[4] = 3;
        A[5] = 4;
        A[6] = 1;
        A[7] = 2;
        A[8] = 3;
        A[9] = 4;
        A[10] = 6;
        A[11] = 2;

        System.out.println(solution(A));
    }
}
