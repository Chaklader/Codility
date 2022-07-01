package com.codility.L5_Prefix_Sums;

/*
* A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called
* A slice of array A (notice that the slice contains at least two elements). The average of A slice (P, Q) is the sum
* of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1]
* + ... + A[Q]) / (Q − P + 1).

For example, array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8

contains the following example slices:

slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of A slice whose average is minimal.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A consisting of N integers, returns the starting position of the slice with the minimal
average. If there is more than one slice with A minimal average, you should return the smallest starting position of
such A slice.

For example, given array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
the function should return 1, as explained above.

Assume that:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


/**
 * Created by Chaklader on 6/23/18.
 */
public class MinAvgTwoSlice {

    /*
     * The goal is to find the starting
     * position of A slice whose average
     * is minimal.
     * */

    /*
     * solution - a
     * */
    public static int solution(int[] A) {


        /*
		ALGORITHM
		---------

		PREMISE: The slice provides the min average will formed of 2 to 3 elements

			0.   define the startIndex = 0 and endIndex = 1
			i.   get the avg for the initial 2 elments (with these indexs)
			ii.  get the avg for the initial 3 elments by moving the end index one step
			iii. update the result comparing them
			iv.  move forward the endIndex 1 step and repeat the process key
        */

        /*
         * initially, define A demo slice and minimal
         * average for slice. Then, keep it updating
         * by moving forward.
         * */

        int tail = 0;
        int head = 1;

        int result = 0;
        int sum = A[0] + A[1];

        double min = (double) sum / 2;
        double temp = min;


        while (true) {

            /*
             * 2 elements in the slice
             * */
            if (head - tail == 1) {

                head++;

                if (head == A.length) {
                    return result;
                }

                sum += A[head];
            }

            /*
             * we have A 3 element slice
             * */
            else {
                sum -= A[tail];
                tail++;
            }

            temp = (double) sum / (head - tail + 1);

            if (temp < min) {

                result = tail;
                min = temp;
            }

            // if (head == A.length) {
            //     return result;
            // }
        }

    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A) {


        int k = 0;
        double min = (double) (A[0] + A[1]) / 2;

        int N = A.length;


        for (int j = 0; j < N - 2; j++) {

            if ((double) (A[j] + A[j + 1]) / 2 < min) {
                
                min = (double) (A[j] + A[j + 1]) / 2;
                k = j;
            }

            if ((double) (A[j] + A[j + 1] + A[j + 2]) / 3 < min) {

                min = (double) (A[j] + A[j + 1] + A[j + 2]) / 3;
                k = j;
            }        
        }


        if ((double) (A[N - 1] + A[N - 2]) / 2 < min) {
            return  (N - 2);
        }

        return k;
    }


    /*
     * solution - c
     * */
    public static int solution2(int[] A) {


        int[] C = new int[A.length];

        C[0] = A[0];


        for (int i = 1; i < A.length; i++) {
            C[i] = C[i - 1] + A[i];
        }

        int minIndex = 0;
        double minAvg = Double.MAX_VALUE;

        /*
         * increase slice size start with 1
         * */
        for (int i = 1; i < A.length; i++) {

            double currentMin = Double.MAX_VALUE;
            int currentMinIndex = 0;

            for (int j = i; j < A.length; j++) {

                double currentAvg = getAverage(C, j - i, j);

                if (currentAvg < currentMin) {

                    currentMin = currentAvg;
                    currentMinIndex = j - i;
                }
            }

            if (minAvg <= currentMin) {
                break;
            }

            if (minAvg > currentMin) {

                minAvg = currentMin;
                minIndex = currentMinIndex;
            }
        }

        return minIndex;
    }


    public static double getAverage(int[] C, int P, int Q) {

        int sub = 0;

        if (P - 1 >= 0) {
            sub = C[P - 1];
        }

        return (double) (C[Q] - sub) / (double) (Q - P + 1);
    }


    /*
     * its native solution with time complexity of O(N^2)
     * */
    /*
     * solution - d
     * */
    public static int solution3(int[] A) {


        double minAvg = Double.MAX_VALUE;
        int minIndex = 0;

        int N = A.length;


        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                int sum = 0;
                int counter = 0;

                for (int k = i; k <= j; k++) {

                    sum += A[k];
                    counter++;
                }

                double avg = (double) sum / (double) counter;

                if (avg < minAvg) {

                    minAvg = avg;
                    minIndex = i;
                }
            }
        }

        return minIndex;
    }


}
