package com.codility.L11_Sieve_Of_Eratosthenes;

/*
* You are given an array A consisting of N integers.

For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.

For example, consider integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6

For the following elements:

    A[0] = 3, the non-divisors are: 2, 6,
    A[1] = 1, the non-divisors are: 3, 2, 3, 6,
    A[2] = 2, the non-divisors are: 3, 3, 6,
    A[3] = 3, the non-divisors are: 2, 6,
    A[4] = 6, there aren't any non-divisors.

Write A function:

class Solution { public int[] solution(int[] A); }

that, given an array A consisting of N integers, returns A sequence of integers representing the amount of non-divisors.

The sequence should be returned as:

    A structure Results (in C), or
    A vector of integers (in C++), or
    A record Results (in Pascal), or

an array of integers (in any other programming language).
For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6

the function should return [2, 4, 3, 2, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
each element of array A is an integer within the range [1..2 * N].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.*;

/**
 * Created by Chaklader on 6/25/18.
 */
public class CountNonDivisible {

    /*
     * Given an array A consisting of N integers, returns a
     * sequence of integers representing the amount of non
     * divisors
     * */

    /*
     * solution - a
     */
    public static int[] solution(int[] A) {

        int N = A.length;

        /*
         * each element of array A is an integer within the range [1..2 * N]
         * */
        int[][] D = new int[2 * N + 1][2];
        int[] result = new int[N];

        /*
        * A = [3,1,2,3,6]

        D = [
           0 -1
           1  0
           1  0
           2  0
           0 -1
           0 -1
           1  0
           0 -1
           0 -1
           0 -1
           0 -1
        ]
        * */
        for (int i = 0; i < N; i++) {

            D[A[i]][0]++;
            D[A[i]][1] = -1;
        }

        for (int i = 0; i < N; i++) {

            if (D[A[i]][1] == -1) {

                D[A[i]][1] = 0;

                for (int j = 1; (long) j * j <= A[i]; j++) {

                    if (A[i] % j == 0) {

                        /*
                         * update the number of divisors for A[i]
                         * */
                        D[A[i]][1] += D[j][0];

                        /*
                         * j is not the square root of A[i]
                         * */
                        if (A[i] / j != j) {

                            int k = A[i] / j;

                            D[A[i]][1] += D[k][0];
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            result[i] = N - D[A[i]][1];
        }

        return result;
    }


    /*
     * print 2-D matrix as each row
     * */
    public static void print(int[][] A) {

        for (int[] a : A) {
            System.out.println(Arrays.toString(a));
        }
    }



    /*
    * solution - bb
    */

    public static int[] solution(int[] A) {


        int N = A.length;

        int[] result = new int[N];

        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());

        for (int i = 0; i < N; i++) {

            int temp = A[i];

            List<Integer> copyOfList = new ArrayList<>(list);
            copyOfList.remove(i);

            int count = 0;

            for (int j = 0; j < copyOfList.size(); j++) {

                if (!(temp % copyOfList.get(j) == 0)) {
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }
    /*
    END of solution - bb
    */


    /*
     * solution - b
     */
    public static int[] solution1(int[] A) {

        int[][] D = new int[A.length * 2 + 1][2];

        for (int i = 0; i < A.length; i++) {
            D[A[i]][0]++;
            D[A[i]][1] = -1;
        }

        for (int i = 0; i < A.length; i++) {

            if (D[A[i]][1] == -1) {

                D[A[i]][1] = 0;

                for (int j = 1; j <= Math.sqrt(A[i]); j++) {

                    if (A[i] % j == 0 && A[i] / j != j) {

                        D[A[i]][1] += D[j][0];
                        D[A[i]][1] += D[A[i] / j][0];
                    } else if (A[i] % j == 0 && A[i] / j == j) {

                        D[A[i]][1] += D[j][0];
                    }
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = A.length - D[A[i]][1];
        }
        return A;
    }


    /*
     * solution - c
     */
    public int[] solution2(int[] A) {

        Set<Integer> setA = asSet(A);
        List<Set<Integer>> divisors = computeDivisors(A.length * 2);

        int occurrences[] = computeOccurrences(A);
        int nonDivisors[] = new int[A.length];

        for (int i = 0; i < A.length; i++) {

            int value = A[i];
            Set<Integer> divisor = divisors.get(value);

            int totalOccurances = 0;

            for (Integer d : divisor) {

                if (setA.contains(d)) {

                    totalOccurances += occurrences[d];
                }
            }

            nonDivisors[i] = A.length - totalOccurances;
        }

        return nonDivisors;
    }


    private static Set<Integer> asSet(int A[]) {

        Set<Integer> result = new HashSet<Integer>();

        for (int value : A) {
            result.add(value);
        }

        return result;
    }


    /**
     * Computes A list that contains for each i in [0...maxValue+1] A set
     * with all divisors of i. This is basically an "Eratosthenes Sieve".
     * But in addition to setting the entries of A list to 'false' (indicating
     * that the respective numbers are non-prime), this methods inserts the
     * divisors into the corresponding set.
     */
    private static List<Set<Integer>> computeDivisors(int maxValue) {

        List<Boolean> prime = new ArrayList<Boolean>();
        prime.addAll(Collections.nCopies(maxValue + 1, Boolean.TRUE));

        List<Set<Integer>> divisors = new ArrayList<Set<Integer>>();

        for (int i = 0; i < maxValue + 1; i++) {

            Set<Integer> d = new HashSet<Integer>();
            d.add(1);
            d.add(i);
            divisors.add(d);
        }

        for (int i = 2; i <= maxValue; i++) {

            int next = i + i;
            while (next <= maxValue) {

                divisors.get(next).addAll(divisors.get(i));
                prime.set(next, Boolean.FALSE);
                next += i;
            }
        }
        return divisors;
    }

    /**
     * Computes an array of length 2*A.length+1, where each entry
     * i contains the number of occurrences of value i in array A
     */
    private static int[] computeOccurrences(int A[]) {

        int occurances[] = new int[A.length * 2 + 1];
        for (int i = 0; i < A.length; i++) {

            int value = A[i];
            occurances[value]++;
        }
        return occurances;
    }


    /*
     * solution - d
     */
    public int[] solution3(int[] A) {

        int[] result = new int[A.length];

        System.arraycopy(A, 0, result, 0, A.length);
        Arrays.sort(result); // O(N * log(N))

        // max value is <= 2 * N
        int[] N = new int[A.length * 2];
        int C = 0;


        /*
         * loops give O(N * log(N)) because the total sum of steps is no
         * more than N/1 + N/2 + N/3 + ... + N/N which is O(N * log(N))
         * */
        for (int i = 0; i < result.length; i++) {

            C += 1;

            if (i + 1 < result.length && result[i] == result[i + 1]) {
                continue;
            }

            for (int j = result[i] - 1; j < N.length; j += result[i]) {
                N[j] += C;
            }

            C = 0;
        }

        for (int i = 0; i < A.length; i++) {
            result[i] = A.length - N[A[i] - 1];
        }

        return result;
    }


    /**
     * print an 2D array like table
     */
    public static void arrayToTable(int[][] grid) {

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }

            System.out.println();
        }
    }


    /*
     * solution - e
     * */
    public int[] solution4(int[] A) {

        int[] copy = A.clone();

        Arrays.sort(copy);

        HashMap<Integer, ArrayList<Integer>> factors = new HashMap<>();

        for (int i = 0; i < A.length; i++) {

            /*
             * find all factors for a number
             * */
            ArrayList<Integer> currentFactors = new ArrayList<>();

            for (int j = 1; j <= Math.sqrt(A[i]); j++) {

                if (A[i] % j == 0) {

                    currentFactors.add(j);

                    if (j != A[i] / j) {
                        currentFactors.add(A[i] / j);
                    }
                }
            }

            factors.put(A[i], currentFactors);
        }

        ArrayList<Integer> one = new ArrayList<Integer>();

        one.add(1);
        factors.put(1, one);

        HashMap<Integer, Integer> distinct = new HashMap<>();

        for (int i = 0; i < A.length; i++) {

            if (distinct.containsKey(A[i])) {
                distinct.put(A[i], distinct.get(A[i]) + 1);
            } else {
                distinct.put(A[i], 1);
            }
        }

        int[] answer = new int[A.length];
        Arrays.fill(answer, A.length);

        for (int i = 0; i < A.length; i++) {

            ArrayList<Integer> currentFactors = factors.get(A[i]);

            for (int j = 0; j < currentFactors.size(); j++) {

                if (Arrays.binarySearch(copy, currentFactors.get(j)) >= 0) {
                    answer[i] -= distinct.get(currentFactors.get(j));
                }
            }
        }

        return answer;
    }
}
