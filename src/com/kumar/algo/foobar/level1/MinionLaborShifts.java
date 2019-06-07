package com.kumar.algo.foobar.level1;

import java.util.HashMap;
import java.util.Map;

public class MinionLaborShifts {

    public static void main(String[] args) {

        int[] a = {1, 2, 2, 3, 3, 3, 4, 5, 5};

        int[] result = solution(a, 2);
        printArr(result);


    }

    private static int[] solution(int[] data, int n){
        int[] result;

        if(n == 0){
            result = new int[0];
            return result;
        }

        Map<Integer, Integer> map =  new HashMap<>();

        int resultSize = data.length;
        for(int i : data){
            if(map.containsKey(i)){
                if(map.get(i) >= n){
                    if(map.get(i) == n){
                        resultSize -= n;
                    }
                    resultSize--;
                }
                map.put(i, map.get(i)+1);
            }
            else{
                map.put(i, 1);
            }
        }

        System.out.println("size: " + resultSize);

        result = new int[resultSize];

        int index = 0;
        for(int i: data){
            if(map.get(i) <= n){
                result[index++] = i;
            }
        }

        return result;
    }

    private static void printArr(int[] arr){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
