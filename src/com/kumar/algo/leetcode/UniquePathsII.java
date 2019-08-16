package com.kumar.algo.leetcode;


//https://leetcode.com/problems/unique-paths-ii/

import com.sun.org.apache.bcel.internal.generic.RET;

public class UniquePathsII {

    public static void main(String[] args) {
//        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] grid = {{0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},
                {1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1},
                {0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
                {0,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0},
                {1,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0},
                {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0},
                {0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                {1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0},
                {0,1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1},
                {1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,1},
                {0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,1},
                {1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1},
                {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0}};
        int totalPaths = uniquePathsWithObstacles(grid);
        System.out.println(totalPaths);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int row = obstacleGrid.length;
        int col = obstacleGrid[row-1].length;
        if(row == 1 && col == 1
        && obstacleGrid[0][0] == 0){
            return 1;
        }

        if(obstacleGrid[0][0] == 1 || obstacleGrid[row-1][col-1] == 1){
            return 0;
        }

        Cell start = new Cell(0,0);

        return getPaths(obstacleGrid, start);
    }

    private static int getPaths(int[][] grid, Cell cell){

        int totalPaths = 0;


        Cell[] cells = getAdjucentCells(grid, cell);

        for(Cell adjCell : cells){
            if(adjCell == null){
                continue;
            }
            int i = adjCell.i;
            int j = adjCell.j;
            if(i == grid.length-1 && j == grid[i].length-1){
                totalPaths++;
            }
            else {
                grid[i][j] = 1;
                totalPaths += getPaths(grid, new Cell(i, j));
                grid[i][j] = 0;
            }
        }

        return totalPaths;

    }

    private static Cell[] getAdjucentCells(int[][] grid, Cell cell){
        Cell[] cells = new Cell[2];

        int i = cell.i;
        int j = cell.j;

        if(i < grid.length-1 && grid[i+1][j] != 1){
            cells[0] = new Cell(i+1, j);
        }

        if(j < grid[i].length-1 && grid[i][j+1] != 1){
            cells[1] = new Cell(i, j+1);
        }
        return cells;
    }

    static class Cell{
        int i;
        int j;
        public Cell(int i,  int j){
            this.i = i;
            this.j = j;
        }
    }

}
