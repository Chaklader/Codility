package com.codility.Interview;


/*
Every week or so, John travels to another city to meet his business partners. He always uses the

same highway, and since the trip is quite long, he stops at some rest areas to eat and refuel.

He has A fixed habit of stopping exactly three times: the first time to eat some light salad,

the second time to eat a pizza, and the third time to eat some nice cake. Different rest areas

offer different kinds of food, and John knows the exact locations of his favorite places: There

are N rest stops offering salads; the K-th stop is located at A[K] kilometers from the beginning

of the highway. There are N stops offering pizzas; the K-th stop is located at B[K] kilometers.

Finally, there are N stops offering cakes; the K-th stop is located at C[K] kilometers. Apart

from his fixed habit, John likes to change small details, so he never visits the same three

places more than once. He wonders how many journeys he must make before he is forced to repeat

A triplet of rest stops that he has used previously. Write A function:




class Solution { public int solution(int[] A, int[] B, int[] C); } that, given three non-empty

arrays A, B, C consisting of N integers each, denoting the locations of rest stops offering

different types of food, returns the number of ways John can pick three stops in different

locations (two locations are different if they have different distance from the beginning of

the highway), such that the first stop offers salad, the second one offers pizza, and the third

one offers cake. If the calculated result is greater than 1,000,000,000 the function should

return −1. Note that the rest stops can be given in any order and there could be many rest stops

of the same kind at the same kilometer. For example, given N = 2, A = [29, 50], B = [37, 61],

C = [37, 70], the function should return 3, since there are three ways to stop: (29, 37, 70),

or (29, 61, 70), or (50, 61, 70). The first triplet means that John can stop for salad at the

29th kilometer of his travel, for pizza at the 37th kilometer, and for cake at the 70th kilometer.

Given N = 2, A = [29, 29], B = [61, 61], C = [70, 70], the function should return 8, since there

are two ways of choosing each of the stops, making it 2*2*2 = 8. Given N = 1, A = [5], B = [5],

C = [5], the function should return 0. Assume that: N is an integer within the range [1..40,000];

each element of arrays A, B, C is an integer within the range [1..1,000,000,000].



Complexity
----------

Expected worst-case time complexity is O(N*log(N));

Expected worst-case space complexity is O(N) (not counting the storage required for input arguments)
*/


import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Chaklader on 7/5/18.
 */
public class RestStops {


    /*
     * solution - a
     * */
    public static int solution(int[] A, int[] B, int[] C) {


        int N = A.length;
        int count = 0;


        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);


        // A = [29, 50], B = [37, 61], C = [37, 70]

        for (int i = 0; i < N; i++) {

            int maxIndex = maximumIndex(A, B[i]);
            int minIndex = minimumIndex(C, B[i]);
        
            if (maxIndex == -1) {
                continue;
            }

            if (minIndex == -1) {
                return count;
            }

            count += (maxIndex + 1) * (N - minIndex);

            if(count > 1000000000){
                return -1;
            }

        }

        return count;
    }


    /*
     * in binary search, the condition breaks at low > high and after the break, low = middle = high +1
     * */
    /*
     * maximum index of an item in the sorted array A which is less than x
     * */
    public static int maximumIndex(int[] A, int x) {

        int N = A.length;

        if (x < A[0]) {
            return -1;
        }

        if (x > A[N - 1]) {
            return (N - 1);
        }


        int low = 0;
        int high = N - 1;


        while (low <= high) {

            int middle = (high + low) / 2;

            if (x < A[middle]) {
                high = middle - 1;
            } 

            else if (x > A[middle]) {
                low = middle + 1;
            } 

            else {
                return (middle - 1);
            }
        }

        return high;
    }


    /*
     * min index of an item in the sorted array C which is just greater than x
     * */
    public static int minimumIndex(int[] C, int x) {

        int N = C.length;

        if (x < C[0]) {
            return 0;
        }

        if (x > C[N - 1]) {
            return -1;
        }


        int low = 0;
        int high = N - 1;

        
        while (low <= high) {

            int middle = (high + low) / 2;

            if (x < C[middle]) {
                high = middle - 1;
            } 

            else if (x > C[middle]) {
                low = middle + 1;
            } 

            else {
                return (middle + 1);
            }
        }

        return low;
    }


    // test = -1 
    private int findMiddle(int low, int high, int test){

        while (low <= high) {

            int middle = (high + low) / 2;

            if (x < C[middle]) {
                high = middle - 1;
            } 

            else if (x > C[middle]) {
                low = middle + 1;
            } 

            else 
                test = middle;
        }

        return test;
    }


    /*
     * solution - b
     * */
    public static int solution1(int[] A, int[] B, int[] C) {

        int N = A.length;
        int count = 0;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                for (int k = 0; k < N; k++) {

                    if (A[i] < B[j] && B[j] < C[k]) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
