package com.kumar.algo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        String s = "abcdefghi";
        printAnagrams(s, "");
    }

    private static void printAnagrams(String string,  String ans){

        if(string.length() == 0){
            System.out.print(ans + " ");
        }

        boolean[] arr = new boolean[26];

        for(int i = 0; i<string.length(); i++){

            char c = string.charAt(i);
            if(!arr[c - 'a']){
                String newStr = string.substring(0, i) + string.substring(i+1, string.length());
                printAnagrams(newStr, ans + c);
                arr[c - 'a'] = true;
            }
        }
    }
}