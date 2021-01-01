package com.codility.L1_Iterations;


/*
* A binary gap within A positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

For example, number 9 has binary representation 1001 and contains the binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

Write A function:

class Solution { public int solution(int N); }

that, given A positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain FrogJmp binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

Assume that:

N is an integer within the range [1..2,147,483,647].
Complexity:

expected worst-case time complexity is O(log(N));
expected worst-case space complexity is O(1).
* */


import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Chaklader on 6/23/18.
 */
public class BinaryGap {



    /*
     * solution - a
     * */
    public int solution(int N) {

        int binaryGap = Stream.of(Integer.toBinaryString(N)
                .replaceAll("0+$", "")
                .split("1+")).filter(a -> a != null)
                .max((a, b) -> Integer.compare(a.length(), b.length())).map(String::length).orElse(0);

        return binaryGap;
    }


    /*
     * solution - b
     * */
    public int solution1(int N) {


//        int max = Integer.MIN_VALUE;
        int max = 1 << 31;

        // String binary = String.valueOf(Integer.toBinaryString(N));
        String binary = Integer.toBinaryString(N);

        /*
         * remove the end zeros and split the String based on "1"
         * */
        String[] arr = binary.replaceAll("0+$", "").split("1+");

        for (String s : arr) {

            if (s == null) {
                continue;
            }

            max = max > s.length() ? max : s.length();
        }

        return max;
    }


    /*
     * solution - c
     * */
    public static int solution2(int N) {

        String binary = Integer.toBinaryString(N);

        int len = binary.length();

        /*
         * Start with 1 because leading element is always '1'
         *
         * Binary 5 is 101. You can write it as 0101. The same.
         * Or 00000101, which is still the same. If you want,
         * you can write it as 0000000000000101. Still means "5"
         * in binary.
         * */

        /*
         *
         * Algorithm
         * ---------
         *
         *
         * */
        int j = 0;

        for (int i = 0; i < len; i++, j++) {
            if (binary.charAt(i) == '0') {
                continue;
            } 
            else 
                break;
        }

        int max = 0;
        int zerosCount = 0;

        for (j = j + 1; j < len; j++) {

            if (binary.charAt(j) == '0') {
                zerosCount++;
            } 

            else {
                max = Math.max(zerosCount, max);
                zerosCount = 0;
            }
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println(solution2(1041));
    }
}



