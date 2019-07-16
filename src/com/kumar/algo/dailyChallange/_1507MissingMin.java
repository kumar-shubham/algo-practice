package com.kumar.algo.dailyChallange;

/*

Given an array of integers, find the first missing positive integer in
linear time and constant space. In other words, find the lowest positive
integer that does not exist in the array. The array can contain duplicates
and negative numbers as well.

For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.

You can modify the input array in-place.

*/


public class _1507MissingMin {

    public static void main(String[] args) {

        int arr[] = {-1, -1, -1};
        int ans = solve(arr);
        System.out.println(ans);

    }

    private static int solve(int[] arr){

        for(int i = 0; i<arr.length; i++){
            if(arr[i] < 0){
                arr[i] = 0;
            }
        }

        for(int i = 0; i<arr.length; i++){
            printArr(arr);
            int num = arr[i];

            if(num <= arr.length){
                if(num == (i+1) || num == 0){
                    continue;
                }

                if(num < 0){
                    num = num*-1;
                    num = num^(i+1);
                }
                System.out.println("num at index " + i + " is: " + num);

                int numAtNumIndex = arr[num-1];
                if(numAtNumIndex == num){
                    continue;
                }
                if(num <= i){
                    arr[num-1] = num;
                }
                else if(numAtNumIndex > 0 && numAtNumIndex <= arr.length){
                    numAtNumIndex = numAtNumIndex ^ num;
                    arr[num-1] = -1*numAtNumIndex;
                }
                else{
                    arr[num-1] = num;
                }
            }
        }

        printArr(arr);

        for(int i = 0; i<arr.length; i++){
            if(arr[i] >= 0 && i != arr[i]-1){
                return i+1;
            }
        }


        return arr.length+1;
    }

    public static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
