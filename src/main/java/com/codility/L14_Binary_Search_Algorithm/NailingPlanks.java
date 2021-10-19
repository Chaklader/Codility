package com.codility.L14_Binary_Search_Algorithm;

/*
* You are given two non-empty arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.

Next, you are given A non-empty array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.

We say that A plank (A[K], B[K]) is nailed if there exists A nail C[I] such that A[K] ≤ C[I] ≤ B[K].

The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find A value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist A nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].

For example, given arrays A, B such that:

    A[0] = 1    B[0] = 4
    A[1] = 4    B[1] = 5
    A[2] = 5    B[2] = 9
    A[3] = 8    B[3] = 10
four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].

Given array C such that:

    C[0] = 4
    C[1] = 6
    C[2] = 7
    C[3] = 10
    C[4] = 2
if we use the following nails:

0, then planks [1, 4] and [4, 5] will both be nailed.
0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
0, 1, 2, 3, then all the planks will be nailed.
Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.

Write A function:

class Solution { public int solution(int[] A, int[] B, int[] C); }

that, given two non-empty arrays A and B consisting of N integers and A non-empty array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.

If it is not possible to nail all the planks, the function should return −1.

For example, given arrays A, B, C such that:

    A[0] = 1    B[0] = 4
    A[1] = 4    B[1] = 5
    A[2] = 5    B[2] = 9
    A[3] = 8    B[3] = 10

    C[0] = 4
    C[1] = 6
    C[2] = 7
    C[3] = 10
    C[4] = 2
the function should return 4, as explained above.

Assume that:

N and M are integers within the range [1..30,000];
each element of arrays A, B, C is an integer within the range [1..2*M];
A[K] ≤ B[K].
Complexity:

expected worst-case time complexity is O((N+M)*log(M));
expected worst-case space complexity is O(M) (not counting the storage required for input arguments).
* */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/*
* Task Score 100%
* Correctness 100%
* Performance 100%
* /


/**
 * Created by Chaklader on 6/28/18.
 */
public class NailingPlanks {


    /*
     * count the minimum number of nails that allow A series of planks
     * to be nailed. We will get the number till the last usable nails
     * for the purpose
     *
     * a plank (A[K], B[K]) is nailed if there exists A nail C[I] such that A[K] ≤ C[I] ≤ B[K].
     * The goal is to find the minimum number of nails that must be used until all the planks
     * are nailed.
     * */

    /*
     * solution - a
     */
    public static int solution(int[] A, int[] B, int[] C) {

        /*
         * A and B are also in the ascending sorted
         * order. Otherwise, do the soring please
         * */
        int P = A.length;
        int N = C.length;

        int[][] sortedNails = new int[N][2];


		/*
            |------------------|
            |    Initial       |
            |------------------|
            |    4 0           |
            |    6 1           |
            |    7 2           |
            |    10 3          |
            |    2 4           |
            |                  |
            |------------------|
            |Sorted On Nail Len|
            |------------------|
            |    2 4           |
            |    4 0           |
            |    6 1           |
            |    7 2           |
            |    10 3          |
            --------------------
        */

        for (int i = 0; i < N; i++) {

            sortedNails[i][0] = C[i];
            sortedNails[i][1] = i;
        }


        /*
         * sort based on the size of the nail in ascending order
         * */
        Arrays.sort(sortedNails, (int[] x, int[] y) -> (Integer.compare(x[0], y[0])));

        int index = 0;

        for (int i = 0; i < P; i++) {

            index = getMinimumIndex(A[i], B[i], sortedNails, index);

            if (index == -1) {
                return -1;
            }
        }

        /*
         * we search through the consecutive indexes
         * */
        int result = index + 1;

        return result;
    }


    /*
     * Get the nail with the minimum length
     */
    public static int getMinimumIndex(int plankStart, int plankEnd, int[][] sortedNails, int oldIndex) {

        int N = sortedNails.length;

        int low = 0;
        int high = N - 1;

        /*
         * the index is in absolute value and
         * smallest possible fit for the given
         * planks
         * */
        int resultIndex = -1;

        while (low <= high) {
            
            int middle = (low + high) / 2;

            if (sortedNails[middle][0] < plankStart) {
                low = middle + 1;
            } 

            else if (sortedNails[middle][0] > plankEnd) {
                high = middle - 1;
            } 

            else {
                high = middle - 1;
                resultIndex = middle;
            }
        }

        if (resultIndex == -1 || sortedNails[resultIndex][0] > plankEnd) {
            return -1;
        }

        int minIndex = sortedNails[resultIndex][1];

        while (resultIndex < N && sortedNails[resultIndex][0] <= plankEnd) {

            minIndex = Math.min(minIndex, sortedNails[resultIndex][1]);

            if (minIndex <= oldIndex) {
                return oldIndex;
            }

            resultIndex++;
        }

        return minIndex;
    }






    /*
     * solution - b
     */
    public static int solution1(int[] A, int[] B, int[] C) {

        /*
         * each element of arrays A, B, C is an integer within the range
         * [1,...., 2*M] where M is the length of the array C (filled with
         * the nails)
         * */

        int[] nails = new int[2 * C.length + 1];
        int begin = 0;

        int end = C.length;
        int result = -1;


        /*
         * binary search. O(log(M)) times
         * */
        while (begin <= end) {

            int middle = (begin + end) / 2;

            for (int i = 0; i < middle; i++) {
                nails[C[i]] = 1;
            }

            int counter = 0;

            for (int i = 0; i < nails.length; i++) {

                if (nails[i] == 1) {
                    counter++;
                }

                nails[i] = counter;
            }

            /*
             * find nails number between A[i] and B[i]
             * */
            boolean isFound = true;

            for (int i = 0; i < A.length; i++) {

                if (nails[B[i]] - nails[A[i] - 1] == 0) {
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                end = middle - 1;
                result = middle;
            } else {
                begin = middle + 1;
            }
        }

        return result;
    }


    /*
     * displays A 2d array in the console, one line per row
     * */
    static void printMatrix(int[][] grid) {

        for (int[] g : grid) {
            System.out.println(Arrays.toString(g));
        }
    }


    /*
     * solution - d
     */
    class Plank {
        private int start;
        private int end;
        private boolean isNailed = false;

        public Plank(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public boolean isNailed() {
            return isNailed;
        }

        public void nail() {
            this.isNailed = true;
        }
    }


    public int solution3(int[] A, int[] B, int[] C) {

        ArrayList<Plank> planks = new ArrayList<>();

        for (int i = 0; i < A.length; i++) {
            planks.add(new Plank(A[i], B[i]));
        }

        Collections.sort(planks, (o1, o2) -> o1.getStart() - o2.getStart());

        ArrayList<Integer> starts = this.toArrayList(A);
        ArrayList<Integer> ends = this.toArrayList(B);

        Collections.sort(starts);
        Collections.sort(ends);

        int count = 0;

        for (int i = 0; i < C.length; i++) {

            int startsQuantity = this.getStartsQuantity(starts, C[i]);
            int endsQuantity = this.getEndsQuantity(ends, C[i]);

            ArrayList<Integer> planksToRemove = new ArrayList<>();

            for (int j = endsQuantity; j < startsQuantity; j++) {
                if (!planks.get(j).isNailed() && planks.get(j).getStart() <= C[i] && planks.get(j).getEnd() >= C[i]) {
                    planks.get(j).nail();
                    planksToRemove.add(j);
                    count++;
                    if (A.length - count == 0) {
                        return i + 1;
                    }
                }
            }
            for (int j = 0; j < planksToRemove.size(); j++) {
                int startIndex = Collections.binarySearch(starts, planks.get(planksToRemove.get(j)).getStart());
                if (startIndex >= 0) {
                    starts.remove(startIndex);
                }
                int endIndex = Collections.binarySearch(ends, planks.get(planksToRemove.get(j)).getEnd());
                if (endIndex >= 0) {
                    ends.remove(endIndex);
                }
            }
            for (int j = 0; j < planksToRemove.size(); j++) {
                planks.remove(planksToRemove.get(j) - j);
            }

        }
        return -1;
    }

    private ArrayList<Integer> toArrayList(int[] data) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            result.add(i, data[i]);
        }
        return result;
    }

    public int getMid(ArrayList<Integer> data, int key) {

        int right = 0;

        int left = data.size() - 1;
        int mid = -1;

        while (right <= left) {

            mid = right + (left - right) / 2;

            if (key < data.get(mid)) {
                left = mid - 1;
            } else if (key > data.get(mid)) {
                right = mid + 1;
            } else {
                return mid;
            }
        }

        return mid;
    }


    public int getEndsQuantity(ArrayList<Integer> ends, int point) {

        int mid = getMid(ends, point);
        if (mid != ends.size() - 1) {
            mid++;
        }

        if (mid == ends.size() - 1 && ends.get(mid) < point) {
            return ends.size();
        }

        for (int i = mid; i > 0; i--) {
            if (ends.get(i - 1) < point && ends.get(i) >= point) {
                return i;
            }
        }

        return 0;
    }

    public int getStartsQuantity(ArrayList<Integer> starts, int point) {

        int mid = getMid(starts, point) - 1;

        if (mid < 0) {
            mid = 0;
        }

        int count = mid;

        for (int i = mid; i < starts.size(); i++) {
            if (starts.get(i) <= point) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public int nativeSolution(int[] A, int[] B, int[] C) {

        ArrayList<Plank> planks = new ArrayList<>();

        for (int i = 0; i < B.length; i++) {
            Plank plank = new Plank(A[i], B[i]);
            planks.add(plank);
        }

        int counter = 0;

        for (int i = 0; i < C.length; i++) {

            for (int j = 0; j < planks.size(); j++) {

                if (planks.get(j).isNailed()) {
                    continue;
                }

                if (planks.get(j).getStart() <= C[i] && planks.get(j).getEnd() >= C[i]) {
                    planks.get(j).nail();
                    counter++;
                }

                if (A.length - counter == 0) {
                    return i + 1;
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        int[] A = new int[4];
        int[] B = new int[4];

        int[] C = new int[5];

        A[0] = 1;
        B[0] = 4;

        A[1] = 4;
        B[1] = 5;

        A[2] = 5;
        B[2] = 9;

        A[3] = 8;
        B[3] = 10;


        C[0] = 4;
        C[1] = 6;
        C[2] = 7;
        C[3] = 10;
        C[4] = 2;


        System.out.println(solution1(A, B, C));
    }
}
