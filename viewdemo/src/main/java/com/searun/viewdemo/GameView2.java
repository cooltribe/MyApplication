package com.searun.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 陈玉柱 on 2015/8/18.
 * 直接在UI线程中调用invalidate()
 */
public class GameView2 extends View {
    private int cx;
    private int cy;
    private Paint p;
    public GameView2(Context context) {
        super(context);
        this.cx = 30;
        this.cy = 30;
        this.p = new Paint();
        p.setColor(Color.RED);
    }

    public GameView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cy,50,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
//                changePosition(x,y);
                GameThread gameThread = new GameThread(x,y);
                gameThread.start();
                return  true;
        }
        return super.onTouchEvent(event);

    }
    private void changePosition(int x, int y){
        this.cx = x;
        this.cy = y;
//        this.invalidate();

    }
    private class GameThread extends Thread{
        private int x;
        private int y;

        public GameThread(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            changePosition(x,y);
            postInvalidate();
        }
    }
}
