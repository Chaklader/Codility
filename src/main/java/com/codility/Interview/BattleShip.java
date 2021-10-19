You and a friend are playing a round of the popular board game Battleship. You'd like to write a program for handling your opponent's moves.

You are given grid, a square matrix of characters representing your game board with your ships arranged on it. For each cell, grid[i][j] = '.' means the cell is empty, while grid[i][j] = '#' means the cell is occupied by a ship. The board must be arranged according to the following rules:

Each ship occupies between 1 and 5 consecutive cells;
Each ship must be oriented in a straight line, vertically or horizontally;
Each ship must be surrounded by empty water cells. The ships may not touch or overlap, even in a diagonal direction.
boat placement examples

You are also given an array of 2-element arrays representing the coordinates of each of the opponents' moves (ie: the cells they are attacking). More formally, the ith move targets the cell grid[moves[i][0]][moves[i][1]]. If there's a boat on that tile, it gets hit, otherwise the move is a miss. A ship is considered to have been sunk once all of the cells it occupies have been hit.

Your task is to return a list of results for each move, according to the following rules:

If the target cell is empty, return "Miss";
If the target cell is occupied by a ship, but the ship is not sunk yet, return "Hit";
If the move sinks a ship, but not all the ships on the grid have been sunk, return "Dead";
If the move sinks the final ship, return "Game over" and end the game (do not return anything else for any remaining moves).
Return the verdicts as an array of strings. It is guaranteed that there is at least one ship on the grid.

Example

For
grid = [['#', '.', '#'],
        ['#', '.', '.'],
        ['.', '.', '#']]
and

moves = [[0, 0], 
         [0, 1],
         [0, 2],
         [1, 0],
         [1, 2],
         [2, 2],
         [1, 1]]
the output should be battleshipGame(grid, moves) = ["Hit", "Miss", "Dead", "Dead", "Miss", "Game over"].

example

Input/Output

[execution time limit] 3 seconds (java)

[input] array.array.char grid

A two-dimensional array of chars representing the layout of your Battleship game board. It is guaranteed that the array contains only the characters '.' and '#'.

Guaranteed constraints:
1 ≤ grid.length ≤ 50,
grid[i].length = grid.length.

[input] array.array.integer moves

A two-dimensional array of integers representing the moves of your opponent. Each move is a unique pair of coordinates on the grid.

Guaranteed constraints:
0 ≤ moves.length ≤ grid.length · grid.length,
moves[i].length = 2,
0 ≤ moves[i][j] < grid.length.

[output] array.string

A list of verdicts for each of your opponents moves, up to the end of the game.
[Java] Syntax Tips

// Prints help message to the console
// Returns a string
// 
// Globals declared here will cause a compilation error,
// declare variables inside the function instead!
String helloWorld(String name) {
    System.out.println("This prints to the console when you Run Tests");
    return "Hello, " + name;
}
