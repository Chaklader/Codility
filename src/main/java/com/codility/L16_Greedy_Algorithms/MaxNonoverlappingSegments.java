package com.codility.L16_Greedy_Algorithms;

/*
* Located on A line are N segments, numbered from 0 to N − 1, whose positions are given in arrays A and B. For each I (0 ≤ I < N) the position of segment I is from A[I] to B[I] (inclusive). The segments are sorted by their ends, which means that B[K] ≤ B[K + 1] for K such that 0 ≤ K < N − 1.

Two segments I and J, such that I ≠ J, are overlapping if they share at least one common point. In other words, A[I] ≤ A[J] ≤ B[I] or A[J] ≤ A[I] ≤ B[J].

We say that the set of segments is non-overlapping if it contains no two overlapping segments. The goal is to find the size of A non-overlapping set containing the maximal number of segments.

For example, consider arrays A, B such that:

    A[0] = 1    B[0] = 5
    A[1] = 3    B[1] = 6
    A[2] = 7    B[2] = 8
    A[3] = 9    B[3] = 9
    A[4] = 9    B[4] = 10
The segments are shown in the figure below.



The size of A non-overlapping set containing A maximal number of segments is 3. For example, possible sets are {0, 2, 3}, {0, 2, 4}, {1, 2, 3} or {1, 2, 4}. There is no non-overlapping set with four segments.

Write A function:

class Solution { public int solution(int[] A, int[] B); }

that, given two arrays A and B consisting of N integers, returns the size of A non-overlapping set containing A maximal number of segments.

For example, given arrays A, B shown above, the function should return 3, as explained above.

Assume that:

N is an integer within the range [0..30,000];
each element of arrays A, B is an integer within the range [0..1,000,000,000];
A[I] ≤ B[I], for each I (0 ≤ I < N);
B[K] ≤ B[K + 1], for each K (0 ≤ K < N − 1).
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Chaklader on 6/25/18.
 */
public class MaxNonoverlappingSegments {


    /*
     * solution - a
     * */
    public static int solution(int A[], int B[]) {

        int N = A.length;

        if (N <= 1) {
            return N;
        }

        int count = 1;
        int previous = B[0];

        int i;
        boolean check = false;

        for (i = 1; i < N; i++) {

            if (A[i] > previous) {

                count++;
                previous = B[i];
            }
        }

        return count;
    }


    /*
     * solution - b
     * */
    public int solution1(int[] A, int[] B) {

        if (A.length == 0) {
            return 0;
        }

        int result = 0;
        int j = 0;

        for (int i = 1; i < A.length; i++) {

            if (A[i] > B[j]) {

                result++;
                j = i;
            }
        }

        return result + 1;
    }


    /*
     * solution - c
     * */
    class Segment {
        int start;
        int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }


    public int solution2(int[] A, int[] B) {

        ArrayList<Segment> segments = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            segments.add(new Segment(A[i], B[i]));
        }

        Collections.sort(segments, (o1, o2) -> o1.getEnd() - o2.getEnd());

        if (segments.size() <= 0) {
            return 0;
        }

        Segment current = segments.get(0);
        int counter = 1;

        for (int i = 0; i < segments.size(); i++) {
            if (current.getEnd() < segments.get(i).getStart()) {
                current = segments.get(i);
                counter++;
            }
        }

        return counter;
    }

    public static void main(String[] args) {


        int[] A = new int[2];
        int[] B = new int[2];

        A[0] = 1;
        B[0] = 5;

        A[1] = 3;
        B[1] = 6;

        System.out.println(solution(A, B));


//        A[2] = 7;
//        B[2] = 8;
//        A[3] = 9;
//        B[3] = 9;
//        A[4] = 9;
//        B[4] = 10;
    }
}
