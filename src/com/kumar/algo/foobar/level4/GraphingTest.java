package com.kumar.algo.foobar.level4;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphingTest extends JPanel {

    public static ArrayList<Box> boxList = new ArrayList<>();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        for (Box box : boxList) {
            draw(box.hero, 1, g2d);
            draw(box.villain, 2, g2d);
            draw(box.corner1, 3, g2d);
            draw(box.corner2, 3, g2d);
            draw(box.corner3, 3, g2d);
            draw(box.corner4, 4, g2d);
        }
    }

    private void draw(Point point, int type, Graphics2D g2d){
        if(point == null){
            return;
        }

        if(type == 1){
            g2d.setColor(Color.GREEN);
        }
        else if(type == 2){
            g2d.setColor(Color.red);
        }
        else{
            g2d.setColor(Color.black);
        }

        g2d.drawLine(point.x, point.y, point.x, point.y);

    }

    public static void main(String[] args) {
        GraphingTest points = new GraphingTest();
        JFrame frame = new JFrame("Points");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(points);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

}

