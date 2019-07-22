package com.kumar.algo.dailyChallange;

/*

Given an array of integers, return a new array such that each element at
index i of the new array is the product of all the numbers in the original
array except the one at i.

For example, if our input was [1, 2, 3, 4, 5], the expected output would
be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output
would be [2, 3, 6].

*/

public class _1307MultiplicationWithoutDivision {

    public static void main(String[] args) {

        int[] arr = new int[60];
        for(int i = 0; i<60;i++){
            arr[i] = 2;
        }
        long start1 = System.currentTimeMillis();
        long[] result = solve(arr);
        long end1 = System.currentTimeMillis();
        printArr(result);
        long start2 = System.currentTimeMillis();
        long[] result1 = solve1(arr);
        long end2 = System.currentTimeMillis();
        printArr(result1);

        System.out.println(start1 + ", " + end1 + ", " + start2 + ", " + end2);

        System.out.println("time taken by solve : " + (end1-start1));
        System.out.println("time taken by solve1: " + (end2-start2));

    }

    private static long[] solve1(int[] arr){

        long mult = arr[0];

        int zeroCount = 0;
        int zeroIndex = -1;

        long[] result = new long[arr.length];

        for(int i = 1; i< arr.length; i++){
            if(arr[i] == 0){
                if(zeroCount > 0){
                    return result;
                }
                else{
                    zeroCount++;
                    zeroIndex = i;
                }
            }
            else{
                mult *= arr[i];
            }
        }

        if(zeroCount == 1){
            result[zeroIndex] = mult;
            return result;
        }

        for(int i = 0; i< arr.length; i++){
            result[i] = divide(mult, arr[i]);
        }
        return result;
    }

    //without division using DP
    private static long[] solve(int[] arr){

        long[][] mult = new long[arr.length][arr.length];

        for(int i = 0; i<arr.length; i++){
            mult[i][i] = arr[i];
            for(int j = i+1; j<arr.length; j++){
                mult[i][j] = mult[i][j-1]*arr[j];
            }
        }

        long[] result = new long[arr.length];

        for(int i = 0; i<arr.length; i++){
            if(i == 0){
                result[i] = mult[1][arr.length-1];
            }
            else if(i == arr.length-1){
                result[i] = mult[0][i-1];
            }
            else{
                result[i] = mult[0][i-1]*mult[i+1][arr.length-1];
            }
        }

        return result;
    }

    private static long divide(long dividend, long divisor) {

        // Calculate sign of divisor
        // i.e., sign will be negative
        // only iff either one of them
        // is negative otherwise it
        // will be positive
        long sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;

        // remove sign of operands
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        // Initialize the quotient
        long quotient = 0, temp = 0;

        // test down from the highest
        // bit and accumulate the
        // tentative value for
        // valid bit
        // 1<<31 behaves incorrectly and gives Integer
        // Min Value which should not be the case, instead
        // 1L<<31 works correctly.
        for (int i = 61; i >= 0; --i) {

            if (temp + (divisor << i) <= dividend) {
                temp += divisor << i;
                quotient |= 1L << i;
            }
        }

        return (sign * quotient);
    }

    private static void printArr(long[] arr){
        for(long i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
