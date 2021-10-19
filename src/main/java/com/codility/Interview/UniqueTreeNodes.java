package com.codility.Interview;


import java.util.*;


/*
Q:1

In this problem we consider binary trees. The figure below shows an example binary tree consisting of

seven nodes. A binary tree is either an empty tree or a node (called the root) containing a single integer

value and linked to two further binary trees. We are interested in paths (sequences of linked adjacent nodes)

that start at the root and follow the tree edges (marked as arrows in the figure above). For example, the

sequence of nodes A, B, D is a valid path, but the sequence A, B, G is not. Problem We would like to find the

maximum number of distinct values that appear on a path starting at the root of the tree. For example, on the

path consisting of nodes A, B, D, G there are two distinct values (4 and 5). On the path A, C, E there are

three distinct values (1, 4 and 6). There is no path that contains four or more distinct values.



                    4
                  /  \
                 5    6
                /    / \
               4    1   6
              /
             5


Write a function:
-----------------


    class Solution {

        public int solution(Tree T);
    }


that, given a binary tree T consisting of N nodes, returns the maximum number of distinct values that appear on

a path starting at the root of tree T. For example, given the tree shown above, the function should return 3.

Technical details A binary tree is given using a pointer data structure.




Assume that the following declarations are given:

    class Tree {

        public int key;

        public Tree left;
        public Tree right;
    }


An empty tree is represented by an empty pointer (denoted by null). A non-empty tree is represented by a pointer

to an object representing its root. The attribute key holds the integer contained in the root, whereas attributes

left and right hold the left and right subtrees of the binary tree, respectively. Assumptions Assume that: N is

an integer within the range [1..50,000]; the height of tree T (number of edges on the longest path from root to

leaf) is within the range [0...3,500]; each value in tree T is an integer within the range [1..N]. Complexity:



Time and Space complexity
-------------------------

    i.  expected worst-case time complexity is O(N)

    ii. expected worst-case space complexity is O(N).
*/



/**
 * Created by Chaklader on 8/8/18.
 */
public class UniqueTreeNodes {




    /*
    * solution - a
    */
    private static class Node {

        int data;

        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static List<List<Node>> findAllPaths(List<List<Node>> paths, Node node, List<Node> path) {

        if (node == null) {
            return paths;
        }

        path.add(node);

        if (node.left == null && node.right == null) {

            paths.add(path);
            return paths;
        }

        //
        else {
            findAllPaths(paths, node.left, new ArrayList<>(path));
            findAllPaths(paths, node.right, new ArrayList<>(path));
        }

        return paths;
    }


    public static int solution(Node root) {

        List<List<Node>> paths = findAllPaths(new ArrayList<List<Node>>(), root, new ArrayList<Node>());

        Set<Integer> set;

        int max = Integer.MIN_VALUE;


        for (List<Node> path : paths) {

            set = new HashSet<>();

            for (Node p : path) {
                set.add(p.data);
            }

            System.out.println(Arrays.toString(set.toArray()));
            max = set.size() > max ? set.size() : max;
        }

        return max;
    }
    /*
     * END of solution - a
     */






    private static class Node {

        public int key;

        public Node left;
        public Node right;

        public Node(int key) {
            this.key = key;
        }
    }


    /*
     * solution - a
     * */
    public static int solution(Node root) {


        List<List<Integer>> list = new ArrayList<>();

        findPaths(root, list, new Stack<Node>());
        int max = 0;

        HashSet<Integer> set = null;

        for (List<Integer> l : list) {

            set = new HashSet<>();

            // System.out.println(l);
            set.addAll(l);

            max = max < set.size() ? set.size() : max;
        }

        return max;
    }



    /*
     * find all the paths of a binary search tree
     * */
    private static void findPaths(Node node, List<List<Integer>> lists, Stack<Node> stack) {

        if (node == null) {
            return;
        }

        List<Integer> list = null;
        Node right = null;

        stack.push(node);

        while (node.left != null) {
            node = node.left;
            stack.push(node);
        }

        if (stack.peek().right != null) {
            right = stack.peek().right;
            findPaths(right, lists, stack);
        }

        if (stack.isEmpty()) {
            return;
        }

        list = new ArrayList<>();

        for (Node n : stack) {
            list.add(n.key);
        }

        lists.add(list);

        /*
         *
         * i.    pop till the stack has elements
         * ii.   delete the old left paths that are already included
         * iii.  delete the old right path that are already included
         *
         * */
        while (!stack.isEmpty() && (stack.peek().right == null 
            || stack.peek().right.equals(right))) {

            right = stack.pop();
        }

        if (!stack.isEmpty()) {

            right = stack.peek().right;
            findPaths(right, lists, stack);
        }
    }



    /*
     * solution - b
     * */
    public static int solution1(Node root) {


        List<List<Integer>> list = new ArrayList<>();

        int max = 0;
        HashSet<Integer> set = null;

        List<List<Integer>> p = findPaths1(root);

        for (List<Integer> a : p) {

            set = new HashSet<>();

            System.out.println(a);
            set.addAll(a);

            max = max < set.size() ? set.size() : max;
        }

        return max;
    }



    /* *
     * The money needs to be the one for the working. 
     */
    private static List<List<Integer>> findPaths1(Node node) {


        if (node == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> paths = new ArrayList<List<Integer>>();

        List<List<Integer>> left_subtree = findPaths1(node.left);
        List<List<Integer>> right_subtree = findPaths1(node.right);


        for (int i = 0; i < left_subtree.size(); ++i) {

            List<Integer> new_path = new ArrayList<Integer>();

            new_path.add(node.key);
            new_path.addAll(left_subtree.get(i));
            
            paths.add(new_path);
        }

        for (int i = 0; i < right_subtree.size(); ++i) {

            List<Integer> new_path = new ArrayList<Integer>();

            new_path.add(node.key);
            new_path.addAll(right_subtree.get(i));

            paths.add(new_path);
        }

        if (paths.size() == 0) {

            paths.add(new ArrayList<Integer>());
            paths.get(0).add(node.key);
        }

        return paths;
    }






    /*
     * solution - c
     * */
    public static int solution2(Node root) {


        List<List<Node>> paths = new ArrayList<>();

        List<List<Integer>> M = new ArrayList<>();
        List<Integer> N = new ArrayList<>();

        int max = 0;
        HashSet<Integer> set = null;

        findPaths2(root, paths);

        for (List<Node> nodes : paths) {

            String s = "";

            for (Node node : nodes) {
                s += String.valueOf(node.key);
            }

            System.out.println(s);
        }


//        for (List<Node> path : paths) {
//
//            set = new HashSet<>();
//
//            System.out.println(path);
//            set.addAll(path);
//
//            max = max < set.size() ? set.size() : max;
//        }

        return max;
    }


    public static void findPaths2(Node root, List<List<Node>> paths) {


        if (root == null) {
            return;
        }

        List<Node> path = new ArrayList<>();

        if (paths.size() > 0) {

            for (List<Node> p : paths) {

                if (p.get(p.size() - 1).equals(root)) {
                    path = p;
                }
            }
        }

        if (paths.size() > 0 && path == null) {
            return;
        }

        path.add(root);
        paths.add(path);

        //        if (root.left == null && root.right == null) {
        //            paths.add(path);
        //        }

        if (root.left != null) {
            findPaths2(root.left, paths);
        }

        if (root.right != null) {
            findPaths2(root.right, paths);
        }

        path.remove(path.size() - 1);
    }


    /*
     * solution - d
     * */
    private static class Tree {


        public int key;

        public Tree left;
        public Tree right;

        boolean visited;

        Tree(int v) {
            this.key = v;
            this.visited = false;
        }
    }

    public static int solution2(Tree root) {

        int result = 0;
        List<Integer> list = null;

        List<ArrayList<Integer>> paths = findPaths3(root);

        for (ArrayList<Integer> path : paths) {

            list = new ArrayList<>();

            for (int p : path) {

                if (list.contains(p)) {
                    continue;
                }

                list.add(p);
            }

            System.out.println(list);

            result = Math.max(result, list.size());
        }

        return result;
    }




    /*
     * find all the paths of a binary tree
     * */
    private static List<ArrayList<Integer>> findPaths3(Tree root) {


        Stack<Tree> stack = new Stack<>();
        List<ArrayList<Integer>> paths = new ArrayList<>();

        stack.push(root);
        root.visited = true;


        while (!stack.isEmpty()) {

            Tree node = stack.peek();

            if (node.left != null && !node.left.visited) {

                stack.push(node.left);
                node.left.visited = true;
            } 

            else {

                if (node.right == null) {

                    ArrayList<Integer> list = new ArrayList<>();

                    for (Tree t : stack) {
                        list.add(t.key);
                    }

                    paths.add(list);
                    stack.pop();
                } 

                else if (node.right != null && !node.right.visited) {
                    stack.push(node.right);
                    node.right.visited = true;
                } 

                else {
                    stack.pop();
                }
            }
        }

        return paths;
    }


    public static void main(String[] args) {


        /*
                    4
                  /  \
                 5    6
                /    / \
               4    1   6
              /
             5

                    4
                  /  \
                 5    6
                /    / \
               4    1   6
              / \
             5  12
                 \
                 13

        * */

        Node root = new Node(4);

        root.left = new Node(5);
        root.left.right = new Node(15);
        root.left.right.left = new Node(115);
        root.left.right.right = new Node(215);


        root.left.left = new Node(4);


        root.left.left.right = new Node(12);
        root.left.left.right.right = new Node(13);


        root.left.left.left = new Node(5);

        root.right = new Node(6);
        root.right.left = new Node(1);
        root.right.right = new Node(6);


        solution2(root);


//        Tree root = new Tree(4);
//
//        root.left = new Tree(5);
//        root.left.right = new Tree(15);
//        root.left.right.left = new Tree(115);
//        root.left.right.right = new Tree(215);
//
//
//        root.left.left = new Tree(4);
//
//
//        root.left.left.right = new Tree(12);
//        root.left.left.right.right = new Tree(13);
//
//
//        root.left.left.left = new Tree(5);
//
//        root.right = new Tree(6);
//        root.right.left = new Tree(1);
//        root.right.right = new Tree(6);
//
//        System.out.println(solution2(root));
    }
}
