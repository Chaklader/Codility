package com.codility.L12_Euclidean_Algorithm;


/*
* Two positive integers N and M are given. Integer N represents the number of chocolates arranged in A circle, numbered from 0 to N − 1.

You start to eat the chocolates. After eating A chocolate you leave only A wrapper.

You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.

More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).

You stop eating when you encounter an empty wrapper.

For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.

The goal is to count the number of chocolates that you will eat, following the above rules.

Write A function:

class Solution { public int solution(int N, int M); }

that, given two positive integers N and M, returns the number of chocolates that you will eat.

For example, given integers N = 10 and M = 4. the function should return 5, as explained above.

Assume that:

N and M are integers within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(log(N+M));
expected worst-case space complexity is O(log(N+M)).
* */

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chaklader on 6/25/18.
 */
public class ChocolatesByNumbers {


    /*
     * The goal is to count the number of chocolates that you will eat
     * */

    /*
     * Greatest common divisor (gcd) of two or more integers, which are
     * not all zero, is the largest positive integer that divides each
     * of the integers.
     * */
    /*
     * solution - A
     */
    public int solution1(int N, int M) {

        int[] X = new int[N];

        int i = 0;
        int count = 0;

        while (X[i] == 0) {

            /*
             * eat the chocolate in the i-th index
             * */
            X[i] = 1;

            i = (i + M) % N;
            count++;
        }

        return count;
    }


    /*
     * solution - b
     */
    private static int solution(int N, int M) {

        int a = gcd(N, M);
        return N / a;
    }

    // Greatest Common Divisor (GCD)
    public static int gcd(int a, int b) {

        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }

    private static int lcm(int a, int b) {

        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b, int res) {

        if (a == b) {
            return res * a;
        } else if (a % 2 == 0 && b % 2 == 0) {
            return gcd(a / 2, b / 2, res * 2);
        } else if (a % 2 == 0) {
            return gcd(a / 2, b, res);
        } else if (b % 2 == 0) {
            return gcd(a, b / 2, res);
        } else if (a > b) {
            return gcd(a - b, b, res);
        } else {
            return gcd(a, b - a, res);
        }
    }


    /*
     * solution - c
     * */
    public int solution2(int N, int M) {

        return N / gcd(N, M);
    }

    /*
     * solution - d
     * */
    public int solution3(int N, int M) {

        return (int) (lcm(N, M) / M);
    }


    /*
     * solution - e
     * */
    public int solution4(int N, int M) {

        Map<Integer, Boolean> visited = new HashMap<>();

        int pos = 0;
        int result = 0;

        while (!visited.containsKey(pos)) {

            visited.put(pos, true);
            pos = (pos + M) % N;

            result++;
        }

        return result;
    }

    private long gcd(long p, long q) {

        if (q == 0) {
            return (int) p;
        }
        return gcd(q, p % q);
    }

    private long lcm(long p, long q) {

        return p * (q / gcd(p, q));
    }


    public static void main(String[] args) {

        System.out.println("Helsinki");
        System.out.println(gcd(4, 10));
    }
}
