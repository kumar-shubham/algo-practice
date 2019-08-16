package com.kumar.algo.dailyChallange;

import java.util.HashMap;
import java.util.Map;

public class _2407LongestSubstring {

    public static void main(String[] args) {

        String result = solve("abcbbbbbaaaaaaaaaa", 3);
        System.out.println(result);

    }

    private static String solve(String s, int k){

        if(k == 0){
            return "";
        }

        Map<Character, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;
        int start = 0;
        int end = 0;
        for(; j<s.length(); j++){

            char c = s.charAt(j);

            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }
            else{
                map.put(c, 1);
            }

            while (map.size() > k){
                char d = s.charAt(i);
                if(map.get(d) == 1){
                    map.remove(d);
                }else{
                    map.put(d, map.get(d)-1);
                }
                i++;
            }

            if(map.size() == k){
                if(j-i > end-start){
                    start = i;
                    end = j;
                }
            }

        }

        System.out.println(map);
        System.out.println(start + " :: " + end);

        if(map.size() < k){
            return "";
        }

        return s.substring(start, end+1);
    }
}
