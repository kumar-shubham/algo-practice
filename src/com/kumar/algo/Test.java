package com.kumar.algo;

public class Test {

    public static void main(String[] args) {

        int dimensions[] = {3,2};
        int distance = 4;

        int parallelDimensionX = (2 * (distance / dimensions[0])) + 1;
        int parallelDimensionY = (2 * (distance / dimensions[1])) + 1;

        System.out.println(parallelDimensionX + ", " + parallelDimensionY);
    }
}

