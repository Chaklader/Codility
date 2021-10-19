package com.codility.Interview;


/*
You are playing A game with N cards. On each side of each card A positive integer is
written.
The score of the game is smallest positive integer that doesn't appear on the cards'
front faces.
You may decide which cards you want to flip over. Having flipped them, you then read

the numbers on the front faces of all the cards. What is the minimum game score you c
an achieve?


Write A function:

    class Solution {

        public int solution(int[] A, int[] B);
    }


that, given two arrays of integers A and B, both of length N, describing the numbers w
ritten on
the fronts and backs of all the cards, returns the minimum possible game score. For ex
ample,
 given A = [1, 2, 4, 3] and B = [1, 3, 2, 3], your function should return 2, as we cou
 ld
 flip second card such that the front-facing numbers were [1, 3, 4, 3] and the smalles
 t
 positive integer excluded from this sequence is 2. Given A = [3, 2, 1, 6, 5] and

 B = [4, 2, 1, 3, 3], your function should return 3, as we could flip first card such
 that
 the front-facing numbers were [4, 2, 1, 6, 5]. Given A = [1, 2] and B = [1, 2] your f
 unction
 should return 3, as no matter how we flip the cards the front-facing numbers will be
 [1, 2].
 Assume that: N is an integer within the range [1..100,000]; each element of arrays A,
  B is
 an integer within the range [1..100,000]; input arrays are of equal size. Complexity:

 expected worst-case time complexity is O(N); expected worst-case space complexity is
 O(N)
 (not counting the storage required for input arguments).
*/



import com.codility.L4_Counting_Elements.FrogRiverOne;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Chaklader on 7/12/18.
 */
public class MinimumScoreTest {

    @Test
    public void solution() {

        int[] A = {1, 2, 4, 3};
        int[] B = {1, 3, 2, 3};

        assertEquals(2, MinimumScore.solution(A, B));
    }



//    @Test
//    public void solution() throws Exception {
//        int[] data = {1, 3, 1, 4, 2, 3, 5, 4};
//        assertEquals(6, new FrogRiverOne.Solution().solution(5, data));
//    }
}