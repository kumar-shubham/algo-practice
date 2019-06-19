package com.kumar.algo.foobar.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheAccessCodes {

    public static void main(String[] args) {

        int[] list = {1, 2, 3, 4, 5, 6};
        int result = solution(list);
        System.out.println(result);

    }

    public static int solution(int[] l) {

        Map<Integer, Map<Integer, List<Number>>> map = new HashMap<>();

        for(int i = 0; i<l.length; i++){
            Map<Integer, List<Number>> listMap = map.get(l[i]);
            if(listMap == null){
                listMap = new HashMap<>();
            }

            List<Number> list = listMap.get(i);
            if(list == null){
                list = new ArrayList<>();
            }
            for(int j = i+1; j<l.length; j++){
                if(l[j] % l[i] == 0){
                    list.add(new Number(l[j], j));
                }
            }
            if(list.size() > 0){
                listMap.put(i, list);
                map.put(l[i], listMap);
            }
        }

        int count = 0;

        for(Map.Entry<Integer, Map<Integer, List<Number>>> entry : map.entrySet()){
            int key = entry.getKey();
            Map<Integer, List<Number>> listMap = entry.getValue();

            for(Map.Entry<Integer, List<Number>> listEntry : listMap.entrySet()){
                int index = listEntry.getKey();
                List<Number> list = listEntry.getValue();

                for(Number number : list){
                    Map<Integer, List<Number>> listMap1 = map.get(number.value);
                    if(listMap1 == null){
                        continue;
                    }
                    for(int valueIndex : listMap1.keySet()){
                        if(valueIndex == number.index){
                            count += listMap1.get(valueIndex).size();
                        }
                    }
                }
            }
        }

        return count;


    }

    static class Number{
        int value;
        int index;

        Number(int value, int index){
            this.value = value;
            this.index = index;
        }
        public String toString(){
            return "("+value+ " at " + index + " )" ;
        }
    }
}
