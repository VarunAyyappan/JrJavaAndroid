package jrjava.graphicsbasic;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Bubble {

    private int x, y; // center
    private int radius;
    private Paint paint;
    private int rgbContent;
    private int opacity = 255, opacityChangeRate = -1;


    public Bubble(int x, int y, int radius, int color){
        this.x = x;
        this.y = y;
        this.radius = radius;

        rgbContent = color & 0x00ffffff; //0b00000000111111111111111111111111;

        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }


    public boolean isHit(Ball ball){
        if(opacity<21) return false;

        double distance = Math.sqrt(Math.pow(x-ball.getX(), 2) + Math.pow((y-ball.getY()), 2));
        if(distance<=radius) return true;
        else return false;
    }


    private void changeOpacity(){

        opacity += opacityChangeRate;

        if(opacity<0){
            opacity = 0;
            opacityChangeRate *= -1;
        }
        else if(opacity>255){
            opacity = 255;
            opacityChangeRate *= -1;
        }

        paint.setColor(opacity<<24 | rgbContent);
    }


    public void draw(Canvas c){
        changeOpacity();
        c.drawCircle(x, y, radius, paint);
    }

}









