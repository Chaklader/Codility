package com.codility.L2_Arrays;

/*
* An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).

The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

Write FrogJmp function:

class Solution { public int[] solution(int[] A, int K); }

that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.


For example, given

    A = [3, 8, 9, 7, 6]
    K = 3

the function should return [9, 7, 6, 3, 8]. Three rotations were made:

    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
For another example, given

    A = [0, 0, 0]
    K = 1
the function should return [0, 0, 0]

Given

    A = [1, 2, 3, 4]
    K = 4
the function should return [1, 2, 3, 4]

Assume that:

N and K are integers within the range [0..100];
each element of array A is an integer within the range [−1,000..1,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
* */


import java.util.HashMap;
import java.util.Map;


/**
 * Created by Chaklader on 6/23/18.
 */
public class CyclicRotation {


    /*
     * solution - a
     * */
    public int[] solution(int[] A, int K) {


        int N = A.length;

        if (A == null || K > N) {
            return null;
        }

        if(N == K){
            return A; 
        }

        // all of the elements of the array are equal
        if(Arrays.stream(A).distinct().count() == 1){
            return A;
        }

        /*
         * Algorithm
         * ---------
         * i.   reverse the whole array O(n) time complexity (TC)
         * ii.  reverse the array from 0 to (K-1)-th index
         * iii. reverse the array from K to end
         * */

        // A = [3, 8, 9, 7, 6]
        //     [6, 7, 9, 8, 3] 
        //     [9, 7, 6, 8, 3]
        //     [9, 7, 6, 3, 8]

        reverse(A, 0, N - 1);
        reverse(A, 0, K - 1);
        reverse(A, K, N - 1);

        return A;
    }

    /*
     * time complexity of O(n)
     * */
    public static void reverse(int[] A, int start, int end) {

        int i = start, j = end;

        while (i < j) {

            if (A[i] != A[j]) {

                // A[i] = swap(A[j], A[j] = A[i]);

                A[i] ^= A[j];
                A[j] ^= A[i];
                A[i] ^= A[j];
            }

            i++;
            j--;
        }
    }

    public static int swap(int itself, int dummy) {
        return itself;
    }

    /*
     * solution - b
     * */
    public int[] solution1(int[] A, int K) {

        Map<Integer, Integer> map = new HashMap<>();

        int N = A.length;

        for (int i = 0; i < N; i++) {

            if (i + K > N - 1) {
               
                map.put(i + K - N, A[i]);
                // map.put((i + K)% N, A[i]);
            } 

            else 
                map.put(i + K, A[i]);            
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            A[e.getKey()] = e.getValue();
        }

        return A;
    }


    /*
     * solution - c
     * */
    public int[] solution2(int[] A, int K) {


        int[] result = new int[A.length];
        int N = A.length;


        for (int i = 0; i < N; i++) {
            result[(i + K) % N] = A[i];
        }


        return result;
    }
}
