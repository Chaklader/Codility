package com.codility.L8_Leader;


/*
* A non-empty array A consisting of N integers is given.

The leader of this array is the value that occurs in more than half of the elements of A.

An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] 
and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.


For example, given array A such that:

    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2


We can find two equi leaders:

    0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
    2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.


The goal is to count the number of equi leaders.

Write A function:


class Solution { public int solution(int[] A); }

    that, given A non-empty array A consisting of N integers, returns the number of equi leaders.

    For example, given:

        A[0] = 4
        A[1] = 3
        A[2] = 4
        A[3] = 4
        A[4] = 4
        A[5] = 2

the function should return 2, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Stack;

/**
 * Created by Chaklader on 6/24/18.
 */
public class EquiLeader {

    /*
     * An equi leader is an index S such that 0 ≤ S < N − 1 and two
     * sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ...,
     * A[N − 1] have leaders of the same value. The goal is to count
     * the number of equi leaders.
     * */


    /*
     * solution - a
     */
    public static int solution(int[] A) {


        Stack<Integer> stack = new Stack<Integer>();


        for (int i = 0; i < A.length; i++) {

            if (stack.isEmpty()) {

                stack.push(A[i]);
                continue;
            }

            if (stack.peek() == A[i]) {
                stack.push(A[i]);
            } 

            // 
            else {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return 0;
        }

        int dominator = stack.peek();

        // int numOfDominator = 0;

        // for (int i = 0; i < A.length; i++) {

        //     if (A[i] == dominator) {
        //         numOfDominator++;
        //     }
        // }

        int numOfDominator = stack.size();

        if (numOfDominator <= A.length / 2) {
            return 0;
        }

        int numOfNonDominator = A.length - numOfDominator;

        stack.clear();

        int dominatorInCurrentSec = 0;
        int nonDominatorInCurrentSec = 0;

        int numOfEquiLeaders = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] == dominator) {
                dominatorInCurrentSec++;
            } 

            else {
                nonDominatorInCurrentSec++;
            }

            // a > L/2   (a + b) = L
            // a > (a/2 + b/2)

            // a/2 > b/2   and  a > b
            boolean isEquiLeader = dominatorInCurrentSec > nonDominatorInCurrentSec && (numOfDominator - dominatorInCurrentSec) > (numOfNonDominator - nonDominatorInCurrentSec);

            if (isEquiLeader) {
                numOfEquiLeaders++;
            }

        }

        return numOfEquiLeaders;
    }



    /*
     * solution - b
     */
    public int solution1(int[] A) {


        if (A.length == 1) {
            return 0;
        }

        int value = A[0];
        int size = 0;

        for (int i = 0; i < A.length; i++) {

            if (size == 0) {

                size++;
                value = A[i];

                continue;
            } 

            // else {

            //     if (A[i] == value) {
            //         size++;
            //     } 

            //     else {
            //         size--;
            //     }
            // }

            size = (A[i] == value)? ++size : --size;
        }


        int candidate = -1;
        int count = 0;

        if (size > 0) {
            candidate = value;
        }

        for (int i = 0; i < A.length; i++) {

            if (A[i] == candidate) {
                count++;
            }
        }

        if (count <= A.length / 2) {
            return 0;
        }

        int leader = candidate;

        int equiCount = 0;
        int leaderCount = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] == leader) {
                leaderCount++;
            }

            if (leaderCount > (i + 1) / 2 && (count - leaderCount) > (A.length - (i + 1)) / 2) {
                equiCount++;
            }
        }

        return equiCount;
    }


    /*
     * solution - c
     * */
    public int solution2(int[] A) {

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < A.length; i++) {

            if (stack.isEmpty()) {
                stack.push(A[i]);
            } 

            else {
            
                if (stack.lastElement() != A[i]) {
                    stack.pop();
                } else {
                    stack.push(A[i]);
                }
            }
        }

        if (stack.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] == stack.lastElement()) {
                total++;
            }
        }

        int leader = 0;

        if (total <= A.length / 2) {
            return 0;
        } else {
            leader = stack.lastElement();
        }

        int left = 0;
        int result = 0;


        for (int i = 0; i < A.length; i++) {

            if (A[i] == leader) {
                left++;
            }
            int right = total - left;
            if (left > (i + 1) / 2 && right > (A.length - i - 1) / 2) {
                result++;
            }
        }

        return result;
    }


}
