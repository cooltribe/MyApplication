package com.searun.viewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.io.InputStream;

/**
 * Created by 陈玉柱 on 2015/8/18.
 * 直接在UI线程中调用invalidate()
 */
public class GameView extends View {
    private int cx;
    private int cy;
    private Paint p;
    public GameView(Context context) {
        super(context);
        this.cx = 30;
        this.cy = 30;
        this.p = new Paint();
        p.setColor(Color.RED);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Bitmap decodeBitmapFromRes(Context context,int resourseId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resourseId);

        return BitmapFactory.decodeStream(is,null,opt);
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
                changePosition(x,y);
                return  true;
        }
        return super.onTouchEvent(event);

    }
    private void changePosition(int x, int y){
        this.cx = x;
        this.cy = y;
        this.invalidate();

    }
}
