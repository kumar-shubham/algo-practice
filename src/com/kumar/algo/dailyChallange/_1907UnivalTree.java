package com.kumar.algo.dailyChallange;

/*

A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

0
/ \
1   0
/ \
1   0
/ \
1   1

*/


public class _1907UnivalTree {

    public static void main(String[] args) {

        Node root = new Node(0);
        Node a = new Node(1);
        Node b = new Node(0);
        Node c = new Node(1);
        Node d = new Node(0);
        Node e = new Node(1);
        Node f = new Node(1);

        root.left = a;
        root.right = b;
        b.left = c;
        b.right = d;
        c.left = e;
        c.right = f;

        Pair pair = solve(root);

        System.out.println(pair.count);

    }


    private static Pair solve(Node root){

        if(root.left == null && root.right == null){
            return new Pair(1, true);
        }

        Pair left = null;
        Pair right = null;

        if(root.left != null){
            left = solve(root.left);
        }
        if(root.right != null){
            right = solve(root.right);
        }

        int count = 0;
        boolean isUnival = false;
        if(left != null){
            count = left.count;
            isUnival = left.isUniVal && root.val == root.left.val;
        }

        if(right != null){
            count += right.count;
            isUnival = isUnival && right.isUniVal && root.val == root.right.val;
        }

        if(isUnival){
            count++;
        }

        return new Pair(count, isUnival);




    }

    static class Pair{
        int count;
        boolean isUniVal;

        public Pair(int count, boolean isUniVal){
            this.count = count;
            this.isUniVal = isUniVal;
        }
    }

    static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val = val;
        }
    }
}
