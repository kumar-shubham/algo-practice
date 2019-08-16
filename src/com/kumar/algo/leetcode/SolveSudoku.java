package com.kumar.algo.leetcode;

public class SolveSudoku {

    public static void main(String[] args) {
        char[][] chars = {{'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        solveSudoku(chars);
        printSudoku(chars);
    }

    public static void solveSudoku(char[][] board) {
        System.out.println(solve(board));

    }

    private static boolean solve(char[][] board){

        int[] cell = findNextCell(board);

        int row = cell[0];
        int col = cell[1];
        System.out.println("row: " + row + ", col: " + col);
        if(row == -1 && col == -1){
            return true;
        }
        for(int i = 1; i<10; i++){
            if(isValidCell(board, row, col, i)){
                board[row][col] = (char)(i + '0');
                if(solve(board)){
                   return true;
                }
                board[row][col] = '.';
            }
        }
        return false;

    }

    private static int[] findNextCell(char[][] board){
        int[] cell = {-1,-1};

        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                if(board[i][j] == '.'){
                    cell[0] = i;
                    cell[1] = j;
                    return cell;
                }
            }
        }
        return cell;
    }


    private static boolean isValidCell(char[][] board, int i, int j, int num) {

        char c = (char)(num + '0');
        for(int k = 0; k<board.length; k++){
            if(board[i][k] == c){
                System.out.println(i + ", " + j + ",  " + num);
                return false;
            }
        }

        for(int k = 0; k<board.length; k++){
            if(board[k][j] == c){
                return false;
            }
        }

        int rowOffset = i - (i%3);
        int colOffset = j - (j%3);
        for(int x = 0; x<3; x++){
            int row = x+ rowOffset;
            for(int y = 0; y<3; y++){
                int col = y+colOffset;
                if(board[row][col] == c){
                    return false;
                }
            }
        }
        return true;
    }

    private static void printSudoku(char[][] board){

        for (int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }
}
