package com.codility.Interview;


/*
A positive integer N is given. The goal is to construct the shortest possible sequence of integers
ending with N, using the following rules:

the first element of the sequence is 1; more specifically: A[0] = 1,

each of the following elements is generated by multiplying the previous element by 2 or

increasing it by 1;

more precisely: A[i] = A[i−1] * 2 or A = A[i−1] + 1, for i ≥ 1.

For example, for N = 17, the shortest sequence is:

    A[0] = 1

    A[1] = 2

    A[2] = 4

    A[3] = 8

    A[4] = 16

    A[5] = 17


Write a function:

class Solution { public int solution(int N); }
    
    that, given a positive integer N, returns the length of the shortest possible
    sequence of integers satisfying the above conditions and ending with N.
    For example, given N = 17, the function should return 6, as explained above.





Assume that
-----------
N is an integer within the range [1..2,147,483,647].


Complexity
----------

    Expected worst-case time complexity is O(log(N));
    Expected worst-case space complexity is O(1).. 1po
*/



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaklader on 7/5/18.
 */
public class ShortestSequence {


    /*
     * solution - a
     * */
    public static int solution(int N) {

        int count = 0;

        while (N > 0) {
            count++;
            N = (N % 2 == 0) ? (N / 2) : (N - 1);
        }

        return count;
    }

    /*
     * solution - b
     * */
    public static int solution1(int N) {

        List<Integer> list = new ArrayList<>();
        list.add(N);

        while (true) {

            if (N == 1) {
                break;
            }

            int modulus = N % 2;

            if (modulus == 0) {
                N = N / 2;
                list.add(0, N);
            } 

            else if (modulus == 1) {
                N = N - 1;
                list.add(0, N);
            }
        }

        return list.size();
    }


    /*
     * solution - c
     * */
    public static int solution2(int N) {

        if (N == 1) {
            return 1;
        }

        int a = 1; // you call this 'temp', I call it 'a' to match the problem description
        int count = 2;

        while (true) {

            if (a * 2 <= N) {

                a *= 2;
            } else {
                a += 1;
            }

            if (a == N) {
                return count;
            }

            count++;
        }
    }
}
