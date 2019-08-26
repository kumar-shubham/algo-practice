package com.kumar.algo.leetcode;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        String s4 = "aabac";
        String s5 = "tmmzuxt";

        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));
        System.out.println(lengthOfLongestSubstring(s4));
        System.out.println(lengthOfLongestSubstring(s5));
    }

    public static int lengthOfLongestSubstring(String s) {

        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }

        Map<Character, Integer> characterMap  = new HashMap<>();

        int maxLength = 1;
        int start = 0;
        characterMap.put(s.charAt(0), 0);
        for(int i = 1; i<s.length(); i++){
            char c = s.charAt(i);
            if(characterMap.containsKey(c) && characterMap.get(c) >= start){
                start = characterMap.get(c)+1;
                characterMap.put(c, i);
            }
            else{
                characterMap.put(c, i);
                maxLength = Math.max(maxLength, i-start+1);
                //System.out.println(start + " :: " + i + " :: " + maxLength);
            }
        }

        return maxLength;
    }
}
