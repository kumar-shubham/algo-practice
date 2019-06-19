package com.kumar.algo.foobar.level3;

/*
Prepare the Bunnies' Escape
        ===========================

        You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny prisoners, but once they're free of the prison blocks, the bunnies are going to need to escape Lambda's space station via the escape pods as quickly as possible. Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies. Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies. Unfortunately (again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path, both to maintain structural integrity of the station and to avoid arousing Commander Lambda's suspicions.

        You have maps of parts of the space station, each starting at a prison exit and ending at the door to an escape pod. The map is represented as a matrix of 0s and 1s, where 0s are passable space and 1s are impassable walls. The door out of the prison is at the top left (0,0) and the door into an escape pod is at the bottom right (w-1,h-1).

        Write a function solution(map) that generates the length of the shortest path from the prison door to the escape pod, where you are allowed to remove one wall as part of your remodeling plans. The path length is the total number of nodes you pass through, counting both the entrance and exit nodes. The starting and ending positions are always passable (0). The map will always be solvable, though you may or may not need to remove a wall. The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; no diagonal moves are allowed.

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
        solution.solution([[0, 1, 1, 0], [0, 0, 0, 1], [1, 1, 0, 0], [1, 1, 1, 0]])
        Output:
        7

        Input:
        solution.solution([[0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 0], [0, 0, 0, 0, 0, 0], [0, 1, 1, 1, 1, 1], [0, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0]])
        Output:
        11

        -- Java cases --
        Input:
        Solution.solution({{0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 0, 0}, {1, 1, 1, 0}})
        Output:
        7

        Input:
        Solution.solution({{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}})
        Output:
        11

*/

import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PrepareTheBunniesEscape {

    public static void main(String[] args) {

        int[][] map = {{0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 0}};

        int distance = solution(map);
        System.out.println(distance);


    }

    public static int solution(int[][] map) {

        Set<Node> visited = new HashSet<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, false));
        int a = 999999;

        int m = map.length;
        int n = map[0].length;

        while (!queue.isEmpty()){
            Node node = queue.remove();
            if(node.row == m-1 && node.col == n-1){
                return node.distance;
            }
            Node[] adjNodes = getAdjacentNodes(map, node);
            for(Node adjNode : adjNodes){
                if(adjNode == null){
                    continue;
                }
                if(!visited.contains(adjNode)){
                    visited.add(adjNode);
                    queue.add(adjNode);
                }
            }
        }
        return -1;

    }

    private static Node[] getAdjacentNodes(int[][] map, Node node){

        int leftCol = node.col -1;
        int rightCol = node.col+1;

        int upRow = node.row-1;
        int downROw = node.row+1;

        Node[] result = new Node[4];

        if(isValid(leftCol, map[0].length) && !(map[node.row][leftCol] == 1 && node.pathIncludesOne)){
            boolean pathIncludesOne = map[node.row][leftCol] == 1 || node.pathIncludesOne;
            Node left = new Node(node.row, leftCol, node.distance+1, pathIncludesOne);
            result[0] = left;
        }

        if(isValid(rightCol, map[0].length) && !(map[node.row][rightCol] == 1 && node.pathIncludesOne)){
            boolean pathIncludesOne = map[node.row][rightCol] == 1 || node.pathIncludesOne;
            Node right = new Node(node.row, rightCol, node.distance+1, pathIncludesOne);
            result[1] = right;
        }

        if(isValid(upRow, map.length) && !(map[upRow][node.col] == 1 && node.pathIncludesOne)){
            boolean pathIncludesOne = map[upRow][node.col] == 1 || node.pathIncludesOne;
            Node up = new Node(upRow, node.col, node.distance+1, pathIncludesOne);
            result[2] = up;
        }

        if(isValid(downROw, map.length) && !(map[downROw][node.col] == 1 && node.pathIncludesOne)){
            boolean pathIncludesOne = map[downROw][node.col] == 1 || node.pathIncludesOne;
            Node down = new Node(downROw, node.col, node.distance+1, pathIncludesOne);
            result[3] = down;
        }
        return result;

    }

    private static boolean isValid(int index, int m){
        return (index < m && index >= 0);
    }

    static class Node{
        public int row;
        public int col;
        public int distance;
        public boolean pathIncludesOne;

        Node(int row, int col, int distance, boolean pathIncludesOne){
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.pathIncludesOne = pathIncludesOne;
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof Node){
                Node that = (Node) obj;
                if(this.row == that.row && this.col == that.col && this.pathIncludesOne == that.pathIncludesOne){
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode(){
            int prime = 31;
            int result = row*prime;
            result = result*col;
            return result;
        }
    }
}
