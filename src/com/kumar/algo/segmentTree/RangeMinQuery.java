package com.kumar.algo.segmentTree;

public class RangeMinQuery {

    public static void main(String[] args) {
        System.out.println("Solving Range Min Query");

        int[] arr = {1,2,-1,5,4,-2,3,1,5,1};

        int[] tree = createSegmentTree(arr);

        System.out.println("size: " + tree.length);

        printArr(tree);

        int min = getMin(tree, 0, 4, 0, arr.length-1, 0);
        System.out.println();
        System.out.println("min=> " + min);
        update(tree, arr.length, -3, 4);
        printArr(tree);
        min = getMin(tree, 0, 4, 0, arr.length-1, 0);
        System.out.println();
        System.out.println("min=> " + min);


    }

    private static int[] createSegmentTree(int[] arr){

        int treeSize = 2*getTreeSize(arr.length)-1;

        int[] tree = new int[treeSize];

        for(int i = 0; i<tree.length; i++){
            tree[i] = Integer.MAX_VALUE;
        }

        fill(arr, tree, 0, 0, arr.length-1);

        return tree;
    }

    private static int fill(int[] arr, int[] tree, int index, int left, int right){

//        System.out.println("index: " + index + ", left: " + left + ", right: " + right);

        if(left == right){
            tree[index] = arr[left];
            return arr[left];
        }

        int mid = (left+right)/2;

//        System.out.println("calling: " + "index: " + (2*index+1) + ", left: " + left + ", right: " + mid);
//        System.out.println("calling: " + "index: " + (2*index+2) + ", left: " + (mid+1) + ", right: " + right);

        int leftMin = fill(arr, tree, 2*index+1, left, mid);
        int rightMin = fill(arr, tree, 2*index+2, mid+1, right);

        tree[index] = Math.min(leftMin, rightMin);

        return tree[index];

    }

    private static void update(int[] tree, int arrSize, int value, int index){
        updateRange(tree, 0, arrSize-1, index, 0, value);
    }

    private static int updateRange(int[] tree, int left, int right, int arrIndex, int index, int value){

        if(left == right){
            tree[index] = value;
            return value;
        }

        int mid = (left + right)/2;
        int leftMin = tree[2*index+1];
        int rightMin = tree[2*index+2];
        if(arrIndex <= mid){
            leftMin = updateRange(tree, left, mid, arrIndex, (2*index+1), value);
        }else{
            rightMin = updateRange(tree, mid+1, right, arrIndex, (2*index+1), value);
        }
        int min = Math.min(leftMin, rightMin);
        tree[index] = min;
        return min;
    }

    private static int getMin(int[] tree, int left, int right, int start, int end, int index){

        if(index >= tree.length){
            return Integer.MAX_VALUE;
        }

        if(left <= start && right >= end){
            return tree[index];
        }
        if(left > end || right  < start){
            return Integer.MAX_VALUE;
        }

        int mid = (start+end)/2;

        int leftMin = getMin(tree, left, right, start, mid, (2*index+1));
        int rightMin = getMin(tree, left, right, mid+1, end, (2*index+2));

        System.out.println("leftMin for range (" + start + ", " + mid + ") is => " + leftMin);
        System.out.println("rightMin for range (" + (mid+1) + ", " + end + ") is => " + rightMin);

        return Math.min(leftMin, rightMin);

    }

    private static int getTreeSize(int arraySize){

        int count = 0;
        while(arraySize > 0){
            arraySize = arraySize>>1;
            count++;
        }
        return (int)Math.pow(2,count);

    }

    private static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
    }
}
