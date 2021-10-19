package com.codility.Geeks_For_Geeks;

/*
Given an unsorted array, design and code an algorithm that returns whether there are two duplicates

within k indices of each other? k indices and within plus or minus left (value) of each other? Do all,

even the latter, in O(n) running time and O(k) space.
*/



/*
Time complexity  of O(nlogk).

 Maintain a balanced binary search tree of k+1 elements at all
 times. Initially, put the first k+1 elements of the unsorted array into the BST, ordered by value.
 Then, for each node j in the BST (other than the node with the maximum value), check if
 |j.value()−successor(j).value()|≤d. If so, you have found fuzzy duplicates. This takes O(klogk). Oth
 erwise, do the following.


For k+1≤i,≤n−1, remove the node with value array[i−(k+1)] from the BST, and insert array[i] into the
BST. Then for the new node you inserted, check if its predecessor (if it has one) is a fuzzy duplic
ate or if its successor (if it has one) is a fuzzy duplicate. If either is a fuzzy dupli
cate, then we are done. Otherwise, we keep iterating. The invariant always maintained is that every
element in the BST is within k indices of every other element in the BST. Additionally, if there were
originally no fuzzy duplicates, and then we removed a node and inserted another node, the only possible
resultant fuzzy duplicates would involve the inserted node. And if any other node contains a value that
is a fuzzy duplicate of the new node's value, then either the successor of the new node or the predecessor
of the new node would have to be a fuzzy duplicate.


Looping takes O((n−k)logk), and therefore our total runtime is O(nlogk).
* */

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Created by Chaklader on 7/5/18.
 */
public class TwoDuplicates {


    /*
     * solution - a
     * */
    public static boolean solution(int A[], int K) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < A.length; i++) {

            /*
             * If already present n hash, then we
             * found a duplicate within k distance
             * */
            if (set.contains(A[i])) {
                return true;
            }

            /*
             * add this item to hashset
             * */
            set.add(A[i]);

            /*
             * remove the k+1 distant item
             * */
            if (i >= K) {
                set.remove(A[i - K]);
            }
        }

        return false;
    }



    /*
     * A taxicab geometry is a form of geometry in which the usual distance function or
     * metric of Euclidean geometry is replaced by a new metric in which the distance
     * between two points is the sum of the absolute differences of their Cartesian
     * coordinates. The taxicab metric is also known as rectilinear distance, L1 distance,
     * L1 distance or  norm (see Lp space), snake
     * distance, city block distance, Manhattan distance or Manhattan length, with
     * corresponding variations in the name of the geometry.[1] The latter names allude
     * to the grid layout of most streets on the island of Manhattan, which causes the
     * shortest path a car could take between two intersections in the borough to have
     * length equal to the intersections' distance in taxicab geometry.
     * */


    /*
     * For example, a=[1, 2, 3, 1, 3, 5] then for k ≤ 1 return false as no duplicates in k index away. For k=2 we return true as 3 is repeated in 2 distance away. Similarly for k ≥ 3 we return true as both 1 and 3 are repeated.
     * */

    /*
     * solution - b
     * */
    public static boolean solution1(int[] A, int K) {

        int N = A.length;

        K = Math.min(N, K);

        /*
         * there are k+1 elements within k induces
         * from an element - so window size is k+1
         * */
        Set<Integer> set = new HashSet<Integer>(K + 1);

        //create initial wiindow of size k+1
        int i;

        for (i = 0; i <= K; i++) {

            if (set.contains(A[i])) {
                return true;
            }

            set.add(A[i]);
        }

        for (i = K + 1; i < N; i++) {

            set.remove(A[i - K]);

            if (set.contains(A[i])) {
                return true;
            }

            set.add(A[i]);
        }

        return false;
    }
}
