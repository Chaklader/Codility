package com.codility.Indeed_Prime_2016_Challenge;/*

A company of dwarfs is travelling across the New Zealand. On reaching the Clutha River the dwarfs need to get across, but recent storms have washed away the bridge. Luckily, a small ferry, in the form of a square raft, is operating.

The raft is square and has N rows of seats, numbered from 1 to N. Each row contains N seats, labeled with consecutive letters (A, B, C, etc.). Each seat is identified by a string composed of its row number followed by its column number; for example, "9C" denotes the third seat in the 9th row.

The raft has already been loaded with barrels in some seat positions, and other seats are already occupied by dwarfs. Our company of dwarfs may only take the remaining unoccupied seats. The ferryman wants to accommodate as many dwarfs as possible, but the raft needs to be stable when making the crossing. That is, the following conditions must be satisfied:

the front and back halves of the raft (in terms of the rows of seats) must each contain the same number of dwarfs;
similarly, the left and right sides of the raft (in terms of the columns of seats) must each contain the same number of dwarfs.
You do not have to worry about balancing the barrels; you can assume that their weights are negligible.

For example, a raft of size N = 4 is shown in the following illustration:



Barrels are marked as brown squares, and seats that are already occupied by dwarfs are labeled d.

The positions of the barrels are given in string S. The occupied seat numbers are given in string T. The contents of the strings are separated by single spaces and may appear in any order. For example, in the diagram above, S = "1B 1C 4B 1D 2A" and T = "3B 2D".

In this example, the ferryman can accommodate at most six more dwarfs, as indicated by the green squares in the following diagram:



The raft is then balanced: both left and right halves have the same number of dwarfs (four), and both front and back halves have the same number of dwarfs (also four).

Write a function:

class Solution { public int solution(int N, String S, String T); }

that, given the size of the raft N and two strings S, T that describes the positions of barrels and occupied seats, respectively, returns the maximum number of dwarfs that can fit on the raft. If it is not possible to balance the raft with dwarfs, your function should return -1.

For instance, given N = 4, S = "1B 1C 4B 1D 2A" and T = "3B 2D", your function should return 6, as explained above.

Assume that:

N is an even integer within the range [2..26];
strings S, T consist of valid seat numbers, separated with spaces;
each seat number can appear no more than once in the strings;
no seat number can appear in both S and T simultaneously.
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
*/

import java.util.stream.IntStream;

public class DwarfsRafting{


    private int[] dwarfs;
    private int[] capacity;

    public int solution(int N, String S, String T) {

        if (N % 2 == 1) {
            return -1;
        }

        S = S.toLowerCase();
        T = T.toLowerCase();
        int[] barrels = parseString(S, N);
        dwarfs = parseString(T, N);
        capacity = new int[4];
        for (int i = 0; i < barrels.length; i++) {
            capacity[i] = (N * N / 4) - barrels[i];
        }
        int diagonal = getBalanced(0, 3);
        int antiDiagonal = getBalanced(1, 2);
        if (diagonal < 0 || antiDiagonal < 0) {
            return -1;
        }
        return diagonal + antiDiagonal - IntStream.of(dwarfs).sum();
    }

    private int charToInt(char letter) {
        return letter - 'a';
    }

    private int getBalanced(int left, int right) {

        int balance = Math.min(capacity[left], capacity[right]);

        if (balance < Math.max(dwarfs[left], dwarfs[right])) {
            return -1;
        } else {
            return balance * 2;
        }
    }

    private int[] parseString(String in, int N) {

        if (in.isEmpty()) {
            return new int[4];
        }

        int[] data = new int[4];
        String[] chunks = in.split(" ");

        for (String chunk : chunks) {

            int column = Integer.parseInt(chunk.substring(0, chunk.length() - 1));
            int row = charToInt(chunk.charAt(chunk.length() - 1));
            if (column <= N / 2) {
                if (row < N / 2) {
                    data[0]++;
                } else {
                    data[1]++;
                }
            } else {
                if (row < N / 2) {
                    data[2]++;
                } else {
                    data[3]++;
                }
            }
        }
        return data;
    }
}