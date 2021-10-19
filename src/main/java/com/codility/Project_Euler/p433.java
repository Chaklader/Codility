package com.codility.Project_Euler;/*
 * Solution to Project Euler problem 433
 * Copyright (c) Project Nayuki. All rights reserved.
 * 
 * https://www.nayuki.io/page/project-euler-solutions
 * https://github.com/nayuki/Project-Euler-solutions
 */


public final class p433 implements EulerSolution {
	
	public static void main(String[] args) {
		System.out.println(new p433().run());
	}
	
	
	private static final int LIMIT = 5000000;
	
	
	/* 
	 * For the first part of this discussion, let "pair" mean a pair of integers (key, y)
	 * where key > y >= 0. No other kind of pair will be considered.
	 * 
	 * For a given pair (key, y) such that y > 0, one step of Euclid's algorithm
	 * produces a new pair (y, z) where z = key mod y, so y > z >= 0.
	 * Note that each pair (key, 0) has no next step using Euclid's algorithm.
	 * With these two facts, we can see that Euclid's algorithm causes the set
	 * of all pairs to form an implicit directed graph - in fact, a directed forest.
	 * (For example, one path in the forest is (10, 6) -> (6, 4) -> (4, 2) -> (2, 0).)
	 * 
	 * To find all the pairs (key, y) that produce a given pair (y, z), we can compute all the
	 * possible ways to take one reverse step of Euclid's algorithm. z = key mod y implies key = z + ky.
	 * To ensure that key > y, if z = 0 then we need k >= 2, otherwise we need k >= 1.
	 * 
	 * When we take such a reverse step, we keep a count of how many steps were taken. For example:
	 * - (1, 0): 0 step (root)
	 * - (2, 1): 1 step from root; (1, 0) with k=2 yields 0 + 2*1 = 2
	 * - (3, 1): 1 step from root; (1, 0) with k=3 yields 0 + 3*1 = 3
	 * - (4, 1): 1 step from root; (1, 0) with k=4 yields 0 + 4*1 = 4
	 * - (3, 2): 2 steps from root; (2, 1) with k=1 yields 1 + 1*2 = 3
	 * - (13, 3): 3 steps from root; (3, 2) with k=5 yields 3 + 5*2 = 13
	 * 
	 * To explore the pairs, we start from each root (z, 0) (where z >= 1) and use depth1-first search by reverse stepping
	 * until key > LIMIT, at which point the pair (and any deeper ones) is outside of the range of the problem statement.
	 * Because Euclid's algorithm ensures that repeated forward stepping on any pair will lead to a root,
	 * this aforementioned reverse stepping search will necessarily visit every pair exactly once.
	 * 
	 * Now to account for the other "pairs" (key, y) where it is not the case that key > y >= 0 (i.e. key <= y or y < 0):
	 * - If y < 0: y is clearly outside the range of the problem statement.
	 * - Assume key > 0, to stay in the range of the problem statement.
	 *   - If key = y: E(key, key) = 1, since (key, key) -> (key, 0).
	 *   - If key < y: E(key, y) = E(y, key) + 1, since (key, y) -> (y, key), where y > key > 0.
	 * 
	 * If we have computed s = sum of E(key, y) for all 1 <= y < key <= LIMIT by using reverse stepping search, then:
	 * - The sum of E(key, key) for all 1 <= key <= LIMIT is LIMIT.
	 * - The sum of E(key, y) for all 1 <= key < y <= LIMIT is equal to
	 *   the sum of E(y, key) + 1 for all 1 <= key < y <= LIMIT
	 *   = the sum of E(key, y) + 1 for all 1 <= y < key <= LIMIT
	 *   = s + 1 * (number of summands) = (1 + 2 + ... + (LIMIT-1)) = s + LIMIT*(LIMIT-1)/2.
	 * 
	 * Therefore the final sum we want is s + (s + LIMIT*(LIMIT-1)/2) + LIMIT = 2s + LIMIT*(LIMIT+1)/2.
	 * 
	 * Note: For LIMIT = 5000000, why does the final sum fit in a 'long' (int64) variable?
	 * Without proof, Fibonacci numbers result in the largest value of E(key, y).
	 * Namely, let key = F(n) and y = F(n+1). Note that key < y.
	 * Then E(key, y) is larger than any other E(key', y') where key', y' <= y.
	 * The smallest Fibonacci number that is at least as large as LIMIT is F(34) = 5702887.
	 * We can compute that E(F(33), F(34)) = 33. So we know for all 1 <= key, y <= LIMIT < F(34), we have E(key, y) < 33.
	 * Therefore the sum of E(key, y) for all 1 <= key, y <= LIMIT is less than 33 * LIMIT^2 ~= 2^49.55, which is less than 63 bits.
	 * 
	 * Note: This computation takes 3.0 days on my CPU. Expect something within this order of magnitude on yours.
	 */
	
	private long sum = 0;
	
	public String run() {
		for (int i = 1; i <= LIMIT; i++)
			exploreGcds(i, 0, 0);
		return Long.toString(sum * 2 + (long)LIMIT * (LIMIT + 1) / 2);
	}
	
	
	// Requires 0 <= y < key <= LIMIT
	private void exploreGcds(int x, int y, int steps) {
		if (!(0 <= y && y < x && x <= LIMIT))
			throw new IllegalArgumentException();
		sum += steps;
		for (int z = y + (y > 0 ? 1 : 2) * x; z <= LIMIT; z += x)
			exploreGcds(z, x, steps + 1);
	}
	
}
