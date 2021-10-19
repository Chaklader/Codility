package com.codility.L7_Stacks_And_Queues;

/*
* You are going to build A stone wall. The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. The depth of the wall is specified by an array H of N positive integers. H[I] is the depth of the wall from I to I+1 meters to the right of its left end. In particular, H[0] is the depth of the wall's left end and H[N−1] is the depth of the wall's right end.

The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). Your task is to compute the minimum number of blocks needed to build the wall.

Write A function:

class Solution { public int solution(int[] H); }

that, given an array H of N positive integers specifying the depth of the wall, returns the minimum number of blocks needed to build it.

For example, given array H containing N = 9 integers:

  H[0] = 8    H[1] = 8    H[2] = 5
  H[3] = 7    H[4] = 9    H[5] = 8
  H[6] = 7    H[7] = 4    H[8] = 8

the function should return 7. The figure shows one possible arrangement of seven blocks.



Assume that:

N is an integer within the range [1..100,000];
each element of array H is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.Stack;

/**
 * Created by Chaklader on 6/24/18.
 */
public class StoneWall {


    /*
     * solution - a
     */
    public static int solution(int[] H) {


        Stack<Integer> stack = new Stack<Integer>();

        int count = 1;
        stack.push(H[0]);

        /*
         * ALGORITHMS:
         * ----------
         * i.   Set block count = 1
         *
         * ii.  If the depth is same as previous block, keep going
         *
         * iii. If the current depth is higher, push that in the stack and increase count
         *
         * iv.  If the current depth is lower, keep poping till the current depth >= peek
         *      Afterwards, if the stack size = 0 or higher, increase the block count by 1
         *
         * */
        for (int i = 1; i < H.length; i++) {

            if (stack.peek() == H[i]) {
                continue;
            }

            if (stack.peek() < H[i]) {                
                stack.push(H[i]);
                count++;
            }

            else {

                while (!stack.isEmpty() && H[i] < stack.peek()) {
                    stack.pop();
                }

                if (!stack.isEmpty() && H[i] == stack.peek()) {
                    continue;
                }

                stack.push(H[i]);
                count++;
            }
        }

        return count;
    }


    /*
     * solution - b
     */
    public static int solution1(int[] H) {


        int count = 0;

        Stack<Integer> stack = new Stack<Integer>();


        for (int i = 0; i < H.length; i++) {

            if (stack.isEmpty()) {
                stack.push(H[i]);
                count++;
            }

            else if (stack.peek() < H[i]) {

                stack.push(H[i]);
                count++;
            }


            else if (stack.peek() > H[i]) {

                while (H[i] <= stack.peek()) {

                    if (H[i] == stack.peek()) {
                        count--;
                    }

                    stack.pop();

                    if(stack.isEmpty()){
                        break;
                    }
                }

                stack.push(H[i]);
                count++;
            }

        }

        return count;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int[] C = new int[A.length];

        int lenOfElements = 0;
        int numOfBlocks = 0;

        for (final int a : A) {

            /*
             * If depth of the current depth is lesser
             * than the higher lenOfElements element of storage,
             * reduce the lenOfElements by 1
             * */
            while (lenOfElements > 0 && C[lenOfElements - 1] > a) {

                lenOfElements--;
            }

            /*
             * Height of the current depth is higher
             * than the higher lenOfElements element of storage
             * */
            if (lenOfElements == 0 || C[lenOfElements - 1] < a) {

                numOfBlocks++;
                C[lenOfElements] = a;

                lenOfElements++;
            }

            // when A[i] == storage[lenOfElements - 1], do nothing
        }

        return numOfBlocks;
    }


    /*
     * solution -  d
     * */
    public static int solution3(int[] A) {

        int[] C = new int[A.length];

        int counter = 0;
        int result = 0;

        for (int a : A) {


            while (counter > 0 && C[counter - 1] > a) {
                counter--;
            }

            if (counter == 0 || C[counter - 1] < a) {

                C[counter] = a;

                result++;
                counter++;
            }
        }

        return result;
    }


    public static void main(String[] args) {

        int[] H = new int[9];

        H[0] = 8;
        H[1] = 8;
        H[2] = 5;
        H[3] = 7;
        H[4] = 9;
        H[5] = 8;
        H[6] = 7;
        H[7] = 4;
        H[8] = 8;

        System.out.println(solution1(H));
    }
}
