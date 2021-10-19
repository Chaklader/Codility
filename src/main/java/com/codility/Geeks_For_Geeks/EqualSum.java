package com.codility.Geeks_For_Geeks;


/*
 * Given a set of non-negative integers, and a value sum, determine
 * if there is a subset of the given set with sum equal to given sum.
 * */

/**
 * Created by Chaklader on 7/15/18.
 */
public class EqualSum {


    public static boolean isSubsetSum(int set[],
                                      int n, int sum) {
        // Base Cases
        if (sum == 0)
            return true;

        if (n == 0 && sum != 0)
            return false;

        // If last element is greater than
        // sum, then ignore it
        if (set[n - 1] > sum)
            return isSubsetSum(set, n - 1, sum);

        /* else, check if sum can be obtained
        by any of the following
            (a) including the last element
            (b) excluding the last element */
        return isSubsetSum(set, n - 1, sum) ||
                isSubsetSum(set, n - 1, sum - set[n - 1]);
    }


    public static void main(String[] args) {

        int[] A = {3, 34, 4, 12, 5, 2};
        int K = 9;

//        Output:  True
    }
}
