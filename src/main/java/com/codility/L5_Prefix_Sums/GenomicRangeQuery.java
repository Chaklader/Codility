package com.codility.L5_Prefix_Sums;

/*
* A DNA sequence can be represented as A string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact getImpactFactor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact getImpactFactor of nucleotides contained in A particular part of the given DNA sequence?

The DNA sequence is given as A non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact getImpactFactor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).

For example, consider string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6

The answers to these M = 3 queries are as follows:

The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
The part between positions 5 and 5 contains A single nucleotide T, whose impact getImpactFactor is 4, so the answer is 4.
The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact getImpactFactor is 1, so the answer is 1.
Write A function:

class Solution { public int[] solution(String S, int[] P, int[] Q); }

that, given A non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

The sequence should be returned as:

A Results structure (in C), or
A vector of integers (in C++), or
A Results record (in Pascal), or
an array of integers (in any other programming language).
For example, given the string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
the function should return the values [2, 4, 1], as explained above.

Assume that:

N is an integer within the range [1..100,000];
M is an integer within the range [1..50,000];
each element of arrays P, Q is an integer within the range [0..N − 1];
P[K] ≤ Q[K], where 0 ≤ K < M;
string S consists only of upper-case English letters A, C, G, T.
Complexity:

expected worst-case time complexity is O(N+M);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chaklader on 6/23/18.
 */
public class GenomicRangeQuery {


    /*
     * solution - a
     * */
    public int[] solution(String S, int[] P, int[] Q) {

        int N = P.length;
        int[] C = new int[N];

        for (int i = 0; i < N; i++) {

            int value = getImpactFactor(S, P[i], Q[i]);

            if(value == -1){
                continue;
            }

            C[i] = value;
        }

        return C;
    }


    /*
     *  Nucleotides of types A, C, G and T
     *  have impact factors of 1, 2, 3 and 4
     * */
    public int getImpactFactor(String S, int i, int j) {

        int N = S.length();

        if(i < 0 || i > N-1){
            return -1;
        }

        if(j < 0 || j > N-1){
            return -1;
        }

        String temp = S.substring(i, j + 1);

        if (temp.contains("A")) {
            return 1;
        } 

        else if (temp.contains("C")) {
            return 2;
        }

        else if (temp.contains("G")) {
            return 3;
        }

        return 4;
    }



    /*
     * solution - b
     * */
    public static int[] solution1(String S, int[] P, int[] Q) {


        int[] C = new int[P.length];
        Map<Integer, ArrayList<Integer>> prefSums = getPrefixSum(S);

        for (int i = 0; i < Q.length; i++) {

            for (int j = 1; j <= 4; j++) {

                int high = prefSums.get(j).get(Q[i]);
                int low = P[i] == 0 ? 0 : prefSums.get(j).get(P[i] - 1);

                if (high - low > 0) {
                    C[i] = j;
                    break;
                }
            }
        }

        return C;
    }


    /*
     * map contains the nucleotides value and their frequencies
     * */
    public static Map<Integer, ArrayList<Integer>> getPrefixSum(String S) {

        Map<Integer, ArrayList<Integer>> prefixSum = new HashMap<Integer, ArrayList<Integer>>();

        for (int j = 0; j < 4; j++) {
            prefixSum.put(j + 1, new ArrayList<Integer>());
        }

        int[] counters = new int[4];

        for (int i = 0; i < S.length(); i++) {

            switch (S.charAt(i)) {

                case 'A':
                    counters[0]++;
                    break;

                case 'C':
                    counters[1]++;
                    break;

                case 'G':
                    counters[2]++;
                    break;

                case 'T':
                    counters[3]++;
                    break;

                default:
                    break;
            }

            for (int j = 0; j < 4; j++) {
                prefixSum.get(j + 1).add(counters[j]);
            }
        }

        return prefixSum;
    }


    /*
     * solution -(c)
     * */
    /*
     * This solution uses prefix. Time complexity is O(N + M)
     * */
    public int[] solution2(String S, int[] P, int[] Q) {


        /*
         * used jagged array to hold the prefix sums of each A, C
         * and G genoms we don't need to get prefix sums of T
         * */
        int[][] genoms = new int[3][S.length() + 1];

        /*
         * If the char is found in the index i, then we set it
         * to be 1 else they are 0 3 short values are needed
         * for this reason*/
        short a, c, g;

        for (int i = 0; i < S.length(); i++) {

            a = 0;
            c = 0;
            g = 0;

            if ('A' == (S.charAt(i))) {
                a = 1;
            }

            if ('C' == (S.charAt(i))) {
                c = 1;
            }

            if ('G' == (S.charAt(i))) {
                g = 1;
            }

            /*
             * calculate prefix sums.
             * */
            genoms[0][i + 1] = genoms[0][i] + a;
            genoms[1][i + 1] = genoms[1][i] + c;
            genoms[2][i + 1] = genoms[2][i] + g;
        }

        int[] result = new int[P.length];


        /*
         * Go through the provided P[] and Q[] arrays as intervals
         * */
        for (int i = 0; i < P.length; i++) {

            int fromIndex = P[i] + 1;
            int toIndex = Q[i] + 1;


            /*
             * If the substring contains A, then genoms[0][toIndex] - genoms[0][fromIndex-1] > 0
             * */
            if (genoms[0][toIndex] - genoms[0][fromIndex - 1] > 0) {
                result[i] = 1;
            } else if (genoms[1][toIndex] - genoms[1][fromIndex - 1] > 0) {
                result[i] = 2;
            } else if (genoms[2][toIndex] - genoms[2][fromIndex - 1] > 0) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }

        return result;
    }


    /*
     * solution - d
     * */
    public int[] solution3(String S, int[] P, int[] Q) {

        int N = S.length();
        int[] C = new int[(N + 1) * 4];

        for (int i = 0; i < N; i++) {

            char c = S.charAt(i);
            int k = (i + 1) * 4;

            C[k] = C[k - 4];

            C[k + 1] = C[k - 3];
            C[k + 2] = C[k - 2];
            C[k + 3] = C[k - 1];

            switch (c) {

                case 'A':
                    C[k]++;
                    break;

                case 'C':
                    C[k + 1]++;
                    break;

                case 'G':
                    C[k + 2]++;
                    break;

                case 'T':
                    C[k + 3]++;
                    break;
            }
        }

        for (int i = 0; i < P.length; i++) {

            int from = P[i];
            int to = Q[i];

            int m = 4 * from;
            int n = 4 * (to + 1);

            if (C[n] - C[m] > 0) {
                P[i] = 1;
                continue;
            }

            if (C[n + 1] - C[m + 1] > 0) {
                P[i] = 2;
                continue;
            }

            if (C[n + 2] - C[m + 2] > 0) {
                P[i] = 3;
                continue;
            }

            if (C[n + 3] - C[m + 3] > 0) {
                P[i] = 4;
            }
        }

        return P;
    }


    /*
     * solution - e
     * */
    public int[] solution4(String S, int[] P, int[] Q) {

        int[] C = new int[S.length()];

        for (int i = 0; i < S.length(); i++) {

            switch (S.charAt(i)) {

                case 'A':
                    C[i] = 0;
                    break;
                case 'C':
                    C[i] = 1;
                    break;
                case 'G':
                    C[i] = 2;
                    break;
                case 'T':
                    C[i] = 3;
                    break;
            }
        }

        int[][] D = new int[4][C.length + 1];
        D[C[0]][0] = 1;

        for (int i = 0; i < 4; i++) {

            for (int j = 1; j < C.length; j++) {

                if (C[j] == i) {
                    D[i][j] = D[i][j - 1] + 1;
                } else {
                    D[i][j] = D[i][j - 1];
                }
            }
        }

        int[] result = new int[P.length];

        for (int i = 0; i < P.length; i++) {

            for (int j = 0; j < 4; j++) {
                if (D[j][Q[i]] - (P[i] - 1 >= 0 ? D[j][P[i] - 1] : 0) > 0) {
                    result[i] = j + 1;
                    break;
                }
            }
        }

        return result;
    }
}
