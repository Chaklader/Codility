package com.codility.L10_Prime_Composite_Num;

/*
* A non-empty array A consisting of N integers is given.

A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].

For example, the following array A:

    A[0] = 1
    A[1] = 5
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2


has exactly four peaks: elements 1, 3, 5 and 10.

You are going on A trip to A range of mountains whose relative heights are represented by array A, as shown in A figure below. You have to choose how many flags you should take with you. The goal is to set the maximum number of flags on the peaks, according to certain rules.



Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.

For example, given the mountain range represented by array A, above, with N = 12, if you take:

two flags, you can set them on peaks 1 and 5;
three flags, you can set them on peaks 1, 5 and 10;
four flags, you can set only three flags, on peaks 1, 5 and 10.
You can therefore set A maximum of three flags in this case.

Write A function:

class Solution { public int solution(int[] A); }

that, given A non-empty array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.

For example, the following array A:

    A[0] = 1
    A[1] = 5
    A[2] = 3
    A[3] = 4
    A[4] = 3
    A[5] = 4
    A[6] = 1
    A[7] = 2
    A[8] = 3
    A[9] = 4
    A[10] = 6
    A[11] = 2

the function should return 3, as explained above.

Assume that:

N is an integer within the range [1..400,000];
each element of array A is an integer within the range [0..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.*;

/**
 * Created by Chaklader on 6/25/18.
 */
public class Flags {


    /*
     * Flags can only be set on peaks. What's more, if you take K flags, then the
     * distance between any two flags should be greater than or equal to K. The
     * distance between indices P and Q is the absolute value |P − Q|.
     *
     * Given A non-empty array A of N integers, returns the maximum number of flags
     * that can be set on the peaks of the array.
     * */

    public static int solution(int[] A) {


        int N = A.length;
        List<Integer> flags = new ArrayList<>();

        for (int i = 1; i < (N - 1); i++) {

            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                flags.add(i);
            }
        }

        for (int i = flags.size(); i >= 1; i--) {

            int k = flags.get(0);
            int count = 1;

            for (int j = 1; j < flags.size(); j++) {

                if ((flags.get(j) - k) / i >= 1) {

                    k = flags.get(j);
                    count++;

                    if (count == i) {
                        return count;
                    }
                }
            }
        }

        return -1;
    }






    /*
     * solution - a
     */
    public static int solution(int[] A) {


        List<Integer> flags = new ArrayList<Integer>();

        for (int i = 1; i < A.length - 1; i++) {

            if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
                flags.add(i);
            }
        }

        int N = flags.size();

        if (N == 0 || N == 1) {
            return N;
        }

        int low = 1;
        int high = N;

        int result = 1;

        /*
         * we can have flags between low to high
         * */
        while (low <= high) {

            /*
             * we try with K flags so the distance between the flags will be >= K
             * */
            int K = (low + high) / 2;

            int count = 0;
            int marked = flags.get(0);

            for (int i = 0; i < N; i++) {

                if (flags.get(i) >= marked) {

                    count++;
                    marked = flags.get(i) + K;

                    if (count == K) {
                        break;
                    }
                }
            }

            /*
             * this is search for maximizing the k
             * */
            if (count == K) {
                result = count;
                low = K + 1;
            } 

            // 
            else {
                high = K - 1;
            }
        }

        return result;
    }




    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int N = A.length;

        /*
         * P =  [1, 1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1]
         * */
        int[] P = nextPeak(A);

        int i = 1;
        int result = 0;


        /*
         * To set i Flags with a distance of i than the total distance
         * would be [i * (i-1)]
         * */
        while (i * (i - 1) <= N) {

            int j = 0;
            int flags = 0;

            while (j < N && flags < i) {

                /*
                 * P =  [1, 1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1]
                 * */
                j = P[j];

                if (j == -1) {
                    break;
                }

                flags += 1;
                j += i;
            }

            /*
             * maximize the number of flags for the whole segment
             * */
            result = Math.max(result, flags);
            i++;
        }

        return result;
    }


    /*
     * A = [1, 1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1]
     * */
    public static int[] nextPeak(int[] P) {

        int N = P.length;

        List<Integer> peaks = new ArrayList<Integer>();

        for (int i = 1; i < P.length - 1; i++) {

            if (P[i] > P[i - 1] && P[i] > P[i + 1]) {
                peaks.add(i);
            }
        }

        int[] A = new int[N];
        A[N - 1] = -1;

        /*
         * array has 4 peaks, [1, 3, 5, 10]
         * */
        for (int i = N - 2; i >= 0; i--) {

            if (peaks.contains(i)) {
                A[i] = i;
            } else {
                A[i] = A[i + 1];
            }
        }

        return A;
    }


    /*
     * solution - c
     */
    public static int solution2(int[] A) {

        int N = A.length;

        List<Integer> peaks = getPeaks(A, N);

        int P = peaks.size();

        int i = 2;
        int result = 0;

        while (i * (i - 1) < N) {

//            if (peaks.get(P - 1) - peaks.get(0) < i) {
//                i++;
//                continue;
//            }

            int flags = 0;

            int marked = peaks.get(0);

            /*
             * P = [1, 3, 5, 10]
             * */
            for (int j = 0; j < P; j++) {

                if (peaks.get(j) >= marked) {

                    flags++;
                    marked = peaks.get(j) + i;

                    if (flags == i) {
                        break;
                    }
                }
            }

            result = Math.max(flags, result);
            i++;
        }

        return result;
    }

    private static List<Integer> getPeaks(int[] A, int N) {

        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < N - 1; i++) {

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i++);
            }
        }

        return peaks;
    }


    /*
     * solution - d
     */
    public static int solution3(int[] A) {

        int N = A.length;

        List<Integer> peaks = peaks(A, N);

        int P = peaks.size();

        int i = 2;

        int result = 0;

        while (i * (i - 1) < N) {

            if (peaks.get(P - 1) - peaks.get(0) < i) {
                i++;
                continue;
            }

            int flags = 0;
            int k = 0;

            while (k < P - 1) {

                int x = k + 1;

                while (x < P - 1 && peaks.get(x) - peaks.get(k) < i) {
                    x++;
                }

                if (x > P - 1) {
                    break;
                }

                k = x;
                flags++;
            }

            flags++;

            if (i <= flags) {
                flags = i;
            }

            result = Math.max(flags, result);
            i++;
        }

        return result;
    }


    private static List<Integer> peaks(int[] A, int N) {

        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < N - 1; i++) {

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i++);
            }
        }

        return peaks;
    }


    /*
     * solution - e
     */
    public int solution4(int[] A) {

        if (A.length < 3) {
            return 0;
        }

        int[] nexts = new int[A.length];
        int next = A.length;

        nexts[A.length - 1] = A.length;
        int peaks = 0;

        for (int i = A.length - 2; i >= 1; i--) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                next = i;
                peaks++;
            }
            nexts[i] = next;
        }

        nexts[0] = next;
        if (peaks == 0) {
            return 0;
        }

        int result = 1;
        int start = nexts[0];

        int p = 1;
        int maxp = 1;

        while (maxp * maxp <= A.length) {
            maxp++;
        }

        /*
         * there might be A situation when the peaks are packed, for
         * intstance 010001000100010. sqrt(n) = 3, but the answer is 4
         * */
        if (maxp * maxp != A.length) {
            maxp++;
        }

        /*
         * cant be no more than total peaks
         * */
        if (peaks < maxp) {
            maxp = peaks;
        }

        /*
         * outer loop is O(sqrt(n)) and each inner loop is performed no
         * more than p which is no more than O(sqrt(n)) it gives total
         * O(n) complexity
         * */
        while (p <= maxp) {

            int setFlags = 0;

            for (int j = start; setFlags < p && j < A.length; j = (j + p < nexts.length ? nexts[j + p] : A.length)) {
                setFlags++;
            }

            if (result < setFlags) {
                result = setFlags;
            }

            p++;
        }

        return result;
    }


    /*
     * solution - f
     */
    public int solution5(int[] A) {

        ArrayList<Integer> peaks = new ArrayList<>();
        if (A.length <= 2) {
            return 0;
        }
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }
        if (peaks.size() == 1) {
            return 1;
        }
        if (peaks.size() == 0) {
            return 0;
        }

        int answer = 0;
        // for one peak we need minimum 3 points
        int to = A.length / 3;

        for (int i = 2; i < to; i++) {

            int current = 0;
            int flagsCount = 0;

            while (current != -1 && flagsCount != i) {
                flagsCount++;
                current = getNextPeak(current, peaks, i);
            }
            if (answer > flagsCount) {
                break;
            }
            answer = flagsCount;
        }
        return answer;
    }

    //binary search for next peak
    private int getNextPeak(int currentPeakIndex, ArrayList<Integer> peaks, int step) {

        int low = currentPeakIndex;
        int high = peaks.size() - 1;

        int key = peaks.get(currentPeakIndex) + step;
        int mid = -1;

        while (low <= high) {

            mid = low + (high - low) / 2;

            if (key < peaks.get(mid)) {
                high = mid - 1;
            } else if (key > peaks.get(mid)) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        for (int i = mid; i < peaks.size(); i++) {

            if (peaks.get(i) >= key) {
                return i;
            }
        }

        return -1;
    }


    /*
     *  ALGORITHM
     *  ---------
     *
     *  i.   start with the maximum flag and gradually decrement to 1. The condition
     *       is distance >= flags if we need to maximize ie distance = flags
     *
     *  ii.  as we set the flag number, use that as distance too to count the number of flags
     *
     *  iii. as we decrement the values, if we reached the condition flags = assumption, return
     *       assumption as an answer
     * */
    /*
     * solution - g
     * */
    public static int solution6(int[] A) {

        int N = A.length;

        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < N - 1; i++) {

            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }

        int F = peaks.size();

        for (int i = F; i >= 1; i--) {


            int marked = peaks.get(0);
            int count = 0;

            for (int j = 0; j < F; j++) {

                if (peaks.get(j) >= marked) {

                    marked = peaks.get(j) + i;
                    count++;

                    // not necessary as the max value of count can be equal to the flags (ie peaks)
//                    if(count == i){
//                        break;
//                    }
                }
            }

            if (i == count) {
                return count;
            }
        }

        return 0;
    }


    public static void main(String[] args) {

        int[] A = new int[12];

        A[0] = 1;
        A[1] = 5;
        A[2] = 3;
        A[3] = 4;
        A[4] = 3;
        A[5] = 4;
        A[6] = 1;
        A[7] = 2;
        A[8] = 3;
        A[9] = 4;


        A[10] = 6;
        A[11] = 2;


//        System.out.println(9/2);
        System.out.println(solution(A));

//        int i = 1;
//        while (i <= 12) {
//            System.out.println(i);
//
//            i++;
//        }


//        while ((i-1) * i <= 12) {
//            System.out.println(i);
//
//            i++;
//        }


//        while ((i) * i <= 12) {
//            System.out.println(i);
//            i++;
//        }
    }
}
