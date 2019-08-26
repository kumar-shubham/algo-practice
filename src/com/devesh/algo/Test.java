package com.devesh.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Test {

    public static void main(String[] args) {

        List<X> list = new ArrayList<>();
        list.add(new X(1,11));
        list.add(new X(2,12));
        list.add(new X(3,13));
        list.add(new X(4,11));
        list.add(new X(5,12));
        list.add(new X(6,13));

        System.out.println(list);

        Map<Integer, List<Integer>> map = list.stream()
                .collect(groupingBy(X::getValue, Collectors.mapping(
                        X::getId, Collectors.toList())));

        System.out.println(map);

    }


    static class X{
        int id;
        int value;
        public X(int id, int value){
            this.id = id;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public int getValue(){
            return value;
        }

        public String toString(){
            return "(" + id + ", " + value + ") ";
        }
    }

}
