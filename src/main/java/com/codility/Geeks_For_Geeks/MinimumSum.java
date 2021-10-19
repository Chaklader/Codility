package com.codility.Geeks_For_Geeks;


/*
 * Given a set of integers, the task is to divide it into two sets S1 and S2
 * such that the absolute difference between their sums is minimum. If there
 * is a set S with n elements, then if we assume Subset1 has m elements, Subset2
 * must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2))
 * should be minimum.
 * */

/**
 * Created by Chaklader on 7/15/18.
 */
public class MinimumSum {


    public static int findMin(int arr[], int n) {

        // Calculate sum of all elements
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        // Create an array to store
        // results of subproblems
        boolean dp[][] = new boolean[n + 1][sum + 1];

        // Initialize first column as true.
        // 0 sum is possible  with all elements.
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Initialize top row, except dp[0][0],
        // as false. With 0 elements, no other
        // sum except 0 is possible
        for (int i = 1; i <= sum; i++)
            dp[0][i] = false;

        // Fill the partition table
        // in bottom up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If i'th element is excluded
                dp[i][j] = dp[i - 1][j];

                // If i'th element is included
                if (arr[i - 1] <= j)
                    dp[i][j] |= dp[i - 1][j - arr[i - 1]];
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {
            // Find the
            if (dp[n][j] == true) {
                diff = sum - 2 * j;
                break;
            }
        }
        return diff;
    }


    /*
     * recursive solution
     * */
    public static int findMinRec(int arr[], int i,
                                 int sumCalculated,
                                 int sumTotal) {


        // If we have reached last element.
        // Sum of one subset is sumCalculated,
        // sum of other subset is sumTotal-
        // sumCalculated.  Return absolute
        // difference of two sums.
        if (i == 0) {
            return Math.abs((sumTotal - sumCalculated) -
                    sumCalculated);
        }


        // For every item arr[i], we have two choices
        // (1) We do not include it first set
        // (2) We include it in first set
        // We return minimum of two choices
        return Math.min(findMinRec(arr, i - 1, sumCalculated
                        + arr[i - 1], sumTotal),
                findMinRec(arr, i - 1,
                        sumCalculated, sumTotal));
    }

    /*
     * returns minimum possible difference between sums of two subsets
     * */
    public static int findMin1(int arr[], int n) {

        /*
         * Compute total sum of elements
         * */
        int sumTotal = 0;

        for (int i = 0; i < n; i++) {
            sumTotal += arr[i];
        }

        /*
         * Compute result using recursive function
         * */
        return findMinRec(arr, n, 0, sumTotal);
    }


    public static void main(String[] args) {

        /*
        Input:  arr[] = {1, 6, 11, 5}
        Output: 1
        Explanation:
        Subset1 = {1, 5, 6}, sum of Subset1 = 12
        Subset2 = {11}, sum of Subset2 = 11
        * */


        int A[] = {3, 1, 4, 2, 2, 1};

        int N = A.length;

        System.out.print("The minimum difference" +
                " between two sets is " +
                findMin1(A, N));
    }
}
