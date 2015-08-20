package com.searun.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 陈玉柱 on 2015/8/18.
 * 在子线程中间接调用invalidate()
 */
public class GameView1 extends View {
    private int cx;
    private int cy;
    private Paint p;
    public GameView1(Context context) {
        super(context);
        this.cx = 30;
        this.cy = 30;
        this.p = new Paint();
        p.setColor(Color.RED);
    }

    public GameView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(cx,cy,30,p);
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
                GameThread thread = new GameThread(x,y);
                thread.start();
                return  true;
        }
        return super.onTouchEvent(event);

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            changePosition(msg.arg1,msg.arg2);
        }
    };
    private class GameThread extends  Thread{
        private int x;
        private int y;

        public GameThread(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            Message msg = mHandler.obtainMessage();
            msg.arg1 = x;
            msg.arg2 = y;
            msg.sendToTarget();
        }
    }
    private void changePosition(int x, int y){
        this.cx = x;
        this.cy = y;
        this.invalidate();

    }
}
