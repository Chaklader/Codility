package com.codility.L5_Prefix_Sums;


/*
* A non-empty array A consisting of N integers is given. The consecutive elements of array A represent consecutive cars on A road.

Array A contains only 0s and/or 1s:

0 represents A car traveling east,
1 represents A car traveling west.
The goal is to count passing cars. We say that A pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.

For example, consider array A such that:

    A[0] = 0
    A[1] = 1
    A[2] = 0
    A[3] = 1
    A[4] = 1

We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A of N integers, returns the number of pairs of passing cars.

The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:

    A[0] = 0
    A[1] = 1
    A[2] = 0
    A[3] = 1
    A[4] = 1

The function should return 5, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.
Complexity:

    expected worst-case time complexity is O(N);
    expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */

/**
 * Created by Chaklader on 6/23/18.
 */
public class PassingCars {



    /*
     * solution - a (1)
     * */
    public static int solution(int[] A) {

        int N = A.length;

        int countZero = 0;
        int result = 0;

        for (int i = 0; i < N; i++) {

            if (A[i] == 0) {
                
                countZero++;
                continue;
            } 

            result += countZero;

            if (result > 1000000000) {
                return -1;
            }
        }

        return result;
    }



    /*
     * solution - a (2)
     * */
    public static int solution(int[] A) {

        int result = 0;
        int ones = 0;

        int N = A.length;

        for (int i = N-1; i >= 0; i--) {

            if (A[i] == 1) {
                ones++;
            } 

            else {

                result += ones;

                if (result > 1000000000) {
                    return -1;
                }
            }
        }

        return result;
    }


    /*
     * solution - b
     * */
    public int solution1(int[] A) {

        int countOne = 0;
        int countZero = 0;

        int pair = 0;
        int counter = 0;


        for (int i = A.length - 1; i >= 0; i--) {

            counter++;

            if (A[i] == 0) {

                countZero++;
                pair += counter - countZero;

                if (pair > 1000000000) {
                    return -1;
                }
            }
        }

        return pair;
    }


    /*
     * solution - c
     * */
    public int solution2(int[] A) {

        int zerosCount = 0;
        int sum = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] == 0) {
                zerosCount++;
            } 

            // 
            else {
                sum += zerosCount;
            }

            if (sum > 1000000000) {
                return -1;
            }
        }

        return sum;
    }
}
