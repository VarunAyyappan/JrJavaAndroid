package jrjava.graphicsbasic;

import android.graphics.Canvas;
import android.graphics.Color;
import java.util.ArrayList;

public class BubbleManager {

    private static ArrayList<Bubble> bubbles;
    private static int[] colors;

    static{
        bubbles = new ArrayList<Bubble>();
        colors = new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.YELLOW, Color.CYAN};
    }


    public static void create(){

        if(Math.random()>0.2) return;

        int radius = 30 + (int)(51*Math.random());
        int x = Wall.getLeft()+radius + (int)((Wall.getRight()-Wall.getLeft()-2*radius)*Math.random());
        int y = Wall.getTop()+radius + (int)((Wall.getBottom()-Wall.getTop()-2*radius)*0.5*Math.random());
        int color =  colors[(int)(Math.random()*colors.length)];

        bubbles.add(new Bubble(x, y, radius, color));
    }


    public static void isHit(Ball ball){
        for(int i=0; i<bubbles.size(); i++){
            if(bubbles.get(i).isHit(ball)){
                bubbles.remove(i);
            }
        }
    }

    public static void draw(Canvas c){
        for(int i=0; i<bubbles.size(); i++) bubbles.get(i).draw(c);
    }

}















