package com.codility.Interview;


/*
Inclusion Exclusion Principle
-----------------------------

Venn diagram showing the union of sets A and B as everything not in white In combinatorics

(combinatorial mathematics), the inclusion–exclusion principle is a counting technique which

generalizes the familiar method of obtaining the number of elements in the union of two finite

sets; symbolically expressed as



|A\cup B|=|A|+|B|-|A\cap B|,} |A\cup B|=|A|+|B|-|A\cap B|,

where A and B are two finite sets and |S| indicates the cardinality of a set S

(which may be considered as the number of elements of the set, if the set is finite).

The formula expresses the fact that the sum of the sizes of the two sets may be too

large since some elements may be counted twice. The double-counted elements are those

in the intersection of the two sets and the count is corrected by subtracting the size

of the intersection.



The principle is more clearly seen in the case of three sets, which for the sets A, B and C is give
n by


|A\cup B\cup C|=|A|+|B|+|C|-|A\cap B|-|A\cap C|-|B\cap C|+|A\cap B\cap C|.} |A\cup B\cup

C|=|A|+|B|+|C|-|A\cap B|-|A\cap C|-|B\cap C|+|A\cap B\cap C|
*/


/*
K caterpillars are eating their way through N leaves, each caterpillar falls from

leaf to leaf in a unique sequence, all caterpillars start at a twig at position 0

and falls onto the leaves at position between 1 and N. Each caterpillar j has as

associated jump number Aj. A caterpillar with jump number j eats leaves at positions

that are multiple of j. It will proceed in the order j, 2j, 3j…. till it reaches the

end of the leaves and it stops and build its cocoon. Given a set A of K elements

K<-15, N<=10^9, we need to determine the number of uneaten leaves.



    Input:

        N = No of uneaten leaves

        K = No. of caterpillars

        A = Array of integer jump numbers

    Output:

        The integer nu. Of uneaten leaves

    Sample Input:

        10
        3
        2
        4
        5

    Output:

        4

    Explanation:

        [2, 4, 5] is a j member jump numbers, all leaves which are multiple of 2, 4, and 5 are

        eaten, leaves 1,3,7,9 are left, and thus the no. 4
*/


import java.util.*;

/**
 * Created by Chaklader on 7/5/18.
 */
public class CountUneatenLeaves {


    /*
     * solution - a
     * */
    public static int solution(int N, int[] A) {

        int total = 0;

        for (int i = 0; i < A.length; i++) {

            int multiplier = (int) Math.pow(-1, i);
            total += multiplier * combination(A, i + 1, N);
        }

        return N - total;
    }

    private static int calc(int[] combination, int[] elements, int num) {

        int eaten = 0;

        if (combination.length == 1) {
            eaten = (int) Math.floor(num / elements[combination[0]]);
        } else {

            int lcm = lcm(elements[combination[0]], elements[combination[1]]);

            for (int i = 2; i < combination.length; i++) {
                lcm = lcm(lcm, elements[combination[i]]);
            }
            eaten = Math.abs((int) Math.floor(num / lcm));
        }
        return eaten;
    }

    private static int lcm(int a, int b) {
        return a * (b / findGCD(a, b));
    }

    private static int findGCD(int number1, int number2) {

        if (number2 == 0) {
            return number1;
        }

        return findGCD(number2, number1 % number2);
    }

    public static int combination(int[] elements, int K, int num) {


        /*
         * get the length of the array e.g. for {'A','B','C','D'} => N = 4
         * */
        int N = elements.length;

        /*
         * get the combination by index e.g. 01 --> AB , 23 --> CD
         * */
        int combination[] = new int[K];

        // position of current index
        //  if (right = 1)              right*
        //  index ==>       0   |   1   |   2
        //  element ==>     A   |   B   |   C

        int r = 0;
        int index = 0;
        int total = 0;

        while (r >= 0) {


            /*
             * possible indexes for 1st position "right=0" are "0,1,2" --> "A,B,C"
             * possible indexes for 2nd position "right=1" are "1,2,3" --> "B,C,D"
             * */

            /*
             * for right = 0 ==> index < (4+ (0 - 2)) = 2
             * */
            if (index <= (N + (r - K))) {
                combination[r] = index;

                /*
                 * if we are at the last position print and increase the index
                 * */
                if (r == K - 1) {

                    /*
                     * do something with the combination e.g. add to list or print
                     * */
                    total += calc(combination, elements, num);
                    index++;
                } else {

                    /*
                     * select index for next position
                     * */
                    index = combination[r] + 1;
                    r++;
                }
            } else {

                r--;
                if (r > 0) index = combination[r] + 1;
                else index = combination[0] + 1;
            }
        }

        return total;
    }


    /*
     * solution - b
     * */
    private static int solution1(int N, int[] A) {

        int factors = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();

        findFactorialsOfAllCombinations(0, A, 0, new int[A.length], map, N);

        for (int key : map.keySet()) {

            if ((key & 1) != 1) {
                List<Integer> list = map.get(key);
                for (int i : list) {
                    factors -= i;
                }
            } else {
                List<Integer> list = map.get(key);
                for (int i : list) {
                    factors += i;
                }
            }
        }

        return N - factors;
    }


    private static void findFactorialsOfAllCombinations(int start, int[] array, int K, int[] result, Map<Integer, List<Integer>> map, int N) {

        if (K > 0) {

            if (!map.containsKey(K)) {
                map.put(K, new ArrayList<Integer>());
            }
            List<Integer> list = map.get(K);
            int[] temp = new int[K];
            System.arraycopy(result, 0, temp, 0, K);
            int factors = (int) Math.floor(N / lcm1(temp));
            list.add(factors);
        }
        for (int i = start; i < array.length; i++) {
            result[K] = array[i];
            findFactorialsOfAllCombinations(i + 1, array, K + 1, result, map, N);
        }
    }


    private static int lcm1(int[] input) {

        int result = input[0];

        for (int i = 1; i < input.length; i++) {
            result = lcm2(result, input[i]);
        }

        return result;
    }

    private static int countUneatenLeavesBruteForce(int N, int[] A) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            boolean found = false;
            for (int j : A) {
                if (i >= j && i % j == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                count++;
            }
        }
        return count;
    }

    private static int lcm2(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }


    /*
     * solution - c
     * */
    public static int solution2(int[] array, int n) {

        ArrayList<Integer> uneatenLeaves = new ArrayList<Integer>();
        ArrayList<Integer> eatenLeaves = new ArrayList<Integer>();

        for (int i = 1; i <= n; i++) {
            uneatenLeaves.add(i);
        }

        // 1. find the multiple of the eatenLeaves
        for (int i = 0; i < array.length; i++) {

            eatenLeaves.add(array[i]);

            for (int j = 1; j < uneatenLeaves.size(); j++) {

                if (array[i] * uneatenLeaves.get(j) <= n) {
                    eatenLeaves.add(array[i] * uneatenLeaves.get(j));
                }
            }
        }

        for (int i = 0; i < eatenLeaves.size(); i++) {

            for (int j = 1; j < uneatenLeaves.size(); j++) {
                if (eatenLeaves.get(i) == uneatenLeaves.get(j)) {
                    uneatenLeaves.remove(uneatenLeaves.get(j));
                }
            }
        }

        System.out.println(uneatenLeaves.size());
        return uneatenLeaves.size();
    }


    /*
     * solution - d
     * */
    public static int solution3(int N, int[] A) {

        if (N <= 0)
            return 0;
        if (A == null || A.length == 0)
            return 0;

        int length = A.length;
        Set<Integer> eaten = new HashSet<Integer>();
        Arrays.sort(A);

        for (int i = 0; i < length; i++) {

            if (A[i] == 1)
                return 0;

            else if (eaten.contains(A[i]))
                continue;

            else
                eatingLeaves(eaten, A[i], N);
        }
        return (N - eaten.size());
    }

    public static void eatingLeaves(Set<Integer> eaten, int catter, int numberOfLeaves) {

        if (catter <= 0)
            return;

        int nextLeave = catter;

        while (nextLeave <= numberOfLeaves) {

            if (!eaten.contains(nextLeave))
                eaten.add(nextLeave);

            nextLeave += catter;
        }
    }
}
