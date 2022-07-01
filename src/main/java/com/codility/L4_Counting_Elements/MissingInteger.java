package com.codility.L4_Counting_Elements;

/*
* This is A demo task.

Write A function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Chaklader on 6/23/18.
 */
public class MissingInteger {



    /*
     * solution - a
     * */
    public static int solution(int[] A) {

        int N = A.length;        
        boolean[] counter = new boolean[N + 1];

        int M = counter.length;


        for (int a : A) {

            if (a > 0 && a <= N) {

                int j = a - 1;
                counter[j] = true;
            }
        }

        for (int i = 0; i < counter.length; i++) {

            if (!counter[i]) {
                return i + 1;
            }
        }

        return N + 1;
    }


    /*
     * solution - d
     * */
    // 100% accuracy for the Codility 
    public int solution3(int[] A) {

        int[] C = new int[A.length];
        int N = A.length;

        for (int i = 0; i < A.length; i++) {

            if (A[i] > 0 && A[i] <= N) {
                C[A[i] - 1] = A[i];
            }            
        }

        for (int i = 0; i < C.length; i++) {

            if (C[i] == 0) {
                return i + 1;
            }
        }

        return A.length + 1;
    }



    /*
     * solution - c
     * */
    public static int solution2(int[] A) {


        Set<Integer> set = new TreeSet<>();

        for (int a : A) {
            set.add(a);
        }

        int N = set.size();

        int[] C = new int[N];

        int index = 0;

        for (int a : set) {

            if(a > 0 && a <= N){

                C[index++] = 0;
                continue;
            }

            C[index++] = a; 
        }

        // for (int i = 0; i < N; i++) {

        //     if (C[i] > 0 && C[i] <= N) {
        //         C[i] = 0;
        //     }
        // }

        for (int i = 0; i < N; i++) {

            if (C[i] != 0) {
                return (i + 1);
            }
        }

        return (N + 1);
    }
 
    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int N = A.length;

        if (N == 0) {
            return 1;
        }


        List<Integer> list = IntStream.of(A).boxed()
                .filter(x -> x > 0).sorted().distinct()
                .collect(Collectors.toCollection(ArrayList<Integer>::new));

        int M = list.size();


        for (int i = 0; i < M; i++) {

            if (list.get(i) != i + 1) {
                return i + 1;
            }
        }

        /*
         * all the numbers are in sequence
         * */
        return M + 1;
    }


    /*
     * solution - e
     * */

    /*
     * if A = [-1,2] the solution breaks
     * */
    public static int solution4(int[] A) {

        int N = A.length;

        /*
         * if A = [-1,2] the solution breaks
         * */
        for (int i = 0; i < N; i++) {

            if (A[i] < 0) {
                A[i] = 0;
            }
        }

        /*
         * Mark A[i] as visited by making A[A[i] - 1] negative
         * */
        for (int i = 0; i < N; i++) {

            /*
             * we need the absolute value for the duplicates
             * */
            int j = Math.abs(A[i]) - 1;

            if (j >= 0 && j < N && A[j] > 0) {
                A[j] = -A[j];
            }
        }

        for (int i = 0; i < N; i++) {

            if (A[i] > 0) {
                return i + 1;
            }
        }

        return N + 1;
    }



    /*
     * solution - f
     * */
    public static int solution5(int[] A) {

        Set<Integer> set = new HashSet<>();
        int N = A.length;

        for (int a : A) {

            if (a > 0 && a <= N) {
                set.add(a);
            }
        }

        for (int i = 0; i < N; i++) {

            if (!set.contains(i + 1)) {
                return i + 1;
            }
        }

        return (N + 1);
    }


    /*
     * solution - g
     * */
    public static int solution6(int[] A) {

        int N = A.length;
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < N; i++) {

            if (A[i] > 0 && A[i] <= N) {
                set.add(A[i]);
            }
        }

        int index = 1;

        for (int a : set) {

            if (a > index) {
                return index;
            }

            index++;
        }

        return (N + 1);
    }


    /*
     * solution - h
     * */
    public static int solution7(int[] A) {


        int N = A.length;

        for (int i = 0; i < N; i++) {

            if (A[i] <= 0 || A[i] > N) {
                A[i] = 0;
            }
        }

        for (int i = 0; i < N; i++) {

            int prev = A[i];

            while (prev > 0 && A[prev - 1] != prev) {

                int next = A[prev - 1];

                A[prev - 1] = prev;
                prev = next;
            }
        }

        /*
         * the first unmapped position is the smallest element
         * */
        for (int i = 0; i < N; i++) {

            if (A[i] != i + 1) {
                return i + 1;
            }
        }

        return N + 1;
    }



    /*
     * solution - i
     * */
    /*
     * if A = [-1,2] the solution works fine
     * */
    public static int solution8(int[] A) {

        int N = A.length;
        int[] C = new int[N];

        /*
         * mark A[i] as visited by making A[A[i] - 1] negative
         * */
        for (int i = 0; i < N; i++) {

            /*
             * we need the absolute value for the duplicates
             * */
            int j = Math.abs(A[i]) - 1;

            if (j >= 0 && j < N && A[j] > 0) {
                C[j] = -A[j];
            }

        }


        for (int i = 0; i < N; i++) {

            if (C[i] == 0) {
                return i + 1;
            }
        }

        return N + 1;
    }




    /*
    * solution - j
    */
    public int solution9(int[] A) {

        Arrays.sort(A);
        return next(1, A);
    }

    public int next(int b, int[] A) {

        for (int a : A){

            if (a<=0){
                continue;
            }

            if (b==a){
                return next(++b, A);
            }
        }

        return b;
    }

    /*

    There is indeed an O(n) complexity solution to this problem even if duplicate ints are involved in the input:

    solution(A)
    Filter out non-positive values from A
    For each int in filtered
        Let a zero-based index be the absolute value of the int - 1
        If the filtered range can be accessed by that index  and  filtered[index] is not negative
            Make the value in filtered[index] negative

    For each index in filtered
        if filtered[index] is positive
            return the index + 1 (to one-based)

    If none of the elements in filtered is positive
        return the length of filtered + 1 (to one-based)

    So an array A = [1, 2, 3, 5, 6], would have the following transformations:

    abs(A[0]) = 1, to_0idx = 0, A[0] = 1, make_negative(A[0]), A = [-1,  2,  3,  5,  6]
    abs(A[1]) = 2, to_0idx = 1, A[1] = 2, make_negative(A[1]), A = [-1, -2,  3,  5,  6]
    abs(A[2]) = 3, to_0idx = 2, A[2] = 3, make_negative(A[2]), A = [-1, -2, -3,  5,  6]
    abs(A[3]) = 5, to_0idx = 4, A[4] = 6, make_negative(A[4]), A = [-1, -2, -3,  5, -6]
    abs(A[4]) = 6, to_0idx = 5, A[5] is inaccessible,          A = [-1, -2, -3,  5, -6]

    A linear search for the first positive value returns an index of 3. Converting back to a one-based index results in solution(A)=3+1=4
    Here's an implementation of the suggested algorithm in C# (should be trivial to convert it over to Java lingo - cut me some slack common):

    # in C#
    public int solution(int[] A){
        var positivesOnlySet = A
            .Where(x => x > 0)
            .ToArray();

        if (!positivesOnlySet.Any())
            return 1;

        var totalCount = positivesOnlySet.Length;
        for (var i = 0; i < totalCount; i++) //O(n) complexity
        {
            var abs = Math.Abs(positivesOnlySet[i]) - 1;
            if (abs < totalCount && positivesOnlySet[abs] > 0) //notice the greater than zero check 
                positivesOnlySet[abs] = -positivesOnlySet[abs];
        }

        for (var i = 0; i < totalCount; i++) //O(n) complexity
        {
            if (positivesOnlySet[i] > 0)
                return i + 1;
        }

        return totalCount + 1;
    }
    */


    public static void main(String[] args) {

        // this would return 1 as this is the missign smallest positive integer
        int[] A = {3, 0, 14, 78, 90};

    }

}