package com.codility.L6_Sorting;

/*
* We draw N discs on A plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].

We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).

The figure below shows discs drawn for N = 6 and A as follows:

  A[0] = 1
  A[1] = 5
  A[2] = 2
  A[3] = 1
  A[4] = 4
  A[5] = 0


There are eleven (unordered) pairs of discs that intersect, namely:

discs 1 and 4 intersect, and both intersect with all the other discs;
disc 2 also intersects with discs 0 and 3.
Write A function:

class Solution { public int solution(int[] A); }

that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.

Given array A shown above, the function should return 11, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [0..2,147,483,647].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.math.BigInteger;
import java.util.*;

/**
 * Created by Chaklader on 6/24/18.
 */


/*

a. After prefix sum, The sum[i] stores the number of discs with the rightmost point within 0 to i

(inclusive). If i = N-1, then it stores disc count of within 0 to i or higher (inclusive)



b. In the last loop, left is the value of the leftmost point for the disc and the sum[left -1] is

the count for discs have rightmost point within 0 to (left-1). So, for that particular disc,

there is no possibility for intersection with those discs and we need to deduct the count from

the maximum possible intersection.




Our intention is to find for a particular disc, the number of discs it doesn't intersect. For a

particular disc with j-th index, the leftmost point would be j - A[j] and for all the discs with

the center of i (variable), it wont intersect if i+ A[i] < j - A[j] suffices.



In terms of prefix array, if j is the leftmost point of a disc, it doesn't intersect with a total

of sum[j-1] discs.




If disc x0 doesn't intersect with the discs y0, y1, y2, it has the count affect if the disc y1 doesn't

interest with the discs of x0, x5 and x10. In every way, the count will be the same. We only need to

make sure we don't perform the double count.



If the leftmost is lesser then 0, we set it as 0 and if the rightmost point of the discs is greater then

N-1, we set it as N-1. because, if the leftmost point is lesser than 0, to be interpretable, the rightmost

point of the same discs will be in => 0 (= in case of a point). So at the time when comparing for discs for

not to be intractable, this discs will be considered. Similar logic applies when we set the rightmost point

as N-1 when its > N-1
* */


/*
*
After prefix sum, The sum[i] stores the number of discs with the rightmost point within 0 to i (inclusive). If i = N-1, then it stores disc count of within 0 to i or higher (inclusive)

In the last loop, left is the value of the leftmost point for the disc and the sum[left -1] is the count for discs have rightmost point within 0 to (left-1). So, for that particular disc, there is no possibility for intersection with those discs and we need to deduct the count from the maximum possible intersection.

Our intention is to find for a particular disc, the number of discs it doesn't intersect. For a particular disc with j-th index, the leftmost point would be j - A[j] and for all the discs with the center of i (variable), it wont intersect if i+ A[i] < j - A[j] suffices.

In terms of prefix array, if j is the leftmost point of a disc, it doesn't intersect with a total of sum[j-1] discs.

If the leftmost is lesser then 0, we set it as 0 and if the rightmost point of the discs is greater then N-1, we set it as N-1. because, if the leftmost point is lesser than 0, to be intersectable, the rightmost point of the same discs will be in => 0 (= in case of a point). So at the time when comparing for discs for not to be intractable, this discs will be considered. Similar logic applies when we set the rightmost point as N-1 when its > N-1
* */
public class NumberOfDiscIntersections {


    /*
     * solution - a
     * */

    /*
     * if a disc has leftmost point <= 0, their rightmost point is > 0. We
     * consider other discs leftmost to compare the the rightmost points of
     * these discs
     * */
    public static int solution(int[] A) {


        int N = A.length;
        int[] sum = new int[N];


        for (int i = 0; i < N; i++) {

            int right = (A[i] + i) <= (N - 1) ? (i + A[i]) : N-1;
            sum[right]++;
        }

        /*
         * the count of the rightmost ends discs in the range of 0 to i (inclusive)
         * */
        for (int i = 1; i < N; i++) {
            sum[i] += sum[i - 1];
        }

        /*
         * nCr = n! / r! * (n - r)!
         * 
         * from N discs, if we pick 2 discs the maximum possible
         * number of intersection will be combination of C(N,R)
         * */

        // int result = N * (N - 1) / 2;
        int result = combination(N, 2);


        for (int j = 0; j < N; j++) {

            int left = (j - A[j] <= 0)? 0 : (j - A[j]);

            /*
                sum[left - 1] is the total number of the disks that has the right most 
                point <= (left-1) and hence, wont intersect with the disks that has 
                the left most point equal to "left"
            */
            if (left > 0) {
                result -= sum[left - 1];//.
            }
        }

        if (result > 10000000) {
            return -1;
        }

        return result;
    }

    /*
     * find the combination of C(N,R) = N!/(R!* (N−R)!)
     * */
    public static int combination(int N, int R) {

        BigInteger ret = BigInteger.ONE;

        for (int k = 0; k < R; k++) {

            ret = ret.multiply(BigInteger.valueOf(N - k))
                    .divide(BigInteger.valueOf(k + 1));
        }

        return ret.intValue();
    }


    /*
     * function to multiply two numbers using bitwise operation
     * */
    public static int multiplyTwoNumbers(int a, int b) {

        int res = 0;

        /*
         * while second number doesn't become 1
         * */
        while (b > 0) {

            /*
             * if second number becomes odd, add the first number to result
             * */
            if ((b & 1) != 0)
                res = res + a;

            /*
             * double the first number and halve the second number
             * */
            a = a << 1;
            b = b >> 1;
        }

        return res;
    }

    /*
     * Compute the number of intersections in a sequence of discs.
     * The J-th disc is drawn with its center at (J, 0) and radius
     * A[J]
     *
     * PREMISE
     * -------
     * if (i + A[i]) > (j - A[j]) where j > i, the intersection occurs.
     *
     * The disks have centers of range [0, 1, 2, 3, ......., N-1]
     * */

    /*
     * for j > i and max value of center is N-1, we have intersection when the right
     * most point of i-th circle is greater than or eqaul to left most point of j-th
     * circle
     * */

    /*
     * solution - b
     * */
    public static int solution1(int[] A) {

        int count = 0;

        for (int i = 0; i < A.length - 1; i++) {

            for (int j = i + 1; j < A.length; j++) {

                if (A[i] + i >= j - A[j]) {

                    count++;

                    if (count > 10000000) {
                        return -1;
                    }
                }
            }
        }

        return count;
    }


    /*
     * solution - c
     * */
    public int solution2(int[] A) {

        int N = A.length;

        int[] lefts = new int[N];
        int[] rights = new int[N];

        for (int i = 0; i < N; i++) {

            lefts[i] = i - A[i];
            rights[i] = i + A[i];
        }

        Arrays.sort(lefts);
        Arrays.sort(rights);

        int[] lm = new int[lefts.length];
        int v = 0;

        for (int i = lefts.length - 2; i >= 0; i--) {

            if (lefts[i] != lefts[i + 1]) {
                v = lefts.length - i - 1;
            }

            lm[i] = v;
        }

        v = 0;
        int[] rl = new int[rights.length];

        for (int i = 1; i < rights.length; i++) {

            if (rights[i - 1] != rights[i]) {
                v = i;
            }

            rl[i] = v;
        }

        int c = 0;

        for (int i = 0; i < N; i++) {

            int ar = i + A[i];

            int idx = Arrays.binarySearch(lefts, ar);
            int e;

            if (idx < 0) {
                idx = -1 - idx;
                e = A.length - idx;
            } else {
                e = lm[idx];
            }

            int al = i - A[i];

            idx = Arrays.binarySearch(rights, al);

            if (idx < 0) {
                idx = -1 - idx;
                e += idx;
            } else {
                e += rl[idx];
            }

            c = c + (A.length - e - 1);

            if (c > 20000000) {
                return -1;
            }
        }

        return c / 2;
    }


    /*
     * solution - d
     * */
    private static class Slice {

        int start;
        int end;

        public Slice(int start, int end) {

            this.start = start;
            this.end = end;
        }
    }


    public static int solution3(int[] A) {

        Slice[] slices = initSlices(A);
        return getIntersectionCount(slices);
    }

    public static int getIntersectionCount(Slice[] slices) {

        /*
         * sort the slices by end points
         * */
        Arrays.sort(slices, new Comparator<Slice>() {

            @Override
            public int compare(Slice o1, Slice o2) {

                if (o1.end < o2.end) {
                    return 1;
                } else if (o2.end == o1.end) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        int count = 0;

        for (int i = 0; i < slices.length; i++) {

            Slice currentSlice = slices[i];
            int j = i + 1;

            while (j < slices.length) {

                if (currentSlice.start <= slices[j].end) {

                    count++;

                    if (count > 1e7) {
                        return -1;
                    }
                } else {
                    break;
                }

                j++;
            }
        }

        return count;
    }


    /*
     * solution - e
     * */
    public static int solution4(int[] A) {

        Slice[] slices = initSlices(A);
        int counter = 0;

        for (int i = 0; i < slices.length; i++) {

            for (int j = i + 1; j < slices.length; j++) {
                if (isIntersect(slices[i], slices[j])) {
                    counter++;
                }
            }
        }

        return counter;
    }

    public static boolean isIntersect(Slice a, Slice b) {

        return b.start <= a.end && b.end >= a.end
                || a.start <= b.end && a.end >= b.end;
    }

    public static Slice[] initSlices(int[] A) {

        Slice[] slices = new Slice[A.length];

        for (int i = 0; i < A.length; i++) {
            int end = i + A[i];
            int start = i - A[i];
            slices[i] = new Slice(start, end);
        }

        return slices;
    }


    public static void main(String[] args) {

        int[] A = new int[6];

        A[0] = 1;
        A[1] = 5;
        A[2] = 2;
        A[3] = 1;
        A[4] = 4;
        A[5] = 0;

        System.out.println(solution(A));
    }
}


class Main {

    public static void test() {

        List<Marker> l = new ArrayList<Marker>();

        l.add(new Marker(0, true));
        l.add(new Marker(10, false));

        l.add(new Marker(5, true));
        l.add(new Marker(20, false));

        l.add(new Marker(2, true));
        l.add(new Marker(30, false));

        Collections.sort(l);
        int total = -1, overlaps = 0;


        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).green) {
                total++;
                if (total > 0) overlaps += total;
            } else {
                total--;
            }
        }

        System.out.println(overlaps);
    }
}

class Marker implements Comparable<Marker> {

    int n;
    boolean green;

    public Marker(int a, boolean b) {
        n = a;
        green = b;
    }

    public int compareTo(Marker other) {
        return n < other.n ? -1 : (n > other.n ? 1 : (green ? -1 : 1));
    }
}



