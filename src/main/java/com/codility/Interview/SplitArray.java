package com.codility.Interview;



/*
 An integer X and a non-empty zero-indexed array A consisting of N integers are given. We are 
 
 interested in which elements of A are equal to X and which are different from X. The goal is 
 
 to split array A into two parts, such that the number of elements equal to X in the first part

 is the same as the number of elements different from X in the other part. More formally, we are

 looking for an index K such that: 0 ≤ K < N and the number of elements equal to X in A[0..K−1] 

 is the same as the number of elements different from X in A[K..N−1]. For K = 0, A[0..K−1] doe
 snot contain any elements.
 


For example, given integer X = 5 and array A such that:

        A = [5, 5, 1, 7, 2, 3, 5]


K equals 4, because: two of the elements of A[0..3] are equal to X, namely A[0] = A[1] = X, and
two of the elements of A[4..6] are different from X, namely A[4] and A[5].


Write a function solution(int X, int[] A); that, given an integer X and a non-empty zero-indexed array

A consisting of N integers, returns the value of index K satisfying the above conditions. If more
 than 
one index K satisfies the above conditions, your function may return any of them. If there is n
o such 
index, the function should return −1.


For example, given integer X and array A as above, the function should return 4, as explained a
bove.


Assume that: N is an integer within the range [1..100,000]; X is an integer within the range [0
..100,000]; 
Each element of array A is an integer within the range [0..100,000].



Complexity: Expected worst-case time complexity is O(N); Expected worst-case space complexity i
s O(1), 
beyond input storage (not counting the storage required for input arguments); Elements of input
 arrays 
can be modified.
* */



/**
 * Created by Chaklader on 7/5/18.
 */
public class SplitArray {


    /*
     * The goal is to split array A into two parts, such that the number of
     *
     * elements equal to X in the first part is the same as the number of
     *
     * elements different from X in the other part.
     *
     * A = [5, 5, 1, 7, 2, 3, 5]
     *
     * K equals 4, because: two of the elements of A[0..3] are equal to X = 5,
     *
     * namely A[0] = A[1] = X, and two of the elements of A[4..6] are different
     *
     * from X, namely A[4] and A[5]. If more than one index K satisfies the above
     *
     * conditions, your function may return any of them.
     *
     * */


    /*
     * solution - a
     * */
    // A = [5, 5, 1, 7, 2, 3, 5]
    public int solution(int X, int[] A) {

        int left = 0;
        int right = 0;

        int N = A.length;

        for (int i = 0; i < N; i++) {

            if (A[i] == X) {
                left++;
            }
        }

        for (int i = N - 1; i >= 0; i--) {

            if (A[i] == X) {
                left--;
            }

            if (left == (N - (i + 1)) - right) {
                return (i + 1);
            }


            if (A[i] == X) {
                right++;
            }
        }

        return -1;
    }


    /*
     * solution - b
     * */
    // A = [5, 5, 1, 7, 2, 3, 5]
    public int solution1(int X, int[] A) {


        if (A == null || A.length == 0) {
            return -1;
        }

        if (X < 0 || X > 100000) {
            return -1;
        }

        int N = A.length;

        if (N < 1 || N > 100000) {
            return -1;
        }

        int sum = 0, segment = 0;


        for (int j = 0; j < N; j++) {

            if (A[j] < 0 || A[j] > 100000) {
                return -1;
            }

            if (A[j] == X) {
                sum++;
                segment++;
            } 

            else {
                segment = 0;
            }
        }

        return (A[N - 1] != X || sum > segment) ? (N - sum) : N;
    }
}
