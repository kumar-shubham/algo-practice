package com.kumar.algo.foobar.level2;

/*

Don't Get Volunteered!
======================

As a henchman on Commander Lambda's space station, you're expected to be resourceful, smart, and a quick thinker. It's not easy building a doomsday device and capturing bunnies at the same time, after all! In order to make sure that everyone working for her is sufficiently quick-witted, Commander Lambda has installed new flooring outside the henchman dormitories. It looks like a chessboard, and every morning and evening you have to solve a new movement puzzle in order to cross the floor. That would be fine if you got to be the rook or the queen, but instead, you have to be the knight. Worse, if you take too much time solving the puzzle, you get "volunteered" as a test subject for the LAMBCHOP doomsday device!

To help yourself get to and from your bunk every day, write a function called solution(src, dest) which takes in two parameters: the source square, on which you start, and the destination square, which is where you need to land to solve the puzzle.  The function should return an integer representing the smallest number of moves it will take for you to travel from the source square to the destination square using a chess knight's moves (that is, two squares in any direction immediately followed by one square perpendicular to that direction, or vice versa, in an "L" shape).  Both the source and destination squares will be an integer between 0 and 63, inclusive, and are numbered like the example chessboard below:

-------------------------
| 0| 1| 2| 3| 4| 5| 6| 7|
-------------------------
| 8| 9|10|11|12|13|14|15|
-------------------------
|16|17|18|19|20|21|22|23|
-------------------------
|24|25|26|27|28|29|30|31|
-------------------------
|32|33|34|35|36|37|38|39|
-------------------------
|40|41|42|43|44|45|46|47|
-------------------------
|48|49|50|51|52|53|54|55|
-------------------------
|56|57|58|59|60|61|62|63|
-------------------------

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Python cases --
Input:
solution.solution(0, 1)
Output:
3

Input:
solution.solution(19, 36)
Output:
1

-- Java cases --
Input:
Solution.solution(19, 36)
Output:
1

Input:
Solution.solution(0, 1)
Output:
3

*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DontGetVolunteered {

    public static void main(String[] args) {

        int count = solution(19,36);
        System.out.println(count);
    }

    public static int solution(int src, int dest) {

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        queue.add(-1);
        int count = 0;
        while (!queue.isEmpty()){
            int curr = queue.remove();
            if(curr == dest){
                break;
            }
            if(curr == -1){
                count++;
                queue.add(-1);
                continue;
            }
            int[] moves = getNextMoves(curr);
            int i = 0;
            for(int move : moves){
                if(move == -1){
                    continue;
                }
                if(!visited.contains(move)){
                    i++;
                    queue.add(move);
                    visited.add(move);
                }
            }
//            System.out.println(queue);

        }

        return count;
    }

    private static int[] getNextMoves(int src){

        int[] moves = new int[8];

        int[] cell = fromCellToRowCol(src, 8);

        int index = 0;

        int rightCol = cell[1]+2;
        if(isValid(rightCol)){
            int row1 = cell[0]-1;
            int row2 = cell[0]+1;
            if(isValid(row1)){
//                System.out.println(1);
                moves[index++] = fromRowColToCell(row1, rightCol, 8);
            }
            if(isValid(row2)){
//                System.out.println(2);
                moves[index++] = fromRowColToCell(row2, rightCol, 8);
            }
        }

        int leftCol = cell[1]-2;
        if(isValid(leftCol)){
            int row1 = cell[0]-1;
            int row2 = cell[0]+1;
            if(isValid(row1)){
//                System.out.println(3);
                moves[index++] = fromRowColToCell(row1, leftCol, 8);
            }
            if(isValid(row2)){
//                System.out.println(4);
                moves[index++] = fromRowColToCell(row2, leftCol, 8);
            }
        }

        int upRow = cell[0]-2;
        if(isValid(upRow)){
            int col1 = cell[1]-1;
            int col2 = cell[1]+1;
            if(isValid(col1)){
//                System.out.println(5);
                moves[index++] = fromRowColToCell(upRow, col1, 8);
            }
            if(isValid(col2)){
//                System.out.println(6);
                moves[index++] = fromRowColToCell(upRow, col2, 8);
            }
        }

        int downRow = cell[0]+2;
        if(isValid(downRow)){
            int col1 = cell[1]-1;
            int col2 = cell[1]+1;
            if(isValid(col1)){
//                System.out.println(7);
                moves[index++] = fromRowColToCell(downRow, col1, 8);
            }
            if(isValid(col2)){
//                System.out.println(8);
                moves[index++] = fromRowColToCell(downRow, col2, 8);
            }
        }

        for(int i = index; i<8; i++){
            moves[i] = -1;
        }

        return moves;

    }

    private static boolean isValid(int row){
        return row >=0 && row <= 7;
    }



    private static int[] fromCellToRowCol(int cell, int n){
        int row = cell/n;
        int col = cell %n;

        int[] pair = {row, col};
        return pair;
    }

    private static int fromRowColToCell(int row, int col, int n){
        return row*n + col;
    }

    private static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
