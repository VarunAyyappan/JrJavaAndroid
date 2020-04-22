package jrjava.graphicsbasic;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {

    private double x, y; // center
    private double vx, vy;
    private int radius;
    private Paint paint;
    private Deflector deflector;


    public Ball(double x, double y, double vx, double vy, int radius, int color){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;

        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
    }


    public void setDeflector(Deflector deflector){ this.deflector = deflector; }

    public double getX(){ return x; }
    public double getY(){ return y; }
    public int getRadius(){ return radius; }
    public double getVY(){ return vy; }

    public void setY(double y){ this.y = y; }
    public void setVY(double vy){ this.vy = vy; }


    public void move(){
        int numOfMoves = 1 + (int)(Math.sqrt(vx*vx + vy*vy)/deflector.getHeight());

        for(int i=0; i<numOfMoves; i++) {
            x += vx/numOfMoves;
            y += vy/numOfMoves;

            if (x <= Wall.getLeft() + radius) {
                x = Wall.getLeft() + radius;
                vx *= -1;
            } else if (x >= Wall.getRight() - radius) {
                x = Wall.getRight() - radius;
                vx *= -1;
            }
            if (y <= Wall.getTop() + radius) {
                y = Wall.getTop() + radius;
                vy *= -1;
            }

            if (x >= deflector.getX() - radius && x <= deflector.getX() + deflector.getWidth() + radius &&
                    y >= deflector.getY() - radius && y <= deflector.getY() + deflector.getHeight()) {
                y = deflector.getY() - radius;
                vy *= -1;
            }

            BubbleManager.isHit(this);
        }
    }

    public void draw(Canvas c){
        c.drawCircle((float)x, (float)y, radius, paint);
    }

}
















