package jrjava.graphicsbasic;


import android.graphics.Canvas;
import android.graphics.Paint;

public class Wall {

    private static int left, right, top, bottom;
    private static Paint paint;

    static {
        paint = new Paint();
        paint.setColor(0xff888888);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }


    public static void setDimenstion(int _left, int _top, int _right, int _bottom){
        left = _left;
        top = _top;
        right = _right;
        bottom = _bottom;
    }


    public static int getLeft(){ return left; }
    public static int getRight(){ return right; }
    public static int getTop(){ return top; }
    public static int getBottom(){ return bottom; }


    public static void draw(Canvas c){
        c.drawLine(left, top, left, bottom, paint);
        c.drawLine(left, top, right, top, paint);
        c.drawLine(right, top, right, bottom, paint);
    }
}











