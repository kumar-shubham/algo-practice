package com.kumar.algo.foobar.level4;

import java.util.HashMap;
import java.util.Map;

public class BringingAGunToAGuradFight {

    public static void main(String[] args) {

//        int[] dimension = {3,2};
//        int[] yp = {1,1};
//        int[] gp = {2,1};
//        int distance = 10000;

//        int[] dimension = {300,275};
//        int[] yp = {150,150};
//        int[] gp = {185,100};
//        int distance = 500;

//        int[] dimension = {2,5};
//        int[] yp = {1,2};
//        int[] gp = {1,4};
//        int distance = 11;
        //expected answer: 27

//        int[] dimension = {23,10};
//        int[] yp = {6,4};
//        int[] gp = {3,2};
//        int distance = 23;
//        //expected answer: 8

        int[] dimension = {10,10};
        int[] yp = {4,4};
        int[] gp = {3,3};
        int distance = 5000;
        //expected answer: 739323

        long start = System.currentTimeMillis();

        int result = solution(dimension, yp, gp, distance);

        long end = System.currentTimeMillis();

        System.out.println(result);

        System.out.println("total running time in seconds: " + (end-start)/1000.0);

    }


    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

         Map<String, Double> heros = new HashMap<>();
         Map<String, Double> villains = new HashMap<>();
         Map<String, Double> corners = new HashMap<>();

        int xDim = distance / dimensions[0];
        int yDim = distance / dimensions[1];

        Point hero = new Point();
        hero.x = your_position[0];
        hero.y =  your_position[1];
        Point villain = new Point();
        villain.x = guard_position[0];
        villain.y = guard_position[1];

        for(int i = -yDim; i<= yDim; i++){

            for(int j = -xDim; j<= xDim; j++){

//                System.out.println("Getting box for xDim: " + j + " and yDim: " + i);

                Box box = getBox(j, i, dimensions, your_position, guard_position);
//                System.out.println(box);
//                System.out.println();

                double d = getDistance(hero, box.villain);

                if(d < distance){

                    String villainSlope = getSlope(box.villain, hero);

                    if(villains.containsKey(villainSlope)){
                        if(villains.get(villainSlope) > d){
                            villains.put(villainSlope, d);
                        }
                    }
                    else{
                        villains.put(villainSlope, d);
                    }


                    double d1 = getDistance(hero, box.hero);

                    if(d1 != 0){
                        String heroSlope = getSlope(box.hero, hero);
                        if(heros.containsKey(heroSlope)){
                            if(heros.get(heroSlope) > d1){
                                heros.put(heroSlope, d1);
                            }
                        }else{
                            heros.put(heroSlope, d1);
                        }
                    }

                    addCorner(box.corner1, hero, corners);
                    addCorner(box.corner2, hero, corners);
                    addCorner(box.corner3, hero, corners);
                    addCorner(box.corner4, hero, corners);
                }

            }

        }

//        System.out.println(heros);
//        System.out.println(villains);
//        System.out.println(corners);

        System.out.println(heros.size());
        System.out.println(villains.size());
        System.out.println(corners.size());

        int count = villains.size();

        for(Map.Entry<String, Double> entry : villains.entrySet()){
            String slope = entry.getKey();
            Double d = entry.getValue();

            if(heros.containsKey(slope) && heros.get(slope) <= d){
                count--;
            }
            if(corners.containsKey(slope) && corners.get(slope) <= d){
                count--;
            }
        }

        return count;
    }

    private static void addCorner(Point corner, Point hero, Map<String, Double> corners){

        if(corner != null){

            double d = getDistance(hero, corner);

            String slope = getSlope(corner, hero);

            if(corners.containsKey(slope)){
                if(corners.get(slope) > d){
                    corners.put(slope, d);
                }
            }
            else {
                corners.put(slope, d);
            }
        }
    }

    private static String getSlope(Point p1, Point p2){

        int xDiff = p2.x - p1.x;
        int yDiff = p2.y - p1.y;

//        System.out.println("xDiff: " + xDiff + ", yDiff: " + yDiff);

        int gcd = gcd(xDiff, yDiff);

        xDiff = xDiff/gcd;
        yDiff = yDiff/gcd;

        if(xDiff < 0 && yDiff < 0){
            xDiff = Math.abs(xDiff);
            yDiff = Math.abs(yDiff);
        }
        else if((xDiff < 0 && yDiff > 0) || (xDiff > 0 && yDiff < 0)){
            xDiff = Math.abs(xDiff);
            yDiff = -1 * Math.abs(yDiff);
        }

        return yDiff + "/" + xDiff;

    }

    private static int gcd(int n1, int n2){

        if(n1 == 0){
            return n2;
        }
        if(n2 == 0){
            return n1;
        }

        n1 = ( n1 > 0) ? n1 : -n1;
        n2 = ( n2 > 0) ? n2 : -n2;

        while(n1 != n2)
        {
            if(n1 > n2)
                n1 -= n2;
            else
                n2 -= n1;

//            System.out.println("n1: " + n1 + " and n2: " + n2);
        }

        return n1;
    }

    private static double getDistance(Point point1, Point point2){

        return Math.sqrt(Math.pow((point1.x - point2.x),2)
                + Math.pow((point1.y - point2.y), 2));

    }

    private static Box getBox(int i, int j, int[] dimensions, int[] your_position, int[] guard_position) {

        Box box = new Box();

        int xMul = (Math.abs(i))*dimensions[0];
        int yMul = (Math.abs(j))*dimensions[1];
        if(i < 0){
            xMul *= -1;
        }
        if(j<0){
            yMul *= -1;
        }

//        System.out.println(xMul + ", " + yMul);

        if((i & 1) != 0 && i != 0){
            box.hero.x = xMul + (dimensions[0] - your_position[0]);
            box.villain.x = xMul + (dimensions[0] - guard_position[0]);
        }
        else if(i == 0){
            box.hero.x = your_position[0];
            box.villain.x = guard_position[0];
        }
        else{
            box.hero.x = xMul + your_position[0];
            box.villain.x = xMul + guard_position[0];
        }

        if((j & 1) == 0 && j != 0){
            box.hero.y = yMul + (dimensions[1] - your_position[1]);
            box.villain.y = yMul + (dimensions[1] - guard_position[1]);
        }
        else if(j == 0){
            box.hero.y = your_position[1];
            box.villain.y = guard_position[1];
        }
        else{
            box.hero.y = yMul + your_position[1];
            box.villain.y = yMul + guard_position[1];
        }

        if(i > 0){
            xMul += dimensions[0];
        }

        if(i != 0){
            if(j < 0){
                Point corner1 = new Point();
                corner1.x = xMul;
                corner1.y = yMul;
                box.corner1 = corner1;
            }
            else if(j == 0){
                Point corner1 = new Point();
                corner1.x = xMul;
                corner1.y = yMul;
                box.corner1 = corner1;
                Point corner2 = new Point();
                corner2.x = xMul;
                corner2.y = dimensions[1];
                box.corner2 = corner2;
            }
            else{
                Point corner1 = new Point();
                corner1.x = xMul;
                corner1.y = yMul + dimensions[1];
                box.corner1 = corner1;
            }
        }
        else {
            if(j < 0){
                Point corner1 = new Point();
                corner1.x = 0;
                corner1.y = yMul;
                box.corner1 = corner1;
                Point corner2 = new Point();
                corner2.x = dimensions[0];
                corner2.y = yMul;
                box.corner2 = corner2;
            }
            else if(j > 0){
                Point corner1 = new Point();
                corner1.x = 0;
                corner1.y = yMul + dimensions[1];
                box.corner1 = corner1;
                Point corner2 = new Point();
                corner2.x = dimensions[0];
                corner2.y = yMul + dimensions[1];
                box.corner2 = corner2;
            }
            else{
                Point corner1 = new Point();
                corner1.x = 0;
                corner1.y = 0;
                box.corner1 = corner1;
                Point corner2 = new Point();
                corner2.x = 0;
                corner2.y = dimensions[1];
                box.corner2 = corner2;
                Point corner3 = new Point();
                corner3.x = dimensions[0];
                corner3.y = 0;
                box.corner3 = corner3;
                Point corner4 = new Point();
                corner4.x = dimensions[0];
                corner4.y = dimensions[1];
                box.corner4 = corner4;
            }
        }

        return box;

    }
}

class Box{

    Point hero;
    Point villain;
    Point corner1;
    Point corner2;
    Point corner3;
    Point corner4;

    public Box(){
        hero = new Point();
        villain = new Point();
    }

    public String toString(){
        return "hero: " + hero + "\n" +
                "villain: " + villain + "\n" +
                "corner1: " + corner1 + "\n" +
                "corner2: " + corner2 + "\n" +
                "corner3: " + corner3 + "\n" +
                "corner4: " + corner4 + "\n";
    }

}

class Point{
    int x;
    int y;

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
