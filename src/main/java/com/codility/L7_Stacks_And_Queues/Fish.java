package com.codility.L7_Stacks_And_Queues;

/*
* You are given two non-empty arrays A and B consisting of N integers. Arrays A and B represent N voracious fish in A river, ordered downstream along the flow of the river.

The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially upstream of fish Q. Initially, each fish has A unique position.

Fish number P is represented by A[P] and B[P]. Array A contains the sizes of the fish. All its elements are unique. Array B contains the directions of the fish. It contains only 0s and/or 1s, where:

0 represents A fish flowing upstream,
1 represents A fish flowing downstream.
If two fish move in opposite directions and there are no other (living) fish between them, they will eventually meet each other. Then only one fish can stay alive − the larger fish eats the smaller one. More precisely, we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them. After they meet:

If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.
We assume that all the fish are flowing at the same speed. That is, fish moving in the same direction never meet. The goal is to calculate the number of fish that will stay alive.

For example, consider arrays A and B such that:

  A[0] = 4    B[0] = 0
  A[1] = 3    B[1] = 1
  A[2] = 2    B[2] = 0
  A[3] = 1    B[3] = 0
  A[4] = 5    B[4] = 0

Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish number 4 and is eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay alive.

Write A function:

class Solution { public int solution(int[] A, int[] B); }

that, given two non-empty arrays A and B consisting of N integers, returns the number of fish that will stay alive.

For example, given the arrays shown above, the function should return 2, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [0..1,000,000,000];
each element of array B is an integer that can have one of the following values: 0, 1;
the elements of A are all distinct.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Stack;


/**
 * Created by Chaklader on 6/24/18.
 */
public class Fish {



    /*
     * solution - a
     * */
    public static int solution(int[] A, int[] B) {

        Stack<Integer> stack = new Stack<Integer>();

        int deadFish = 0;
        int totalFish = A.length;

        /*
            A[0] = 4    B[0] = 0
            A[1] = 3    B[1] = 1
            A[2] = 2    B[2] = 0
            A[3] = 1    B[3] = 0
            A[4] = 5    B[4] = 0
         */

        // 0 - Upstream
        // 1 - Downstream

        for (int i = 0; i < totalFish; i++) {

            if (B[i] == 0) {

                while (!stack.isEmpty()) {

                    deadFish++;
                    if (A[i] < stack.peek()) {
                        break;
                    }
                    
                    stack.pop();
                }

                continue;
            }

            stack.push(A[i]); 
        }

        int aliveFish =  totalFish - deadFish;
        return aliveFish;
    }


    /*
     * solution - b
     */
    public int solution1(int[] A, int[] B) {


        int upstreamFish = 0;
        int total = A.length;

        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < total; i++) {

            /*
             * we have a upstream fish
             * */
            if (B[i] == 0) {

                /*
                 * upstream fish is eating the downstream fish
                 * */
                while (!stack.isEmpty() && A[i] > stack.peek()) {
                    stack.pop();
                }

                if (stack.isEmpty()) {
                    upstreamFish++;
                }
            }

            /*
             * we have a downstream fish
             * */
            else {
                stack.push(A[i]);
            }
        }

        int numOfDownstreamFish = stack.size();
        return upstreamFish + numOfDownstreamFish;
    }




    /*
     * solution - c
     */
    public int solution2(int[] A, int[] B) {


        int N = A.length;
        int lenOfElements = 0;

        int[] storage = new int[A.length];

        for (int i = 0; i < A.length; i++) {

            if (B[i] == 1) {
                storage[lenOfElements++] = A[i];
            } 

            // 
            else {

                while (true) {

                    if (lenOfElements == 0) {
                        break;
                    } 

                    else {

                        if (storage[lenOfElements - 1] < A[i]) {
                            lenOfElements--;
                            N--;
                        } 

                        else {
                            N--;
                            break;
                        }
                    }
                }
            }
        }

        return N;
    }


    /*
     * solution - d
     */
    public int solution3(int[] A, int[] B) {


        Stack<Integer> upStream = new Stack<>();
        int survive = 0;

        for (int i = 0; i < A.length; i++) {

            if (B[i] == 0) {

                if (upStream.size() > 0) {

                    boolean eaten = false;

                    while (upStream.size() > 0 && !eaten) {

                        if (A[i] > upStream.lastElement()) {
                        
                            upStream.pop();

                            if (upStream.size() == 0) {
                                survive++;
                            }
                        } 

                        else {
                            eaten = true;
                        }
                    }
                } 

                else {
                    survive++;
                }
            } 

            else {
                upStream.push(A[i]);
            }
        }

        return upStream.size() + survive;
    }
}
