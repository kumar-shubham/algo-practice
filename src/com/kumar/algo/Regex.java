package com.kumar.algo;

public class Regex {

    public static void main(String[] args) {
        String text = "ab";
        String regex = ".*..c*";
        System.out.println(match(text, regex));
    }

    private static boolean match(String text, String regex){

        System.out.println("text: " + text + ", regex: " + regex);
        if(regex.length() == 0){
            return text.length() == 0;
        }

        if(text.length() == 0){
            if(regex.length() < 2){
                return false;
            }
            if(regex.charAt(1) == '*'){
                return match(text, regex.substring(2));
            }
            return false;
        }

        char t1 = text.charAt(0);
        char r1 = regex.charAt(0);

        if(t1 == r1){
            if(regex.length() > 1 && regex.charAt(1) == '*'){
                char r2 = regex.charAt(1);
                return getRecursiveMatching(text, regex);
            }
            return match(text.substring(1), regex.substring(1));
        }
        else if(r1 == '.'){
            if(regex.length() > 1){
                char r2 = regex.charAt(1);
                if(r2 == '*'){
                    return getRecursiveMatching(text, regex);
                }else{
                    return match(text.substring(1), regex.substring(1));
                }
            }
            return text.length() == regex.length();
        }
        else if(regex.length() > 1 && regex.charAt(1) == '*'){
            return match(text, regex.substring(2));
        }
        return false;
    }

    private static boolean getRecursiveMatching(String text, String regex){
        if(match(text, regex.substring(2))){
            return true;
        }
        else if(match(text.substring(1), regex)){
            return true;
        }else {
            return match(text.substring(1), regex.substring(2));
        }
    }
}
