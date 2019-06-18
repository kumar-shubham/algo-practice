import java.util.Map;

public class BringingAGunToAGuradFight {

    public static void main(String[] args) {

        int[] dimension = {3,2};
        int[] yp = {1,1};
        int[] gp = {2,1};
        int distance = 4;

        int result = solution(dimension, yp, gp, distance);

        System.out.println(result);

    }



    public static int solution(int[] dimensions, int[] your_position, int[] guard_position, int distance) {

        int xDim = distance / dimensions[0];
        int yDim = distance / dimensions[1];

        for(int i = -yDim; i<= yDim; i++){

            for(int j = -xDim; j<= xDim; j++){

                System.out.println("Getting box for xDim: " + j + " and yDim: " + i);

                Box box = getBox(j, i, dimensions, your_position, guard_position);
                System.out.println(box);
                System.out.println();

            }

        }

        return 0;
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

        System.out.println(xMul + ", " + yMul);

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
                "corner2: " + corner2 + "\n";
    }

}

class Point{
    int x;
    int y;

    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
