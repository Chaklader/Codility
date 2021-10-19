package com.codility.L6_Sorting;

/*
* Write A function

class Solution { public int solution(int[] A); }

that, given an array A consisting of N integers, returns the number of distinct values in array A.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
For example, given array A consisting of six elements such that:

 A[0] = 2    A[1] = 1    A[2] = 1
 A[3] = 2    A[4] = 3    A[5] = 1
the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.

Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.*;
import java.util.stream.IntStream;


/**
 * Created by Chaklader on 6/24/18.
 */
public class Distinct {


    /*
     * solution - a
     */
    public static int solution(int[] A) {

        /*
         * time complexity: O(N * log(N)) > O(N)
         * */
        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < A.length; i++) {

            int value = freq.containsKey(A[i]) ? freq.get(A[i]) + 1 : 1;
            freq.put(A[i], value);
        }

        return freq.size();
    }


    /*
     * solution - b
     */
    public static int solution1(int[] A) {

        Set<Integer> set = new HashSet<Integer>();

        for (int a : A) {            
            set.add(a);
        }

        int size =  set.size();
        return size;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int result = ((Long) Arrays.stream(A).boxed().distinct().count()).intValue();
        return result;

        // return (int) Arrays.stream(A).distinct().count();
    }


    /*
     * solution - d
     */
    public static int solution3(int[] A) {

        /*
         * ---------------------------------------------
         *  Sorting       Best            Worst
         * ---------------------------------------------
         * Quicksort	    Ω(n log(n))	    O(n^2)
         * Mergesort	    Ω(n log(n))	    O(n log(n))
         * ---------------------------------------------
         * */

        Arrays.sort(A);
        int dups = 0;

        for (int i = 1; i < A.length; i++) {

            if (A[i] == A[i - 1]) {
                dups++;
            }
        }

        return A.length - dups;
    }


    /*
     * solution - d
     */
    public int solution4(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        int counter = 1;

        for (int i = 1; i < A.length; i++) {

            if (A[i - 1] < A[i]) {
                counter++;
            }
        }

        return counter;
    }



    /*
     * solution - e
     */
    public static int solution5(int[] A) {

        Set<Integer> set = new HashSet<>();
        
        for (int a: A){
            set.add(a);
        }
        
        return set.size();
    }


}
