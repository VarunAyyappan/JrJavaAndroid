package jrjava.graphicsbasic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Deflector {

    private int x, y; // left, top
    private int width, height;
    private Paint paint;
    private int dx, dy;
    private int lastX, lastY;
    private int yBaseline, yBaselineLow;
    private double momentum, maxMomentum = 10;
    private Ball[] balls;


    public Deflector(int x, int y, int yBaselineLow, int width, int height, int color){
        this.x = x;
        this.y = y;
        yBaseline = y;
        this.yBaselineLow = yBaselineLow;
        this.width = width;
        this.height = height;

        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }


    public void setBalls(Ball[] balls){ this.balls = balls; }


    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }


    public void fingerPressed(MotionEvent e){
        lastX = (int) e.getX();
        lastY = (int) e.getY();
    }


    public void fingerDragged(MotionEvent e){
        dx = (int) e.getX() - lastX;
        dy = (int) e.getY() - lastY;
        x += dx;
        y += dy;
        lastX = (int) e.getX();
        lastY = (int) e.getY();

        if(x<Wall.getLeft()) x = Wall.getLeft();
        else if(x>Wall.getRight()-width) x = Wall.getRight()-width;

        if(y<yBaseline) y = yBaseline;
        else if(y>yBaselineLow) y = yBaselineLow;
    }


    public void fingerReleased(MotionEvent e){

        momentum = (y-yBaseline)*maxMomentum/(yBaselineLow-yBaseline);

        for(int i=0; i<balls.length; i++){
            if(balls[i].getX()>=x-balls[i].getRadius() &&
                    balls[i].getX()<=x+width+balls[i].getRadius() &&
                    balls[i].getY()>=yBaseline-balls[i].getRadius() &&
                    balls[i].getY()<=y+height){

                balls[i].setY(yBaseline-balls[i].getRadius());
                balls[i].setVY(-Math.abs(balls[i].getVY()) - momentum);
            }
        }

        y = yBaseline;
        lastY = yBaseline;
    }


    public void draw(Canvas c){
        c.drawRect(x, y, x+width, y+height, paint);
    }
}



















