package com.codility.Indeed_Prime_2016_Challenge;


/*
A halfling is searching for treasure hidden in a maze in the dwarfs' mine. He has a map of the maze
and would like to find the shortest path to the treasure.

The maze has a specific shape. It is placed on a square grid with M2 cells, where M = 2N+1+1 for som
e given size N. Each cell has coordinates (key, y), where 0 ≤ key, y < M, and can either be empty or contain
a rock.

The mazes of sizes N = 1 and N = 2 are presented in the pictures below:


A maze of size N is constructed recursively from the layout of the maze of size N−1 (like the Hilber
t curve). It contains four mazes of size N−1, each maze in one quarter. The maze in the bottom-left quart
er is rotated by 90 degrees clockwise and the maze in the bottom-right quarter is rotated by 90 degrees counter-clockwise. The mazes in the top quarters are not rotated. There are three additional rocks (squares marked in green in the picture below) in the areas where the mazes intersect. The construction of the maze of size N = 3 is shown below:


The halfling would like to reach the treasure in the smallest number of steps possible. At each step
, he can move to any one of the four adjacent cells (north, south, west, east) that does not contain a ro
ck and is not outside of the grid.



For example, given N = 1, the halfling needs 8 steps to move from cell (2, 1) to cell (3, 4):



Write a function:





class Solution { public int solution(int N, int A, int B, int C, int D); }





that, given the size of the maze N, coordinates of the halfling (A, B) and coordinates of the treasu
re (C, D), returns the minimum number of steps required for the halfling to reach the treasure.




Examples
--------

Given N = 1, A = 2, B = 1, C = 3 and D = 4 the function should return 8, as shown above.

Given N = 2, A = 2, B = 5, C = 6 and D = 6 the function should return 7:

Given N = 3, A = 6, B = 6, C = 10 and D = 13 the function should return 39:





Assume that:

N is an integer within the range [1..25];
A, B, C, D are integers within the range [0..2N+1];
cells (A, B) and (C, D) do not contain rocks;
the result will be an integer smaller than 2,147,483,647.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N).
*/
public class HilbertMaze {


/*
Every points location can be defined by an array of quadrants and their orientation (it will have N
elements) - each element representing the quadrant in the previous quadrant. The whole maze having u
pwards orientations

You need to define this array for both points. For example: if N = 2 and the point is in the lower left
eft quadrant then it will have the orientation to the left. We take this quadrant and we rotate our
coordinate system so it will the same orientation. This way we define the next quadrant and orientation
 pair in our new system. So if we have our point in the lower left quadrant then it will have orientation
 to the left, but as this was relative to our previous orientation (which was also to the left) this will
 become an upwards orientation.


At this point we have all the quadrants and orientation down to the smallest maze that contains our
point. From backwards (from the smallest maze) we need to solve them. Every maze can be solved by th
e following rules:





if our point in our current quadrant is on any of the extremes (meaning that any of the coordinate's
 components are either the lowest or highest of the quadrant) we leave it where it was, otherwise che
 ck next points

if our point is downwards or at the middle of the current quadrant then we move to the quadrants low
est middle point (these goes relative the previously defined orientation, i.e.: if our orientation i
s upwards then we will move our point at the topmost middle point)

if our point is upwards (in the relative direction) we will have to move it to the topmost middle po
int


Storing these moves, we check if we have any common elements in the two array belonging to the two p
oints:


if not we calculate the distance between the two endpoints and the we add up all the distances from
the two moves list (in this list every distance can be calculated as coordinate component subtractio
ns, i.e.: abs(x1-x2) + abs(y1-y2))

if we have common elements then we delete every move after that including the common elements and we
 calculate the distance as mentioned at the point before

 * */


    /*
     * Swift implementation:
     *
     * [https://app.codility.com/demo/results/training9WWFXU-EWC/]
     * */


}


