package com.devesh.algo.dailyChallange;

public class _1507MinMissingPositiveInteger {
    static int getMinimumPositiveMissingInteger(int [] arr){

        printArr(arr);
        //mark negative ones to int max value
        for(int i=0; i < arr.length; i++){
            if(arr[i] <= 0){
                arr[i] = Integer.MAX_VALUE;
            }
        }

        printArr(arr);

        //update items index's value to their negative
        for(int i=0; i < arr.length; i++){
            int item = arr[i];
            if(item == Integer.MAX_VALUE){
                continue;
            }
            item = Math.abs(item);

            if(item > 0 && item <= arr.length && arr[item-1] > 0){
                arr[item-1] = arr[item-1]*-1;
            }
        }

        printArr(arr);

        //iterate and look for first positive value
        for(int i=0; i < arr.length; i++){
            if(arr[i] >= 0){
                return i+1;
            }
        }

        //if no positive found, array.length+1 would be the minimum positive number
        return arr.length+1;
    }

    public static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void main(String ar[]){
        int [] arr = {1,2,3,3,2,1};
//        int [] arr = {1,2, 0};
//        int [] arr = {1, 2, 3, 4, 6};
        int res = getMinimumPositiveMissingInteger(arr);
        System.out.println(res);
    }
}