package maze;

import java.util.ArrayList;
import java.util.List;

public class Maze {

    private static List<int[]> path = new ArrayList<>();

    public static boolean solveMaze(int[][] maze, int startRow, int startCol, int endRow, int endCol) {
        long startTime = System.currentTimeMillis();

        boolean result = dfs(maze, startRow, startCol , endRow, endCol);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken: " + elapsedTime + " milliseconds");

        if (result) {
            System.out.println("Path: " + formatPath(path));
            System.out.println("Path Length: " + path.size());
        } else {
            System.out.println("No path found.");
        }

        return result;
    }

    private static boolean dfs(int[][] maze, int row, int col, int endRow, int endCol) {
        if (row == endRow && col == endCol) {
            return true;
        }
        maze[row][col] = 2;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = row + dr[i];
            int newCol = col + dc[i];

            if (isValid(maze, newRow, newCol) && maze[newRow][newCol] == 0) {
                path.add(new int[]{newRow, newCol});
                if (dfs(maze, newRow, newCol, endRow, endCol)) {
                    return true;
                }

                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    private static boolean isValid(int[][] maze, int row, int col) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private static String formatPath(List<int[]> path) {
        StringBuilder sb = new StringBuilder("[");
        for (int[] point : path) {
            sb.append("(").append(point[0]).append(", ").append(point[1]).append("), ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
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
        boolean result = solveMaze(maze1, startRow, startCol, endRow, endCol);
        System.out.println("Can the maze be solved? " + result);

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
    
        result = solveMaze(maze2, startRow, startCol, endRow, endCol);
        System.out.println("Can the maze be solved? " + result);

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

        result = solveMaze(maze3, startRow, startCol, endRow, endCol);
        System.out.println("Can the maze be solved? " + result);
    }
}
