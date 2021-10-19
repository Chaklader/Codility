package com.codility.Interview;


/*
A network consisting of M cities and M − 1 roads connecting them is given. Cities are labeled

with distinct integers within the range [0..(M − 1)]. Roads connect cities in such A way that

each pair of distinct cities is connected either by A direct road or along a path consisting

of direct roads. There is exactly one way to reach any city from any other city. In other words,

cities and direct roads form a tree. The number of direct roads that must be traversed is called

the distance between these two cities. For example, consider the following network consisting of

ten cities and nine roads: Cities 2 and 4 are connected directly, so the distance between them

is 1. Cities 4 and 7 are connected by a path consisting of the direct roads 4−0, 0−9 and 9−7;

Hence the distance between them is 3. One of the cities is the capital, and the goal is to count

the number of cities positioned away from it at each of the distances 1, 2, 3, ..., M − 1. If

city number 1 is the capital, then the cities positioned at the various distances from the capital

would be as follows: 9 is at a distance of 1; 0, 3, 7 are at a distance of 2; 8, 4 are at A

distance of 3; 2, 5, 6 are at A distance of 4. Write A function:



        class Solution { 

            public int[] solution(int[] T); 
        } 


That, given a non-empty array T consisting

of M integers describing a network of M cities and M − 1 roads, returns an array consisting of

M − 1 integers, specifying the number of cities positioned at each distance 1, 2, ..., M − 1.

Array T describes A network of cities as follows: if T[P] = Q and P = Q, then P is the capital;

if T[P] = Q and P ≠ Q, then there is a direct road between cities P and Q. For example, given

the following array T consisting of ten elements:



    T[children] = parent


    T[0] = 9

    T[1] = 1

    T[2] = 4

    T[3] = 9

    T[4] = 0

    T[5] = 4

    T[6] = 8

    T[7] = 9

    T[8] = 0

    T[9] = 1


                 1
                 |
                 9
               / | \
              3  0  7
                / \
               4   8
             / \  /
            2  5 6


the function should return [1, 3, 2, 3, 0, 0, 0, 0, 0], as explained above. Assume that: M is a
n integer within the range

[1..100,000]; each element of array T is an integer within the range [0..M−1]; there is exactly

one (possibly indirect) connection between any two distinct cities. Complexity:




Expected worst-case time complexity is O(M); expected worst-case space complexity is O(M)

(not counting the storage required for input arguments).
*/


import java.util.*;

/**
 * Created by Chaklader on 7/5/18.
 */
public class CountCities {




    private static class Node {

        private int key;
        private ArrayList<Node> adjacents;

        public Node(int v) {

            this.key = v;
            this.adjacents = new ArrayList<Node>();
        }

        public int getKey() {
            return this.key;
        }

        public void addAdjacentNode(Node node) {
            this.adjacents.add(node);
        }

        public ArrayList<Node> getAdjacents() {
            return this.adjacents;
        }
    }


    private static Node root = null;

    public static Node createGraph(int[] T) {

        int N = T.length;

        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < T.length; ++i) {

            if (T[i] == i) {
                root = nodes[i];
            } 

            else {
                nodes[T[i]].addAdjacentNode(nodes[i]);
            }
        }

        return root;
    }


    /*
     * get the nodes in the graph same distances from the capital using BSF
     * */
    public static ArrayList<LinkedList<Node>> getSameLevelNodes(Node root) {


        ArrayList<LinkedList<Node>> result = new ArrayList<LinkedList<Node>>();
        LinkedList<Node> current = new LinkedList<Node>();

        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {

            result.add(current); // Add previous level
            LinkedList<Node> parents = current; // Go to next level

            current = new LinkedList<Node>();

            for (Node node : parents) {
                current.addAll(node.getAdjacents());
            }
        }

        result.remove(0);
        return result;
    }


    /*
     * get the nodes in the graph same distances from the capital using DSF
     * */
    public static ArrayList<LinkedList<Node>> getSameLevelNodes1(Node root) {

        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();

        getSameLevelNodes1(root, lists, 0);
        lists.remove(0);

        return lists;
    }


    public static void getSameLevelNodes1(Node node, ArrayList<LinkedList<Node>> lists, int level) {

        /*
        ALGORITHM
        ---------

            i.   if the list size is the same as the level, create a new instance of list

            ii.  otherwise, get the list using the level

            iii. add the node in the list

            iv. continue the recursive process till where is no child
        */
        if (node == null) {
            return;
        }

        LinkedList<Node> list = null;

        if (lists.size() == level) {

            list = new LinkedList<Node>();
            lists.add(list);
        } 

        else {
            list = lists.get(level);
        }

        list.add(node);

        for (Node node : node.getAdjacents()) {
            getSameLevelNodes1(node, lists, level + 1);
        }
    }


    /*
     * solution - a
     * */
    public static int[] solution(int[] T) {


        Node root = createGraph(T);
        ArrayList<LinkedList<Node>> v = getSameLevelNodes(root);


        int index = 0;
        int N = T.length;

        int[] result = new int[N - 1];

        for (LinkedList<Node> l : v) {

            // int count = 0;

            // for (Node node : l) {
            //     count++;
            // }

            // result[index++] = count;

            result[index++] = l.size();
        }

        return result;
    }


    /*
     * solution - b
     * */
    public static int[] solution1(int[] T) {


        Node root = createGraph(T);
        int N = T.length;

        int[] result = new int[N - 1];

        List<Node> parent = new ArrayList<>();
        List<Node> children = null;

        for (Node node : root.getAdjacents()) {
            parent.add(node);
        }

        int index = 0;

        while (true) {

            result[index++] = parent.size();

            children = new ArrayList<>();

            for (Node node : parent) {

                if (node.getAdjacents() == null) {
                    continue;
                }

                children.addAll(node.getAdjacents());
            }

            if (children.size() == 0) {
                break;
            }

            parent = children;
        }

        return result;
    }

    public static void main(String[] args) {


        /*
                 1
                 |
                 9
               / | \
              3  0  7
                / \
               4   8
             / \  /
            2  5 6
        * */


        int[] T = new int[10];

        T[0] = 9;
        T[1] = 1;
        T[2] = 4;
        T[3] = 9;
        T[4] = 0;
        T[5] = 4;
        T[6] = 8;
        T[7] = 9;
        T[8] = 0;
        T[9] = 1;

        int[] result = solution(T);
        int[] result1 = solution1(T);

        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result1));
    }
}
