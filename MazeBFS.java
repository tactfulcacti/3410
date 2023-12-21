package maze;

import java.util.LinkedList;
import java.util.Queue;

public class MazeBFS {

    public static boolean solveMazeBFS(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        int rows = maze.length;
        int cols = maze[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] parent = new int[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        boolean pathFound = false;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int i = 0; i < 4; i++) {
                int newRow = row + dr[i];
                int newCol = col + dc[i];

                if (isValid(maze, visited, newRow, newCol)) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    parent[newRow][newCol] = row * cols + col;

                    if (newRow == endRow && newCol == endCol) {
                        pathFound = true;
                        break;
                    }
                }
            }
        }

        if (pathFound) {
            printPath(parent, startRow * cols + startCol, endRow * cols + endCol, cols);
            return true;
        } else {
            System.out.println("No path found.");
            return false;
        }
    }

    private static boolean isValid(int[][] maze, boolean[][] visited, int row, int col) {
        int rows = maze.length;
        int cols = maze[0].length;
        return row >= 0 && row < rows && col >= 0 && col < cols && maze[row][col] == 0 && !visited[row][col];
    }

    private static void printPath(int[][] parent, int start, int end, int cols) {
        int current = end;
        int length = 0;

        while (current != start) {
            int row = current / cols;
            int col = current % cols;
            length++;
            System.out.print("(" + row + ", " + col + ") ");
            current = parent[row][col];
        }

        System.out.print("(" + start / cols + ", " + start % cols + ")");
        System.out.println("\nPath Length: " + length);
    }

    
    public static void main(String[] args) {
        int[][] maze1 = {
                {0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int startRow = 0;
        int startCol = 0;
        int endRow = 4;
        int endCol = 4;
        
        long startTime = System.currentTimeMillis();
        boolean result = solveMazeBFS(maze1, startRow, startCol, endRow, endCol);
        long endTime = System.currentTimeMillis();

        System.out.println("Can the maze be solved? " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");

        int[][] maze2 = {
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
        };
        startRow = 0;
        startCol = 0;
        endRow = 9;
        endCol = 9;

        startTime = System.currentTimeMillis();
        result = solveMazeBFS(maze2, startRow, startCol, endRow, endCol);
        endTime = System.currentTimeMillis();

        System.out.println("Can the maze be solved? " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");

        int[][] maze3 = {
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0},
            {1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0},
        };

         startRow = 0;
         startCol = 0;
         endRow = 18;
         endCol = 18;
        
         startTime = System.currentTimeMillis();
         result = solveMazeBFS(maze3, startRow, startCol, endRow, endCol);
         endTime = System.currentTimeMillis();

        System.out.println("Can the maze be solved? " + result);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");
        
    
    }
}



