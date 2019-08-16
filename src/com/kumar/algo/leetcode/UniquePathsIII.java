package com.kumar.algo.leetcode;


//https://leetcode.com/problems/unique-paths-iii/


public class UniquePathsIII {

    public static void main(String[] args) {

        int[][] grid = {{0,1},{2,0}};
        int totalPaths = uniquePathsIII(grid);
        System.out.println(totalPaths);

    }

    public static int uniquePathsIII(int[][] grid) {

        Cell start = null;
        int totalZeros = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(grid[i][j] == 1){
                    start = new Cell(i, j);
                }
                else if(grid[i][j] == 0){
                    totalZeros++;
                }
            }
        }

        return getUniquePaths(grid, start, totalZeros);

    }

    private static int getUniquePaths(int[][] grid, Cell cell, int totalZeros){

        int totalPaths = 0;

        Cell[] cells = getAdjucentCells(grid, cell);

        for(Cell adjCell : cells){
            if(adjCell == null){
                continue;
            }
            int i = adjCell.i;
            int j = adjCell.j;
            if(grid[i][j] == 2 && totalZeros == 0){
                return 1;
            }

            if(grid[i][j] == 0){
                grid[i][j] = -1;
                totalPaths += getUniquePaths(grid, new Cell(i, j), totalZeros-1);
                grid[i][j] = 0;
            }

        }

        return totalPaths;


    }

    private static Cell[] getAdjucentCells(int[][] grid, Cell cell){
        Cell[] cells = new Cell[4];

        int i = cell.i;
        int j = cell.j;
        if(i > 0 && grid[i-1][j] != -1){
            cells[0] = new Cell(i-1, j);
        }

        if(i < grid.length-1 && grid[i+1][j] != -1){
            cells[1] = new Cell(i+1, j);
        }

        if(j > 0 && grid[i][j-1] != -1){
            cells[2] = new Cell(i, j-1);
        }

        if(j < grid[i].length-1 && grid[i][j+1] != -1){
            cells[3] = new Cell(i, j+1);
        }
        return cells;
    }



    private static void print2D(int[][] arr){
        for(int i = 0;i<arr.length; i++){
            for(int j = 0; j<arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
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
