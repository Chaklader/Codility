package com.codility.Interview;


import java.util.*;


/*
---
Q:3
---


A prefix of a string S is any leading contiguous part of S. For example, the string "codility" has the following

prefixes: "", "c", "co", "cod", "codi", "codil", "codili", "codilit" and "codility". A prefix of S is called

proper if it is shorter than S. A suffix of a string S is any trailing contigous part of S. For example, the string

"codility" has the following suffixes: "", "y", "ty", "ity", "lity", "ility", "dility", "odility" and "codility".

A suffix of S is called proper if it is shorter than S.




Write a function: class Solution { public int solution(String S); } that, given a string S consisting of N

characters, returns the length of the longest string that is both a proper prefix of S and a proper suffix of S.

For example, given S = "abbabba" the function should return 4, because:


Proper prefixes of S are: "", "a", "ab", "abb", "abba", "abbab", "abbabb"; proper suffixes of

S are: "", "a", "ba", "bba", "abba", "babba", "bbabba"; string "abba" is both a proper prefix and a proper suffix

of S; this is the longest such string. For example, given S = "codility" the function should return 0, because:

string "" is both a proper prefix and a proper suffix of S; this is the longest such string.



Complexity: expected worst-case time complexity is O(N); expected worst-case space complexity is O(N) (not

counting the storage required for input arguments). Assume that: the length of S is within the range [1..1,000,000];

string S consists only of lowercase letters (a−z). Copyright 2009–2018 by Codility Limited.
*/


/*
 * Correctness: 100%
 * Performance: 0%
 * */

/**
 * Created by Chaklader on 8/8/18.
 */
public class PrefixSuffixLength {


    /*
     * solution - a
     * */
    public static int solution(String S) {

        int N = S.length();

        for (int i = N - 1; i >= 1; i--) {

            String prefix = S.substring(0, i);
            String suffix = S.substring(N - i, N);

            if (prefix.equals(suffix)) {

                // System.out.println("Prefix and Suffix = " + prefix);
                return i;
            }
        }

        return 0;
    }



    /*
     * solution - b
     * */
    public static int solution1(String S) {

        int N = S.length();

        /*
         * for empty or single alphabate String
         * */
        if (N == 0 || N == 1) {
            return N;
        }

        /*
         * check if the largest match possible
         * */
        if (S.substring(0, N - 1).equals(S.substring(1, N))) {
            return (N - 1);
        }

        int result = 0;
        int i = N / 2;

        // addadda
        while (i > 0 && i < N) {

            String prefix = S.substring(0, i);
            String suffix = S.substring(N - i, N);

            if (prefix.equals(suffix)) {
                result = i;
                i++;
            } else {
                i--;
            }
        }

        return result;
    }


    /*
     * solution - c
     * */
    public static int solution2(String S) {

        List<String> prefixes = new ArrayList<>();
        List<String> suffixes = new ArrayList<>();

        int N = S.length();

        for (int i = 0; i < N - 1; i++) {

            String prefix = S.substring(0, i + 1);
            prefixes.add(prefix);
        }

        for (int i = N - 1; i >= 1; i--) {

            String suffix = S.substring(i, N);
            suffixes.add(suffix);
        }

        int M = prefixes.size();
        int result = 0;

        for (int i = M - 1; i >= 0; i--) {

            if (prefixes.get(i).equals(suffixes.get(i))) {

                result = prefixes.get(i).length();
                return result;
            }
        }

        return result;
    }


    public static void main(String[] args) {

        String s = "addadda";
        System.out.println(solution1(s));
    }

}
