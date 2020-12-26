package com.codility.L15_Caterpiller_Method;

/*
* A non-empty array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.

For example, consider array A such that:

  A[0] = -5
  A[1] = -3
  A[2] = -1
  A[3] =  0
  A[4] =  3
  A[5] =  6
The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A consisting of N numbers, returns absolute distinct count of array A.

For example, given array A such that:

  A[0] = -5
  A[1] = -3
  A[2] = -1
  A[3] =  0
  A[4] =  3
  A[5] =  6
the function should return 5, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
array A is sorted in non-decreasing order.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by Chaklader on 6/25/18.
 */
public class AbsDistinct {


    /*
     * solution - a
     * */
    public int solution(int[] A){

        Set<Integer> set = new HashSet<>();

        int N = A.length;


        for (int i = 0; i < N; i++) {

            int abs = Math.abs(A[i]);
            set.add(abs);
        }

        return set.size();
    }


    /*
     * solution - a
     * */
    public static int solution(int[] A) {

        return (int) IntStream.of(A).map(v -> Math.abs(v)).distinct().count();
    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int dups = 0;
        int N = A.length;

        for (int i = 0; i < N; i++) {

            if (A[i] < 0) {
                A[i] = -A[i];
            }
        }

        // The array is sorted in non-decreasing order. 
        Arrays.sort(A);

        for (int i = 1; i < N; i++) {

            if (A[i] == A[i - 1]) {
                dups++;
            }
        }

        int count = N - dups;

        return count;
    }


    /*
     * solution - c
     * */
    public int solution2(int[] A) {

        int N = A.length;

        int low = 0;
        int high = N - 1;

        int result = 1;

        /*
         * the current maximal value
         * */
        int currMaxValue = Math.max(Math.abs(A[low]), Math.abs(A[high]));

        if (A[low] == Integer.MIN_VALUE) {
            currMaxValue = Math.abs(A[low]);
        }

        while (low <= high) {

            int currHead = Math.abs(A[low]);

            /*
             * Same value of the current maximal value should not be counted
             * */
            if (currHead == currMaxValue) {
                low++;
                continue;
            }

            int currTail = Math.abs(A[high]);

            /*
             * Same value of the current maximal value should not be counted
             * */
            if (currTail == currMaxValue) {
                high--;
                continue;
            }

            /*
             * get the new current maximal value
             * */
            if (currHead >= currTail) {
                currMaxValue = currHead;
                low++;
            } 

            else {
                currMaxValue = currTail;
                high--;
            }

            result++;
        }
        return result;
    }


    /*
     * solution - d
     * */
    public int solution3(int[] A) {

        int[] negatives = new int[A.length];

        int i = 0;
        int j = 0;

        int prev = 1;
        int result = 0;

        for (; i < A.length && A[i] < 0; i++) {

            if (prev != A[i]) {

                negatives[j] = -A[i];
                prev = A[i];

                j++;
                result++;
            }
        }

        prev = -1;

        for (--j; i < A.length; i++) {

            while (j >= 0 && negatives[j] < A[i]) {
                j--;
            }

            if (j < 0 || negatives[j] != A[i]) {

                if (prev != A[i]) {
                    result++;
                    prev = A[i];
                }
            }
        }

        return result;
    }


    /*
     * solution - e
     * */
    public int solution4(int[] A) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {

            int key = Math.abs(A[i]);

            if (map.containsKey(key)) {
                map.put(Math.abs(key), map.get(key) + 1);
            } else {
                map.put(key, 0);
            }
        }

        return map.size();
    }


    /*
     * solution - f
     * */
    public static int solution5(int[] A) {

        int N = A.length;

        /*
         * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647]
         * hence, this solution doesn't help
         * */
        int M = Math.max(Math.abs(A[0]), Math.abs(A[N - 1]));

        int[] counter = new int[M + 1];


        for (int i = 0; i < N; i++) {
            counter[Math.abs(A[i])]++;
        }

        int count = 0;

        for (int i = 0; i < (M + 1); i++) {

            if (counter[i] > 0) {
                count++;
            }
        }

        return count;
    }
}
