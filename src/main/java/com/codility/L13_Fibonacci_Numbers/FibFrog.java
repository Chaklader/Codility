package com.codility.L13_Fibonacci_Numbers;

/*
*   

    The Fibonacci sequence is defined using the following recursive formula:

        F(0) = 0
        F(1) = 1

        F(M) = F(M - 1) + F(M - 2) if M >= 2


    A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (pos −1)

    and wants to get to the other bank (pos N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci

    number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of

    the bank at pos N.



    The leaves on the river are represented in an array A consisting of N integers. Consecutive elements of array A represent

    consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:


        0 represents a pos without a leaf

        1 represents a pos containing a leaf




    The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from pos

    −1 to pos N). The frog can jump between positions −1 and N (the banks of the river) and every pos containing a leaf.


    For example, consider array A such that:

        A[0] = 0
        A[1] = 0
        A[2] = 0
        A[3] = 1
        A[4] = 1
        A[5] = 0
        A[6] = 1
        A[7] = 0
        A[8] = 0
        A[9] = 0
        A[10] = 0


    The frog can make 3 jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.

    Write a function:

        class Solution {

                public int solution(int[] A){

                }
        }


    that, given an array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other
    side of the river. If the frog cannot reach the other side of the river, the function should return −1.

    For example, given:

        A[0] = 0
        A[1] = 0
        A[2] = 0
        A[3] = 1
        A[4] = 1
        A[5] = 0
        A[6] = 1
        A[7] = 0
        A[8] = 0
        A[9] = 0
        A[10] = 0

    the function should return 3, as explained above.

    Assume that:


    N is an integer within the range [0..100,000];
    each element of array A is an integer that can have one of the following values: 0, 1.

    Complexity
    ----------

    Expected worst-case time complexity is O(N*log(N));

    Expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */

import java.util.*;

/**
 * Created by Chaklader on 7/6/18.
 */
public class FibFrog {


    /*
     *
     * ALGORITHM
     * ---------
     *
     * to find the minimum steps,
     *
     * i.  if you use the Stack, keep the data in the ascending order
     *
     * ii. if you use the List or Queue, keep the data in the descending order
     * */

    /*
     * Given an array A consisting of N integers, returns the minimum number of
     * jumps by which the frog can get to the other side of the river. If the
     * frog cannot reach the other side of the river, the function should return −1.
     * */

    /*
     * solution - a
     */
    int number = 0;

    private static class Jump {

        int pos;
        int jumps;

        Jump(int pos, int jumps) {

            this.pos = pos;
            this.jumps = jumps;
        }
    }

    public static int solution(int[] A) {


        int N = A.length;
        List<Integer> fibs = getFibonaciNumbers(N + 1);

        boolean[] visited = new boolean[N];

        Stack<Jump> stack = new Stack<Jump>();

        /*
         * pos and number of jumps
         * */
        stack.push(new Jump(-1, 0));

        /*
         * The frog can jump between positions −1 and N (the
         * banks of the river) and every pos containing a leaf.
         * */


        /*
         * ALGORITHM
         * ---------
         *
         * To find the minimum steps to reach the opposite bank,
         *
         * i.    Try to make the longest jump from the current position to reach opposite
         *       bank. If possible, return the jump number as result
         *
         * ii.   Otherwise, remove the current position
         *
         * iii.  Meanwhile, store all the entities with leaves in the stack
         *
         * iv.   Start investigation from the longest entity from the set of current addition
         *
         * v.    Repeat the entire process
         * */
        while (!stack.isEmpty()) {

            Jump curr = stack.pop();

            /*
             * A    = [0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0] and N = 11
             * A    = [0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0] and N = 11
             * fibs = [1, 2, 3, 5, 8, 13]
             * */
            for (int f : fibs) {

                int index = curr.pos + f;

                if (index > N) {
                    break;
                }

                /*
                 * we start from -1, hence, with a jump of (N+1) we
                 * would reached the opposite bank ie [(-1) + (N+1) = N]
                 * index is the index value of array "A"
                 * */
                if (index == N) {
                    return curr.jumps + 1;
                }

                /*
                 * we are at a leaf and this pos is not visited yet
                 * */
                if (A[index] == 1 && !visited[index]) {

                    stack.push(new Jump(index, curr.jumps + 1));
                    visited[index] = true;
                }
            }
            
            
        }

        return -1;
    }


    /*
     * the maximum value for the fibonacci number
     * required is (N+1) where N = A.length
     * */
    public static List<Integer> getFibonaciNumbers(int N) {

        List<Integer> fibs = new ArrayList<Integer>();

        fibs.add(0);
        fibs.add(1);

        int i = 2;

        // 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
        while (fibs.get(fibs.size() - 1) <= N) {

            int value = fibs.get(i - 1) + fibs.get(i - 2);
            fibs.add(value);

            i++;
        }

        fibs.remove(0);
        fibs.remove(1);

        return fibs;
    }


    /*
     * solution - b
     */
    /*
     * ALGORITHM
     * ---------
     *
     * i.   from any point, try to jump to the opposite bank
     *
     * ii.  if not possible, add the leaves matches with fibonacci numbers in the descending order
     *      (largest to the smallest)
     *
     * iii.  store all the data in a list, after th for loop increase the index and keep iterating
     *
     * */
    public static int solution1(int[] A) {

        List<Integer> fibs = getFibonaciNumbers(A.length + 1);
        Collections.reverse(fibs);

        boolean[] visited = new boolean[A.length];
        List<Jump> jumps = new ArrayList<Jump>();

        jumps.add(new Jump(-1, 0));
        Jump current = null;

        int i = 0;

        /*
         * break if no jumps are added in the previous iteration
         * and no more entities to investigate
         * */
        while (i != jumps.size()) {

            current = jumps.get(i);

            /*
             * the fibs are sorted reversely so that
             * we can add the largest spatial jump
             * */
            for (int f : fibs) {


                int index = current.pos + f;
                /*
                 * [(-1) + (N+1) = N] for index basis calculation
                 * to cross just opposite the river
                 * */
                if (index == A.length) {
                    return current.jumps + 1;
                }

                /*
                 * we will be far out of the opposite bank if
                 * this step is taken
                 * */
                else if (index > A.length) {
                    continue;
                }

                /*
                 * no leave or already visited step
                 * */
                else if (A[index] == 0 || visited[index]) {
                    continue;
                }


                /*
                Indexs =  [-1, 7, 4, 9, 11]
                A = [0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0]

                    Indexes Tree
                    ------------

                        -1
                       /  \
                      4   7
                           \
                           9
                           \
                           11
                * */

                jumps.add(new Jump(index, current.jumps + 1));
                visited[index] = true;
            }

            i++;
        }

        return -1;
    }


    /*
     * solution - c
     */
    /*
     * dynamic approach for each cell we memorize the minimal
     * amount of jumps based on the previous cells minimal
     * jumps
     * */
    public static int solution2(int[] A) {


        int N = A.length;
        int[] fibs = new int[N < 2 ? 2 : N + 1];

        fibs[0] = 1;
        fibs[1] = 2;

        /*
         * N = 11
         *
         *  fibs = [1, 2, 3, 5, 8, 13, 0, 0, 0, 0, 0, 0]
         *  A =    [0, 0, 0, 1, 1, 0,  1, 0, 0, 0, 0]
         * */
        int fIndex = 2;

        while (fibs[fIndex - 1] <= N) {

            fibs[fIndex] = fibs[fIndex - 1] + fibs[fIndex - 2];
            fIndex++;
        }

        int result = -1;

        /*
         * N = 11
         *
         * fibs = [1, 2, 3, 5, 8, 13, 0, 0, 0, 0, 0, 0]
         * A =    [0, 0, 0, 1, 1, 0,  1, 0, 0, 0, 0]
         * */
        for (int i = 0; i <= N; i++) {

            if (i == N || A[i] == 1) {

                int min = Integer.MAX_VALUE;

                /*
                 * O(log(n)) because it goes through Fibonacci numbers
                 * before n And the number of them = O(log(n))fIndex = 6,
                 * the size of fibs
                 * */
                for (int j = 0; j < fIndex && fibs[j] <= i + 1; j++) {

                    int from = i - fibs[j];

                    if (from == -1) {
                        min = 1;
                    } else if (A[from] > 0) {

                        if (A[from] + 1 < min) {
                            min = A[from] + 1;
                        }
                    }
                }

                if (i < N) {

                    if (min == Integer.MAX_VALUE) {
                        A[i] = 0;
                    } else {
                        A[i] = min;
                    }
                } else {

                    if (min != Integer.MAX_VALUE) {
                        result = min;
                    }
                }
            }
        }

        return result;
    }


    /**
     * The task is very similar with the coin changing problem. It's a classical dynamic programming problem. The recurrence
     * relation in this case is:
     * <p>
     * K[i] = 0, if i == 0;
     * <p>
     * K[i] = min(j<=F.length)(K[i-F[j]]+1), if i > 0  and ( A[i] == 1 or i == A.length)
     * <p>
     * Where K[i] is a number of jumps on i'th pos F - fibonacci sequence A - the map of banks
     */

    /*
     * solution - d
     * */
    public int solution4(int A[]) {

        if (A.length <= 2) {
            return 1;
        }

        List<Integer> result = getFibonaciNumbers(A.length + 1);
        int[] fibs = result.stream().mapToInt(a -> a).toArray();

        int[] Container = new int[A.length + 2];

        Arrays.fill(Container, -1);
        Container[0] = 0;

        for (int i = 0; i <= A.length; i++) {

            int min = Integer.MAX_VALUE;

            for (int f : fibs) {

                int index = i - f + 1;

                if (index < 0) {
                    break;
                }

                if (Container[index] != -1 && (i == A.length || A[i] == 1)) {
                    min = Math.min(Container[index] + 1, min);
                }
            }

            if (min != Integer.MAX_VALUE) {
                Container[i + 1] = min;
            }
        }

        return Container[A.length + 1];
    }


    /*
     * solution - e
     * */
    /*
     * using the knapsack algorithms
     * */
    public static int solution5(int[] A) {

        int N = A.length;

        List<Integer> list = getFibonaciNumbers(N + 1);

        int size = list.size();
        int[] fibs = new int[size];


        // int[] fibs1 = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025};

        for (int i = 0; i < size; i++) {
            fibs[i] = list.get(i);
        }

        int u = (int) (Math.log10(fibs[fibs.length - 1]) + 1);

//        int v = (int) Math.pow(10, u * u) % (1 << 31);
        int v = 1000000000;

        int[] steps = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            steps[i] = v;
        }

        for (int i = 0; i < fibs.length; i++) {

            int j = fibs[i] - 1;

            if (j < N && A[j] == 1) {
                steps[j] = 1;
            }

            if (j == N) {
                steps[N] = 1;
            }
        }

        for (int i = 0; i < N; i++) {

            if (A[i] == 1) {

                for (int j = 0; j < fibs.length; j++) {

                    int k = i - fibs[j];

                    if (k >= 0 && steps[k] != v && steps[i] > steps[k] + 1) {
                        steps[i] = steps[k] + 1;
                    }
                }
            }
        }


        for (int i = N; i >= 0; i--) {

            for (int j = 0; j < fibs.length; j++) {

                int k = i - fibs[j];

                if (k >= 0 && steps[k] != v && steps[i] > steps[k] + 1) {
                    steps[i] = steps[k] + 1;
                }
            }
        }

        if (steps[N] == -1) {
            return -1;
        }

        return steps[N];
    }



    /*
    * solution - f;  100% correctness provided 
    */
    private static class State {

        int pos;
        int step;

        public State(int pos, int step) {

            this.pos = pos;
            this.step = step;
        }
    }

    public static int solution(int A[]) {

        int N = A.length;

        int f1 = 0;
        int f2 = 1;

        int count = 2;

        while (true) {

            int f3 = f2 + f1;

            if (f3 > N) {
                break;
            }

            f1 = f2;
            f2 = f3;

            ++count;
        }


        int[] fib = new int[count + 1];

        fib[0] = 0;
        fib[1] = 1;

        int i = 2;

        while (true) {

            fib[i] = fib[i - 1] + fib[i - 2];

            if (fib[i] > N) {
                break;
            }

            ++i;
        }

        for (int j = 0, k = count; j < count / 2; j++, k--) {

            int t = fib[j];

            fib[j] = fib[k];
            fib[k] = t;
        }

        State[] q = new State[N];

        for (int j = 0; j < N; j++) {

            q[j] = new State(-1,0);
        }

        int front = 0;
        int rear = 0;

//        q[0].pos = -1;
//        q[0].step = 0;

        int que_s = 1;

        while (que_s > 0) {

            State s = q[front];

            front++;
            que_s--;

            for (i = 0; i <= count; i++) {

                int nextpo = s.pos + fib[i];

                if (nextpo == N) {
                    return s.step + 1;
                }

                //
                else if (nextpo > N || nextpo < 0 || A[nextpo] == 0) {
                    continue;
                }

                //
                else {

                    q[++rear].pos = nextpo;
                    q[rear].step = s.step + 1;

                    que_s++;

                    A[nextpo] = 0;
                }
            }
        }

        return -1;
    }





    /*
    * solution - g
    */
    public class Jump {

        int pos;
        int move;
        public Jump(int pos, int move) {
            this.pos = pos;
            this.move = move;
        }
    }

    public int solution(int[] A) {

        int n = A.length;
        List < Integer > fibs = fibArray(n + 1);
        Queue < Jump > positions = new LinkedList < Jump > ();
        boolean[] visited = new boolean[n + 1];

        if (A.length <= 2)
            return 1;

        for (int i = 0; i < fibs.size(); i++) {
            int initPos = fibs.get(i) - 1;
            if (A[initPos] == 0 || visited[initPos])
                continue;
            positions.add(new Jump(initPos, 1));
            visited[initPos] = true;
        }

        while (!positions.isEmpty()) {
            Jump jump = positions.remove();
            for (int j = fibs.size() - 1; j >= 0; j--) {
                int nextPos = jump.pos + fibs.get(j);
                if (nextPos == n)
                    return jump.move + 1;
                else if (nextPos < n && A[nextPos] == 1 && !visited[nextPos]) {
                    positions.add(new Jump(nextPos, jump.move + 1));
                    visited[nextPos] = true;
                }
            }
        }


        return -1;
    }


    private List < Integer > fibArray(int n) {
        List < Integer > fibs = new ArrayList < > ();
        fibs.add(1);
        fibs.add(2);
        while (fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2) <= n) {
            fibs.add(fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2));
        }
        return fibs;
    }





    public static void main(String[] args) {


        int[] A = new int[11];

        A[0] = 0;
        A[1] = 0;
        A[2] = 0;
        A[3] = 1;
        A[4] = 1;
        A[5] = 0;
        A[6] = 1;
        A[7] = 0;
        A[8] = 0;
        A[9] = 0;
        A[10] = 0;

//        int[] A = {0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0};

        System.out.println(solution5(A));
    }
}
