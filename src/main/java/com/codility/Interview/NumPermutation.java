package com.codility.Interview;


import java.util.*;

/*
Q: 2

Task Two non-negative integers N and M are said to be similar if their decimal representations can be

obtained from each other by rearranging their digits. Note that a correct decimal representation does not

contain leading zeroes (except for number 0). For example: 1234 is similar to 4312; 12 is similar to 12; 113

is NOT similar to 133 (there are different numbers of individual digits); 100 is NOT similar to 10 (010

contains a leading zero, so it is not a correct decimal representation).



Write a function: class Solution { public int solution(int N); } that, given a non-negative integer N, returns

the number of non-negative integers similar to N. For example, given N = 1213 the function should return 12

because there are 12 integers similar to 1213, namely: 1123, 1132, 1213, 1231, 1312, 1321, 2113, 2131, 2311,

3112, 3121 and 3211. Given N = 123 the function should return 6 because there are six integers similar to 123,

namely: 123, 132, 213, 231, 312 and 321. Given N = 100 the function should return 1 because there is only one

similar integer (the number itself). 001 and 010 are both incorrect decimal representations of integers.

Given N = 0 the function should return 1 bacause there is only one similar integer (the number itself). Assume

that: N is an integer within the range [0..2,000,000,000]



Complexity
----------

expected worst-case time complexity is O(log(N))

expected worst-case space complexity is O(1)
*/


/*
 * Correctness: 76%
 * Performance: 0%
 * */

/**
 * Created by Chaklader on 8/8/18.
 */
public class NumPermutation {


    public static int solution(int N) {

        if(N == 0){
            return 1;
        }

        String s = String.valueOf(N);
        Set<String> set = new HashSet<>();

        permutation("", s, set);

        return set.size();
    }

    private static void permutation(String prefix, String str, Set<String> set) {

        int N = str.length();

        if (N == 0) {

            if (!prefix.startsWith("0")) {
                set.add(prefix);
            }
        } else {

            /*
             * "abcd"
             *
             * a bcd
             * a cbd
             * a dbc
             *
             *
             * */
            for (int i = 0; i < N; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), set);
            }
        }
    }
}
