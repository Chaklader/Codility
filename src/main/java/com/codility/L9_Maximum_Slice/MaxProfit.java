package com.codility.L9_Maximum_Slice;

/*
* 
An array A consisting of N integers is given. It contains daily prices of A stock share 
for A period of N consecutive days. If A single share was bought on day P and sold on day Q, 
where 0 ≤ P ≤ Q < N, then the profit of such transaction is equal to A[Q] − A[P], provided 
that A[Q] ≥ A[P]. Otherwise, the transaction brings loss of A[P] − A[Q].


For example, consider the following array A consisting of six elements such that:

    A[0] = 23171
    A[1] = 21011
    A[2] = 21123
    A[3] = 21366
    A[4] = 21013
    A[5] = 21367


If A share was bought on day 0 and sold on day 2, A loss of 2048 would occur because 
A[2] − A[0] = 21123 − 23171 = −2048. If A share was bought on day 4 and sold on day 5, 
A profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible 
profit was 356. It would occur if A share was bought on day 1 and sold on day 5.


Write A function,

    class Solution { public int solution(int[] A); }

That, given an array A consisting of N integers containing daily prices of A stock share 
for A period of N consecutive days, returns the maximum possible profit from one transaction 
during this period. The function should return 0 if it was impossible to gain any profit.


For example, given array A consisting of six elements such that:


      A[0] = 23171
      A[1] = 21011
      A[2] = 21123
      A[3] = 21366
      A[4] = 21013
      A[5] = 21367


The function should return 356, as explained above.

Assume that:

    N is an integer within the range [0..400,000];
    each element of array A is an integer within the range [0..200,000].
    Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */
import java.util.Arrays;


/**
 * Created by Chaklader on 6/24/18.
 */
public class MaxProfit {


    /*
     * solution - a
     */
    public static int solution(int[] A) {

        int N = A.length;

        int result = 0;
        int max = 0;

        for (int i = 1; i < N; i++) {
                    
            max = Math.max(0, max + A[i] - A[i - 1]);
            result = Math.max(max, result);
        }

        //return result > 0 ? result : 0;
        return result;
    }


    /*
     * solution - b
     */
    /*
     * PREMISE: To gain the max profit from A temporal series
     * of stock prices, it requires to buy with the min price.
     * */
    public static int solution1(int[] A) {

        int min = Integer.MAX_VALUE;
        int result = 0;

        for (int value : A) {

            if (value < min) {
                min = value;
            }

            int diff = value - min;

            if (diff > result) {
                result = diff;
            }            
        }

        return result;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        if (A == null || A.length < 2) {
            return 0;
        }

        int[] sells = new int[A.length];
        int[] buys = new int[A.length];

        sells[0] = 0;
        sells[1] = Math.max(0, A[1] - A[0]);

        buys[0] = -A[0];
        buys[1] = Math.max(-A[0], -A[1]);


        for (int i = 2; i < A.length; i++) {

            sells[i] = Math.max(sells[i - 1], buys[i - 1] + A[i]);
            buys[i] = Math.max(buys[i - 1], sells[i - 2] - A[i]);
        }

        return sells[sells.length - 1];
    }


    /*
     * solution - d
     * */
    public static int solution3(int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int[] C = new int[A.length - 1];

        for (int i = 1; i < A.length; i++) {
            C[i - 1] = A[i] - A[i - 1];
        }

        int maxEnding = 0;
        int maxSlice = 0;


        for (int i = 0; i < C.length; i++) {

            maxEnding = Math.max(maxEnding + C[i], 0);
            maxSlice = Math.max(maxEnding, maxSlice);
        }

        return Math.max(maxSlice, 0);
    }


    public static void main(String[] args) {

        int[] A = new int[6];

        A[0] = 23171;
        A[1] = 21011;
        A[2] = 21123;
        A[3] = 21366;
        A[4] = 21013;
        A[5] = 21367;

        System.out.println(solution(A));
    }
}
