package jrjava.graphicsbasic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    private Ball[] balls;
    private Deflector deflector;

    private Thread t;

    public GameView(Context context){
        super(context);

        t = new Thread(this);
        SurfaceHolder holder = this.getHolder();
        holder.addCallback(
                new SurfaceHolder.Callback(){
                    public void surfaceCreated(SurfaceHolder holder){ t.start(); }
                    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }
                    public void surfaceDestroyed(SurfaceHolder holder) { }
                }
        );
    }


    public boolean onTouchEvent(MotionEvent e){

        if(e.getAction()==MotionEvent.ACTION_DOWN) deflector.fingerPressed(e);
        else if(e.getAction()==MotionEvent.ACTION_MOVE) deflector.fingerDragged(e);
        else if(e.getAction()==MotionEvent.ACTION_UP) deflector.fingerReleased(e);

        return true;
    }


    public void run(){
        SurfaceHolder holder = this.getHolder();
        Canvas c = holder.lockCanvas();
        int cW = c.getWidth();
        int cH = c.getHeight();
        holder.unlockCanvasAndPost(c);

        int margin = 20;
        Wall.setDimenstion(margin, margin, cW-margin, cH-margin);
        deflector = new Deflector(Wall.getLeft(), cH*7/10, cH*8/10, Wall.getRight()-Wall.getLeft(), cW/40, 0xff000000);

        balls = new Ball[1];
        for(int i=0; i<balls.length; i++) {
            balls[i] = new Ball(cW/2, cH/2, Math.random()*20-10, Math.random()*20-10, 15, 0xff000000 + (int)(0x00ffffff*Math.random()));
            balls[i].setDeflector(deflector);
        }

        deflector.setBalls(balls);


        while(true) {
            for(int i=0; i<balls.length; i++) balls[i].move();
            BubbleManager.create();

            c = holder.lockCanvas();
            c.drawColor(0xffffffff);
            for(int i=0; i<balls.length; i++) balls[i].draw(c);
            deflector.draw(c);
            BubbleManager.draw(c);
            Wall.draw(c);
            holder.unlockCanvasAndPost(c);

            try {
                Thread.currentThread().sleep(30);
            } catch (InterruptedException e) { }
        }
    }



}










