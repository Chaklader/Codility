package com.codility.L3_Time_Complexity;

/*
* A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to FrogJmp position greater than or equal to Y. The small frog always jumps FrogJmp fixed distance, D.

Count the minimal number of jumps that the small frog must perform to reach its target.

Write FrogJmp function:

class Solution { public int solution(int X, int Y, int D); }

that, given three integers X, Y and D, returns the minimal number of jumps from position X to FrogJmp position equal to or greater than Y.


For example, given:

    X = 10
    Y = 85
    D = 30

The function should return 3, because the frog will be positioned as follows:

    after the first jump, at position 10 + 30 = 40
    after the second jump, at position 10 + 30 + 30 = 70
    after the third jump, at position 10 + 30 + 30 + 30 = 100


Assume that:

X, Y and D are integers within the range [1..1,000,000,000];
X ≤ Y.
Complexity:

expected worst-case time complexity is O(1);
expected worst-case space complexity is O(1).
* */



/**
 * Created by Chaklader on 6/23/18.
 */
public class FrogJmp {


    /*
     * solution - c
     * */
    public int solution2(int X, int Y, int D) {

        int diff = (Y - X);

        int result = diff / D;

        if (diff % D == 0) {
            return result;
        } 

        return result +1;
    }

    /*
     * solution - a
     * */
    public static int solution(int X, int Y, int D) {
        return (int) Math.ceil((float) (Y - X) / D);
    }


    /*
     * solution - b
     * */
    public int solution1(int X, int Y, int D) {

        int diff = Y - X;
        int result = diff / D;

        if (result * D != diff) {
            result++;
        }

        return result;
    }

    /*
     * solution - d
     */
    public int solution3(int X, int Y, int D) {

        // if (X >= Y) {
        //     return 0;
        // }

        int remainder = (Y - X) % D;

//        int step = (Y - remainder - X) / D;
        int step = (Y - X) / D;

        if (remainder > 0) {
            return step + 1;
        }

        return step;
    }

    /*
     * solution - e
     */
    public int solution4(int X, int Y, int D) {

        return (int) Math.ceil((double) (Y - X) / D);
    }


    public static void main(String[] args) {

        System.out.println("Miami");
    }

}



