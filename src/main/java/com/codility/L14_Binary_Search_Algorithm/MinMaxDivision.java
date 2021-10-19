package com.codility.L14_Binary_Search_Algorithm;

/*
* You are given integers K, M and A non-empty array A consisting of N integers. Every element of the array is not greater than M.
You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
The large sum is the maximal sum of any block.


For example, you are given integers K = 3, M = 5 and array A such that:


        A[0] = 2
        A[1] = 1
        A[2] = 5
        A[3] = 1
        A[4] = 2
        A[5] = 2
        A[6] = 2


The array can be divided, for example, into the following blocks:

        [2, 1, 5, 1, 2, 2, 2], [], [] with A large sum of 15
        [2], [1, 5, 1, 2], [2, 2] with A large sum of 9
        [2, 1, 5], [], [1, 2, 2, 2] with A large sum of 8
        [2, 1], [5, 1], [2, 2, 2] with A large sum of 6

The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.

Write A function:

class Solution { public int solution(int K, int M, int[] A); }

that, given integers K, M and A non-empty array A consisting of N integers, returns the minimal large sum.

For example, given K = 3, M = 5 and array A such that:

  A[0] = 2
  A[1] = 1
  A[2] = 5
  A[3] = 1
  A[4] = 2
  A[5] = 2
  A[6] = 2
the function should return 6, as explained above.

Assume that:

N and K are integers within the range [1..100,000];
M is an integer within the range [0..10,000];
each element of array A is an integer within the range [0..M].
Complexity:

expected worst-case time complexity is O(N*log(N+M));
expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */


import java.util.stream.IntStream;

/**
 * Created by Chaklader on 6/28/18.
 */
public class MinMaxDivision {


    /*
     * You should divide this array into K blocks of consecutive elements.
     * The size of the block is any integer between 0 and N. Every element
     * of the array should belong to some block. The goal is to minimize the
     * large sum.
     * */

    /*
     * divide array A into K blocks and minimize the largest sum of any block
     * */

    /*
     * solution - a
     */

    /*
    * The goal is to minimize the large sum.
    */
    public static int solution(int K, int M, int[] A) {


        int sum0 = 0;
        int max0 = 0;

        int min = Integer.MAX_VALUE;

        /*
         * Get the sum and miximum value of the provided array
         * */
        for (int i = 0; i < A.length; i++) {

//            max0 = max0 >= A[i] ? max0 : A[i];
            sum0 += A[i];
            min = Math.min(A[i], min);
        }

        /*
         * Every element of the array is not greater than M
         * */
        // int min = Math.max((int) Math.ceil((double) sum0 / K), M);
        min = Math.min(A[i], min);
        int max = sum0;

        int sum = 0;

        /*
         * ALGORITHM
         * ---------
         *
         * i.  we will set the block value to the average and check if
         *     it provides a valid solution as the average value of a
         *     block
         *
         * ii. in case of valid solution, we will gradually decrease the
         *     maximum block value with the intention to minimize block
         *     section
         * */

        /********************************************************************/
        /*
            The array can be divided, for example, into the following blocks:

                [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
                [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
                [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
                [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
        */
        /********************************************************************/        
        while (min <= max) {

            int middle = (min + max) / 2;

            if (verify(A, middle, K)) {

                /*
                 *
                 * ALGORITHM
                 * ---------
                 * i.  we need to minimize the maximum sum so decrease
                 *     gradually by 1 from the average
                 *
                 * ii. update the block value to middle
                 * */
                max = middle - 1;
                sum = middle;
            } else {
                min = middle + 1;
            }
        }

        return sum;
    }


    /*
     * check if its possible to divide the array A
     * in K parts values of less than or equal to K
     * */
    public static boolean verify(int[] A, int middle, int K) {

        int N = A.length;

        int sum = 0;
        int block = 1;

        /*
         * check if we can divide the array in K parts with
         * sum of each part <= proposed value (ie middle)
         */
        for (int i = 0; i < N; i++) {

            if (sum + A[i] <= middle) {
                sum += A[i];
            } 

            else {

                block++;
                sum = A[i];

                /*
                 * we are not able to iterate over all the array
                 * elements but already atop of K blocks
                 * */
                if (block > K) {
                    return false;
                }
            }
        }

        return true;
    }


    /*
     * conduct A binary search iteratively to minimize the large sum
     * */
    public static int search(int[] A, int minimum, int maximum, int K) {

        /*
         * the value of minimized largest sum
         * */
        int s = 0;

        int min1 = minimum;
        int max1 = maximum;

        /*
         * ALGORITHM
         * ---------
         *
         * i.  we will set the block value to the average and check if
         *     it provides a valid solution as the average value of a
         *     block
         *
         * ii. in case of valid solution, we will gradually decrease the
         *     maximum block value with the intention to minimize block
         *     section
         * */
        while (min1 <= max1) {

            int middle = (min1 + max1) / 2;

            if (verify(A, middle, K)) {

                /*
                 *
                 * ALGORITHM
                 * ---------
                 *
                 * i.  we need to minimize the maximum sum so decrease
                 *     gradually by 1 from the average
                 *
                 * ii. update the block value to middle
                 * */
                max1 = middle - 1;
                s = middle;
            } else {
                min1 = middle + 1;
            }
        }

        return s;
    }


    /*
     * conduct A binary search recursively
     * */
    public static int searchR(int min, int max, int[] A, int K) {

        if (max - min < 2) {

            if (verify(A, min, K)) {
                return min;
            } else {
                return max;
            }
        }

        int middle = (min + max) / 2;

        if (verify(A, middle, K)) {
            return searchR(min, middle, A, K);
        } else {
            return searchR(middle, max, A, K);
        }
    }


    /*
     * solution - b
     */
    public int solution1(int K, int M, int[] A) {

        int min = 0;
        int max = 0;

        /*
         * get the sum as max, and the largest number as min
         * */
        for (int i = 0; i < A.length; i++) {
            max += A[i];
            min = Math.max(min, A[i]);
        }

        int result = max;

        while (min <= max) {

            int mid = (min + max) / 2;

            if (verify1(mid, K - 1, A)) {

                max = mid - 1;
                result = mid;
            } else {
                min = mid + 1;
            }
        }

        return result;
    }


    private boolean verify1(int mid, int k, int[] a) {

        int sum = 0;

        for (int i = 0; i < a.length; i++) {

            sum += a[i];

            if (sum > mid) {
                sum = a[i];
                k--;
            }

            if (k < 0) {
                return false;
            }
        }

        return true;
    }


    /*
     * solution - c
     */
    public int solution2(int K, int M, int[] A) {

        int max = 0;
        int sum = 0;

        for (int value : A) {

            if (value > max) {
                max = value;
            }

            sum += value;
        }

        /*
         * two trivial cases. if K is 1, then
         * all the elements inside one group
         * */
        if (K == 1) {
            return sum;
        }


        /*
         * If K is more than number of elements, some of them may be empty,
         * but, all the elements may be split to the groups of 1 element.
         * large sum will be equal to max element value.
         * */
        if (K >= A.length) {
            return max;
        }


        /*
         * The result is somewhere between max(n) and sum(n). use binary search to
         * find the correct one. max value of sum is n * M, max(n) can be 0 if all
         * the elements are 0. So at most we perform binary search on the interval
         * from 0 to n * M which is O(log(n * M))
         * */
        int begin = max;
        int end = sum;

        int result = sum;


        /*
         * time complexity is O(n * log(M * n)) in total
         * */
        while (begin <= end) {

            int medium = (begin + end) / 2;

            if (isDivisible(A, medium, K)) {
                result = medium;
                end = medium - 1;
            } else {
                begin = medium + 1;
            }
        }

        return result;
    }


    /*
     * time complexity is O(n) in total
     * */
    private boolean isDivisible(int[] a, int size, int k) {

        int sum = 0;

        /*
         * first is started already
         * */
        int stepsLeft = k - 1;

        for (int value : a) {

            sum += value;

            if (sum > size) {
                sum = value;
                stepsLeft--;
            }
        }

        return stepsLeft >= 0;
    }


    /*
     * solution - d
     */
    public int solution3(int K, int M, int[] A) {

        int min = IntStream.of(A).max().getAsInt();
        int max = IntStream.of(A).sum();

        int result = max;

        while (min <= max) {

            int mid = (min + max) / 2;

            if (check(A, mid, K - 1)) {
                max = mid - 1;
                result = mid;
            } else {
                min = mid + 1;
            }
        }

        return result;
    }

    private boolean check(int[] A, int mid, int K) {

        int sum = 0;

        for (int i = 0; i < A.length; i++) {

            sum += A[i];

            if (sum > mid) {
                sum = A[i];
                K--;
            }

            if (K < 0) {
                return false;
            }
        }

        return true;
    }
}
