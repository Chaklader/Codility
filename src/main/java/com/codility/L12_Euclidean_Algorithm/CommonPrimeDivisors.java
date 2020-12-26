package com.codility.L12_Euclidean_Algorithm;


/*
A prime is A positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers

are 2, 3, 5, 7, 11 and 13. A prime D is called A prime divisor of A positive integer P if there exists A positive

integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20. You are given two positive integers

N and M. The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.


For example, given
------------------

    N = 15 and M = 75, the prime divisors are the same: {3, 5}

    N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5}

    N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}


Write A function
----------------

class Solution { public int solution(int[] A, int[] B); }

that, given two non-empty arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.

For example, given:

    A[0] = 15   B[0] = 75

    A[1] = 10   B[1] = 30

    A[2] = 3    B[2] = 5


the function should return 1, because only one pair (15, 75) has the same set of prime divisors.

Assume that:

Z is an integer within the range [1..6,000];
each element of arrays A, B is an integer within the range [1..2,147,483,647].
Complexity:

expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */

/**
 * Created by Chaklader on 6/25/18.
 */
public class CommonPrimeDivisors {


    /*
     * solution - a
     */
    private static int solution(int[] A, int[] B) {

        int count = 0;
        int N = A.length;

        for (int i = 0; i < N; i++) {

            int x = A[i];
            int y = B[i];

            // Greatest Common Divisor (GCD)
            int gcd = gcd(x, y);
            int temp = 0;

            /*
             * we would like to reduce the key and y to 1
             * */
            while (x != 1) {

                temp = gcd(x, gcd);

                if (temp == 1) {
                    break;
                }

                x /= temp;
            }
            
            if (x != 1) {
                continue;
            }

            while (y != 1) {

                temp = gcd(y, gcd);

                if (temp == 1) {
                    break;
                }

                y /= temp;
            }

            if (y != 1) {
                continue;
            }

            count++;
        }

        return count;
    }

    // Greatest Common Divisor (GCD)
    public static int gcd(int a, int b) {

        if (a % b == 0) {
            return b;
        }

        return gcd(b, a % b);
    }


    /*
     * check if two numbers have the same prime divisors
     * */
    public static boolean samePrimeDivisors(int x, int y) {

        int gcd = gcd(x, y, 1);
        int gcdTmp = 0;

        while (x != 1) {

            gcdTmp = gcd(x, gcd);

            if (gcdTmp == 1) {
                break;
            }

            x /= gcdTmp;
        }

        if (x != 1) {
            return false;
        }

        while (y != 1) {

            gcdTmp = gcd(y, gcd);

            if (gcdTmp == 1) {
                break;
            }

            y /= gcdTmp;
        }

        if (y != 1) {
            return false;
        }

        return true;
    }


    /*
     * GCD is the two or more integers, which are not all zero,
     * is the largest positive integer that divides each of the
     * integers
     */
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
     * solution - b
     */
    public int solution1(int[] A, int[] B) {

        int count = 0;

        for (int i = 0; i < A.length; i++) {

            if (hasSamePrimeDivisors(A[i], B[i])) {
                count++;
            }
        }
        return count;
    }

    public boolean hasSamePrimeDivisors(int a, int b) {

        int gcdValue = gcd(a, b);
        int gcdA;
        int gcdB;

        while (a != 1) {

            gcdA = gcd(a, gcdValue);

            if (gcdA == 1) {
                break;
            }

            a = a / gcdA;
        }

        if (a != 1) {
            return false;
        }

        while (b != 1) {

            gcdB = gcd(b, gcdValue);

            if (gcdB == 1) {
                break;
            }

            b = b / gcdB;
        }

        return b == 1;
    }


    /*
     * solution - c
     */
    public int solution2(int[] A, int[] B) {

        int count = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] == 1 && B[i] == 1) {
                count++;
                continue;
            }

            /*
             * Two numbers n and m may have the same set of prime factors only
             * if (n / gcd1) and (m / gcd1) contain only factors included into gcd1
             * */
            int g = gcd(A[i], B[i]);

            int m = A[i] / g;
            int gcm = g;

            boolean containsA = false;

            while (true) {

                if (gcm % m == 0) {

                    containsA = true;
                    break;
                } else {

                    int newG = gcd1(gcm, m);

                    if (newG == 1) {
                        break;
                    }

                    m = m / newG;
                    gcm = newG;
                }
            }

            m = B[i] / g;
            gcm = g;

            boolean containsB = false;

            while (true) {

                if (gcm % m == 0) {
                    containsB = true;
                    break;
                } else {

                    int newG = gcd1(gcm, m);
                    if (newG == 1) {
                        break;
                    }

                    m = m / newG;
                    gcm = newG;
                }
            }

            if (containsA && containsB) {
                count++;
            }
        }

        return count;
    }

    private int gcd1(final int a, final int b) {

        if (a % b == 0) {
            return b;
        } else {
            return gcd1(b, a % b);
        }
    }


    /*
     * solution - d
     * */
    public int solution4(int[] A, int[] B) {

        int counter = 0;

        for (int i = 0; i < A.length; i++) {

            if (hasCommonPrimeDivisors(A[i], B[i])) {
                counter++;
            }
        }

        return counter;
    }

    private boolean hasCommonPrimeDivisors(int A, int B) {

        int commonGCD = gcd(A, B);
        int divisorA = A / commonGCD;
        int gcdA = gcd(commonGCD, divisorA);

        while (gcdA != 1) {
            divisorA /= gcdA;
            gcdA = gcd(commonGCD, divisorA);
        }

        int divisorB = B / commonGCD;
        int gcdB = gcd(commonGCD, divisorB);

        while (gcdB != 1) {
            divisorB /= gcdB;
            gcdB = gcd(commonGCD, divisorB);
        }

        return divisorA == 1 && divisorB == 1;
    }


    public static void main(String[] args) {

        System.out.println(gcd(15, 75));
    }
}
