package com.kumar.algo.leetcode;

public class ValidSudoku {

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

        System.out.println(isValidSudoku(chars));

    }

    public static  boolean isValidSudoku(char[][] board) {

        int l = board.length;
        for(int i = 0; i<l; i++){
            for(int j = 0; j<l; j++){
                if(board[i][j] != '.' && !isValidCell(board, i, j)){
                    return false;
                }
            }
        }

        return true;

    }

    private static boolean isValidCell(char[][] board, int i, int j) {

        char c = board[i][j];
        for(int k = 0; k<board.length; k++){
            if(k != j){
                if(board[i][k] == c){
                    System.out.println(i + ", " + j + "," + c);
                    return false;
                }
            }
        }

        for(int k = 0; k<board.length; k++){
            if(k != i){
                if(board[k][j] == c){
                    return false;
                }
            }
        }

        int rowOffset = i - (i%3);
        int colOffset = j - (j%3);
        for(int x = 0; x<3; x++){
            int row = x+ rowOffset;
            for(int y = 0; y<3; y++){
                int col = y+colOffset;
                if(row != i && col != j){
                    if(board[row][col] == c){
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
