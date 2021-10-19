package com.codility.L11_Sieve_Of_Eratosthenes;

/*
* A prime is A positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.

A semiprime is A natural number that is the product of two (not necessarily distinct) prime numbers. The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.

You are given two non-empty arrays P and Q, each consisting of M integers. These arrays represent queries about the number of semiprimes within specified ranges.

Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.

For example, consider an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
The number of semiprimes within each of these ranges is as follows:

(1, 26) is 10,
(4, 10) is 4,
(16, 20) is 0.
Write A function:

class Solution { public int[] solution(int N, int[] P, int[] Q); }

that, given an integer N and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.

For example, given an integer N = 26 and arrays P, Q such that:

    P[0] = 1    Q[0] = 26
    P[1] = 4    Q[1] = 10
    P[2] = 16   Q[2] = 20
the function should return the values [10, 4, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
M is an integer within the range [1..30,000];
each element of arrays P, Q is an integer within the range [1..N];
P[i] ≤ Q[i].
Complexity:

expected worst-case time complexity is O(N*log(log(N))+M);
expected worst-case space complexity is O(N+M) (not counting the storage required for input arguments).
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chaklader on 6/25/18.
 */
public class CountSemiprimes {


    /*
     * sieve of Eratosthenes is A simple, ancient algorithm for finding all prime numbers up
     * to any given limit.It does so by iteratively marking as composite (i.e., not prime)
     * the multiples of each prime, starting with the first prime number, 2. The multiples
     * of A given prime are generated as A sequence of numbers starting from that prime, with
     * constant difference between them that is equal to that prime. This is the sieve's
     * key distinction from using trial division to sequentially test each candidate number
     * for divisibility by each prime
     * */

    /*
     * A semiprime is A natural number that is the product
     * of two (not necessarily distinct) prime numbers.
     * */


    // Count the semiprime numbers in the given range [a..b]
    /*
     * solution - a
     */

    // A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. 
    public static int[] solution(int[] A, int[] B, int N) {


        int[] factArray = sieve(N);        
        int[] semiPrimes = new int[N +1];

        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26]
        // [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2,  0,  2,  0,  2,  3,  2,  0,  2,  0,  2,  3,  2,  0,  2,  5,  2]
        // [0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1,  0,  0,  0,  1,  1,  0,  0,  0,  0,  0,  1,  1,  0,  0,  1,  1]
        // [0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 4,  4,  4,  4,  5,  6,  6,  6,  6,  6,  6,  7,  8,  8,  8,  9,  10]
        // [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5, 2]
        for (int i = 0; i < semiPrimes.length; i++) {

            // int k = i / factArray[i]  factArray[k] == 0 means the number in the k index is prime
            if (factArray[i] != 0 && factArray[i / factArray[i]] == 0) {
                semiPrimes[i] = 1;
            }
        }

        int[] semiPrimesPreSum = prefixSum(semiPrimes);
        int[] res = new int[A.length];


        /*
        Q[0] = 26
        Q[1] = 10
        Q[2] = 20

        P[0] = 1    
        P[1] = 4    
        P[2] = 16   
        */

        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26]
        // [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2,  0,  2,  0,  2,  3,  2,  0,  2,  0,  2,  3,  2,  0,  2,  5,  2]
        // [0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1,  0,  0,  0,  1,  1,  0,  0,  0,  0,  0,  1,  1,  0,  0,  1,  1]

        // [0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 4,  4,  4,  4,  5,  6,  6,  6,  6,  6,  6,  7,  8,  8,  8,  9,  10]

        for (int i = 0; i < B.length; i++) {
            res[i] = semiPrimesPreSum[B[i]] - semiPrimesPreSum[A[i] - 1];
        }

        return res;
    }


    // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26]
    // [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2,  0,  2,  0,  2,  3,  2,  0,  2,  0,  2,  3,  2,  0,  2,  5,  2]
    // [0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1,  0,  0,  0,  1,  1,  0,  0,  0,  0,  0,  1,  1,  0,  0,  1,  1]

    // [0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 4,  4,  4,  4,  5,  6,  6,  6,  6,  6,  6,  7,  8,  8,  8,  9,  10]

    /*
     * preparing array for factorization (array with primes)
     * [0, 0, 0, 0, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 0, 2, 3, 2, 0, 2, 5, 2]
     * */

    /*
        Initially, we have the set of all the numbers {2, 3,...,n}. At each step we choose the
        smallest number in the set and remove all its multiples. Notice that every composite number
        has a divisor of at most on. In particular, it has a divisor which is a prime number. It
        is sucient to remove only multiples of prime numbers not exceeding Ôn. In this way, all
        composite numbers will be removed.
    */
    public static int[] sieve(int n) {

        int[] F = new int[n + 1];

        for (int i = 2; i * i <= n; i++) {

            if (F[i] == 0) {

                for (int k = i * i; k <= n; k += i) {

                    if (F[k] == 0) {
                        F[k] = i;
                    }
                }
            }
        }

        return F;
    }



    // [0, 0, 0, 0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5, 6, 6, 6, 6, 6, 6, 7, 8, 8, 8, 9, 10]
    public static int[] prefixSum(int[] A) {

        int[] prefSum = new int[A.length];

        for (int i = 0; i < A.length; i++) {

            if (i == 0) {
                prefSum[i] = A[i];
            } else {
                prefSum[i] = prefSum[i - 1] + A[i];
            }
        }

        return prefSum;
    }


    public static List<Integer> findPrimesUsingSieve2(int N) {

        int[] F = new int[N + 1];        
        List<Integer> result = new ArrayList<>();

        for (int i = 2; i <= N; i++) {

            if (F[i] == 0) {

                result.add(i);

                for (int k = i * i; k <= N; k += i) {

                    if (F[k] == 0) {
                        F[k] = 1;
                    }
                }
            }
        }

        return result;
    }
    
    // sieve Of Eratosthenes 
    public static List<Integer> findPrimesUsingSieve(int N) {

        List<Integer> result = new ArrayList<>();

        boolean[] isComposite = new boolean[N + 1];

        for (int i = 2; i <= N; i++) {

            if (!isComposite[i]) {

                result.add(i);
                int M = 2;

                while (i * M <= N) {

                    isComposite[i * M] = true;
                    M++;
                }
            }
        }

        return result;
    }
    // ENd of solution - a




    /*
    solution - bb
    */
    public static int[] solution(int N, int[] P, int[] Q) {


        List<Integer> primes = findSemiPrimes(26);
        List<Integer> semiprimes = findSemiPrimes(N, primes);

        int[] result = new int[P.length];


        for (int i = 0; i < P.length; i++) {

            int count = 0;

            for (int j = 0; j < semiprimes.size(); j++) {

                if (semiprimes.get(j) > Q[i]) {
                    break;
                }

                if (P[i] <= semiprimes.get(j) && semiprimes.get(j) <= Q[i]) {
                    count++;
                }
            }

            result[i] = count;
        }

        return result;
    }


    public static List<Integer> findSemiPrimes(int N, List<Integer> primes) {

        List<Integer> semiprimes = new ArrayList<>();
        
        for (int i = 0; i < primes.size(); i++) {

            for (int j = i; j < primes.size(); j++) {

                int temp = (primes.get(i) * primes.get(j));

                if (temp > N) {
                    break;
                }

                semiprimes.add(temp);
            }
        }

        Collections.sort(semiprimes);

        return semiprimes;
    }

    private static List<Integer> findSemiPrimes(int N) {

        int[] A = new int[N + 1];

        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= N; i++) {

            if (A[i] == 0) {

                primes.add(i);

                for (int j = i; j <= N; j += i) {

                    if (A[j] == 0) {

                        A[j] = 1;
                    }
                }
            }
        }

        return primes;
    }
    /*
    END of solution bb
    */


    /*
     * solution - b
     */
    public int[] solution1(int N, int[] P, int[] Q) {

        int length = P.length;
        int[] prime = sieve1(N);

        boolean[] semiprime = semiprime1(prime);
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {

            int primeNumber = countSemiprimes(P[i], Q[i], semiprime, N);
            result[i] = primeNumber;
        }

        return result;
    }


    /*
     * use the sieve to get factorized numbers
     */
    public int[] sieve1(int N) {

        int[] prime = new int[N + 1];

        for (int i = 2; i <= (double) Math.sqrt(N); i++) {

            if (prime[i] == 0) {

                int k = i * i;

                while (k <= N) {

                    if (prime[k] == 0) {
                        prime[k] = i;
                    }

                    k += i;
                }
            }
        }
        return prime;
    }


    public boolean[] semiprime1(int[] prime) {

        boolean semiprime[] = new boolean[prime.length];

        for (int i = 0; i < prime.length; i++) {

            if (prime[i] == 0) {
                continue;
            }

            int firstFactor = prime[i];

            if (prime[i / firstFactor] == 0) {
                semiprime[i] = true;
            }
        }

        return semiprime;
    }


    public int countSemiprimes(int P, int Q, boolean[] semiprime, int N) {

        int count = 0;

        if (P > Q || P > N || Q > N) {
            return 0;
        }

        for (int i = P == 1 ? 2 : P; i <= Q; i++) {
            if (semiprime[i]) {
                count++;
            }
        }

        return count;
    }


    /*
     * solution - c
     */
    public int[] solution2(int N, int[] P, int[] Q) {

        int length = P.length;

        int[] prime = sieve2(N);
        int[] semiprime = semiprime(prime);

        int[] result = new int[length];
        int[] semiprimesAggreation = new int[N + 1];


        for (int i = 1; i < N + 1; i++) {

            semiprimesAggreation[i] = semiprime[i];
            semiprimesAggreation[i] += semiprimesAggreation[i - 1];
        }

        for (int i = 0; i < length; i++) {

            result[i] = semiprimesAggreation[Q[i]]
                    - semiprimesAggreation[P[i]]
                    + semiprime[P[i]];
        }

        return result;
    }

    public int[] sieve2(int N) {

        int[] prime = new int[N + 1];

        for (int i = 2; i <= (double) Math.sqrt(N); i++) {

            if (prime[i] == 0) {

                int k = i * i;

                while (k <= N) {
                    if (prime[k] == 0) {
                        prime[k] = i;
                    }
                    k += i;
                }
            }
        }
        return prime;
    }

    public int[] semiprime(int[] prime) {

        int semiprime[] = new int[prime.length];

        for (int i = 0; i < prime.length; i++) {

            if (prime[i] == 0) {
                continue;
            }

            int firstFactor = prime[i];

            if (prime[i / firstFactor] == 0) {
                semiprime[i] = 1;
            }
        }
        return semiprime;
    }


    /*
     * solution - d
     */
    public int[] solution(int n, int[] p, int[] q) {

        final int[] m = new int[n];
        int i = 2;

        // mark all non-prime numbers with minimal prime factor
        while ((long) i * i <= n) {

            int k = i * i;

            while (k <= n) {

                if (m[k - 1] == 0) {
                    m[k - 1] = i;
                }

                k += i;
            }

            i++;
        }

        // if number / it's minimal prime factor is not a prime number, unmark
        for (i = m.length - 1; i >= 0; i--) {

            if (m[i] > 0 && m[(i + 1) / m[i] - 1] != 0) {
                m[i] = 0;
            }
        }

        // memorize number semiprimes from 0 to i
        int c = 0;

        for (i = 0; i < m.length; i++) {

            if (m[i] > 0) {
                c++;
            }

            m[i] = c;
        }

        // calculate result for ranges
        int[] result = new int[p.length];

        for (i = 0; i < p.length; i++) {

            int from = p[i] - 1;
            int to = q[i] - 1;

            if (from == 0) {
                result[i] = m[to];
            } else {
                result[i] = m[to] - m[from - 1];
            }
        }

        return result;
    }


    /*
     * solution - a
     * */
    HashMap<Integer, Boolean> semiPrimes;

    public int[] solution4(int N, int[] P, int[] Q) {

        if (N == 1) {
            return new int[P.length];
        }

        initSemiprimes(N);
        int[] result = new int[P.length];
        int[] df = new int[N + 1];
        df[0] = 0;
        for (int i = 1; i < df.length; i++) {
            if (semiPrimes.get(i)) {
                df[i] = df[i - 1] + 1;
            } else {
                df[i] = df[i - 1];
            }
        }
        for (int i = 0; i < Q.length; i++) {
            int secondIndex = P[i] == 0 ? 0 : P[i] - 1;
            result[i] = df[Q[i]] - df[secondIndex];
        }
        return result;
    }

    private void initSemiprimes(int N) {
        semiPrimes = new HashMap<>();
        for (int i = 0; i <= N; i++) {
            semiPrimes.put(i, false);
        }
        int[] primes = getPrimes(N);
        int i = 0;
        while (primes[i] <= N / 2) {
            int j = i;
            while (primes[j] * primes[i] <= N) {
                semiPrimes.put(primes[i] * primes[j], true);
                j++;
            }
            i++;
        }
    }

    private int[] getPrimes(int N) {
        boolean[] sieve = new boolean[N + 1];
        Arrays.fill(sieve, Boolean.TRUE);
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i < (int) (Math.sqrt(N) + 1); i++) {
            if (sieve[i]) {
                for (int j = i + i; j < N; j += i) {
                    sieve[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i]) {
                primes.add(i);
            }
        }
        return primes.stream().mapToInt(i -> i).toArray();
    }
}
