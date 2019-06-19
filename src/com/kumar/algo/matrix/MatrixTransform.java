package com.kumar.algo.matrix;

import java.util.Arrays;

public class MatrixTransform {

    public static void main(String[] args) {
//        int[][] input = new int[3][3];
//        for(int i=0;i<3;i++)    {
//            for(int j=0;j<3;j++)    {
//
//                int num = (int) Math.round(Math.random()*10) % 10;
//                num = (num==0) ? num+1 : num;
//                input[i][j]=num;
//            }
//        }

//        int[][] input= {{7,7,1},{9,4,9},{9,7,4}};

        int[][] input = {{4,9,2},
                         {3,5,7},
                         {8,1,5}};
        printMatrix(input);
        transform(input);


    }

    private static int transform(int[][] m){

        int[] sum = new int[m.length+m[0].length+2];

        for(int i = 0; i<m.length; i++){
            int[] row = m[i];
            int rowSum = 0;
            for(int j = 0; j<row.length; j++){
                int num = m[i][j];
                rowSum += num;
                sum[m.length+j] += num;
                if(i == j){
                    sum[m.length+row.length] += num;
                }

                if(i+j == m.length-1){
                    sum[m.length+row.length+1] += num;
                }
            }
            sum[i] = rowSum;
        }
        printArr(sum);

        int totalSum = 0;
        for(int i : sum){
            totalSum += i;
        }
        System.out.println(totalSum);

        int avg = totalSum/8;
        System.out.println(avg);

        int cost = 0;
        for(int i : sum){
            cost += Math.abs(avg - i);
        }
        int cost1 = 0;
        avg++;
        for(int i : sum){
            cost1 += Math.abs(avg - i);
        }
        System.out.println(cost);
        System.out.println(cost1);


        return 0;
    }

    private static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] arr) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("------------------------");
        for(int i=0;i<3;i++)    {
            for(int j=0;j<3;j++)    {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

}
