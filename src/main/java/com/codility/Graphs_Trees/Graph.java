package com.codility.Graphs_Trees;

import java.util.*;

/*
Adjacency Matrix
----------------

Adjacency Matrix is a 2D array of size V key V where V is the number of vertices in a graph. Let
the 2D array be adj[][], a slot adj[i][j] = 1 indicates that there is an edge from vertex i to
vertex j. Adjacency matrix for undirected graph is always symmetric. Adjacency Matrix is also
used to represent weighted graphs. If adj[i][j] = w, then there is an edge from vertex i to vertex
j with weight w.


Pros
----
Representation is easier to implement and follow. Removing an edge takes O(1) time. Queries like
whether there is an edge from vertex ‘u’ to vertex ‘v’ are efficient and can be done O(1).

Cons
----
Consumes more space O(V^2). Even if the graph is sparse(contains less number of edges), it consumes
the same space. Adding a vertex is O(V^2) time.



Adjacency List
--------------

An array of linked lists is used. Size of the array is equal to the number of vertices. Let the array
be array[]. An entry array[i] represents the linked list of vertices adjacent to the ith vertex. This
representation can also be used to represent a weighted graph. The weights of edges can be stored in
nodes of linked lists. Following is adjacency list representation of the above graph.
* */


/**
 * Created by Chaklader on 7/22/18.
 */
public class Graph {


    /*
     * node for creating graph with adjacent nodes
     * */
    private static class Node {

        public int index;

        private Node adjacent[];

        public Node(int count) {
            index = 0;
            adjacent = new Node[count];
        }

        public void addAdjacent(Node node) {
            this.adjacent[index++] = node;
        }

        public Node[] getAdjacent() {
            return adjacent;
        }
    }


    /*
     * breadth frist search and depth frist search
     * in a graph using the adjacency list
     * */
    private static int count;


    private static LinkedList<Integer>[] adj;

    Graph(int index) {

        this.count = index;
        this.adj = new LinkedList[index];

        for (int i = 0; i < index; ++i) {
            adj[i] = new LinkedList();
        }
    }

    /*
     * function to add an edge into the graph
     * */
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    /*
     * prints performBFS traversal from a given
     * source S
     * */
    public void performBFS(int s) {

        /*
         * mark all the vertices as not visited(By
         * default set as false)
         * */
        boolean visited[] = new boolean[count];

        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {

            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adj[s].listIterator();

            while (i.hasNext()) {

                int n = i.next();

                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }


    public static void performDFS(int v) {

        boolean visited[] = new boolean[count];
        DFSUtil(v, visited);
    }

    public static void DFSUtil(int v, boolean visited[]) {

        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();

        while (i.hasNext()) {

            int n = i.next();

            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }


    /*
     * depth first search in a graph with recursive algorithm
     * */
    public static void depthFirstSearch(int[][] mat, int start) {

        if (start < 0) {
            return;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int cols = mat[0].length;
        int row, col;

        int[] visited = new int[cols];

        visited[start] = 1;
        stack.push(start);

        while (!stack.isEmpty()) {

            row = stack.peek();
            col = row;

            while (col < cols) {

                if (mat[row][col] == 1 && visited[col] == 0) {

                    System.out.print(row + "\t");

                    stack.push(col);
                    visited[col] = 1;

                    row = col;
                    col = 0;

                    continue;
                }

                col++;
            }

            stack.pop();
        }
    }


    public static void depthFirstSearch1(int i, int[][] mat) {

        if (i < 0) {
            return;
        }

        dfsHelper(i, mat, new boolean[mat[0].length]);
    }

    public static void dfsHelper(int row, int[][] mat, boolean[] visited) {

        if (!visited[row]) {

            visited[row] = true;
            System.out.print(row + " ");

            for (int col = 0; col < mat[row].length; col++) {

                if (mat[row][col] == 1 && !visited[col]) {
                    dfsHelper(col, mat, visited);
                }
            }
        }
    }


    /*
     * breadth first search using the adjacency matrix
     * */
    public static void breadthFirstSearch(int[][] mat, int start) {

        if (start < 0) {
            return;
        }

        int cols = mat[0].length;
        int row, col;

        int[] visited = new int[cols];

        Queue<Integer> queue = new LinkedList<Integer>();

        visited[start] = 1;
        queue.add(start);

        while (!queue.isEmpty()) {

            row = queue.remove();
            col = row;

            System.out.print(col + " ");

            while (col < cols) {

                if (mat[row][col] == 1 && visited[col] == 0) {

                    queue.add(col);
                    visited[col] = 1;
                }

                col++;
            }
        }
    }


    /*
     * breadth first search in a graph with recursive algorithm
     * */
    public void recursiveBFS(Node node) {

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);

        recursiveBFSHelper(queue);
    }

    public void recursiveBFSHelper(LinkedList<Node> queue) {

        while (!queue.isEmpty()) {

            Node node = queue.pop();

            System.out.println("Node: " + node);

            Node[] nodes = node.getAdjacent();

            for (Node n : nodes) {
                queue.push(n);
            }

            recursiveBFSHelper(queue);
        }
    }


    public static void main(String[] args) {

        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        g.performBFS(2);
        g.performDFS(2);
    }
}
