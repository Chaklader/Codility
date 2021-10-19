package com.codility.Indeed_Prime_2016_Challenge;/*
Halfling Woolly Proudhoof is an eminent sheep herder. He wants to build a pen (enclosure) for his new flock of sheep. The pen will be rectangular and built from exactly four pieces of fence (so, the pieces of fence forming the opposite sides of the pen must be of equal length). Woolly can choose these pieces out of N pieces of fence that are stored in his barn. To hold the entire flock, the area of the pen must be greater than or equal to a given threshold X.

Woolly is interested in the number of different ways in which he can build a pen. Pens are considered different if the sets of lengths of their sides are different. For example, a pen of size 1×4 is different from a pen of size 2×2 (although both have an area of 4), but pens of sizes 1×2 and 2×1 are considered the same.

Write a function:

class Solution { public int solution(int[] A, int X); }

that, given an array A of N integers (containing the lengths of the available pieces of fence) and an integer X, returns the number of different ways of building a rectangular pen satisfying the above conditions. The function should return −1 if the result exceeds 1,000,000,000.

For example, given X = 5 and the following array A:

  A[0] = 1
  A[1] = 2
  A[2] = 5
  A[3] = 1
  A[4] = 1
  A[5] = 2
  A[6] = 3
  A[7] = 5
  A[8] = 1


the function should return 2. The figure above shows available pieces of fence (on the left) and possible to build pens (on the right). The pens are of sizes 1x5 and 2x5. Pens of sizes 1×1 and 1×2 can be built, but are too small in area. It is not possible to build pens of sizes 2×3 or 3×5, as there is only one piece of fence of length 3.

Assume that:

N is an integer within the range [0..100,000];
X is an integer within the range [1..1,000,000,000];
each element of array A is an integer within the range [1..1,000,000,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
*/


import java.util.*;
import java.util.stream.Collectors;

public class RectangleBuilderGreaterArea {


    /*
     * solution - a
     * */
    public int solution(int[] A, int X) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            if (map.get(a) == null) {
                map.put(a, 1);
            } else {
                map.put(a, map.get(a) + 1);
            }
        }
        List<Integer> valid = map.entrySet().stream()
                .filter(entry -> entry.getValue() >= 2)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        int count = 0;
        int duplicatesCount = 0;
        int result = 0;
        Collections.sort(valid);
        for (Integer number : valid) {
            int factor = (int) Math.ceil((double) X / (double) number);
            int index = Collections.binarySearch(valid, factor);
            if (index < 0) {
                index = -index - 1;
            }
            count += valid.size() - index;
            if (number >= factor) {
                if (map.get(number) >= 4) {
                    duplicatesCount++;
                }
                count--;
            }
            result = count / 2 + duplicatesCount;
            if (result > 1000000000) {
                return -1;
            }
        }
        return result;
    }


    /*
     * solution - b
     * */
    public int solution1(int[] A, int X) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.get(A[i]) == null) {
                map.put(A[i], 1);
            } else {
                map.put(A[i], map.get(A[i]) + 1);
            }
        }
        ArrayList<Integer> valid = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            valid.add(entry.getKey());
        }
        int count = 0;
        Arrays.sort(A);
        for (Integer firstNumber : valid) {
            for (Integer secondNumber : valid) {
                if (firstNumber * secondNumber >= X) {
                    if (firstNumber == secondNumber) {
                        if (map.get(firstNumber) >= 4) {
                            count++;
                        }
                    } else if (map.get(firstNumber) >= 2 && map.get(secondNumber) >= 2) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}