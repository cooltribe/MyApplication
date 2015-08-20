package com.searun.surfaceviewdemo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;

/**
 * Created by 陈玉柱 on 2015/8/19.
 */
public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    public static  int SCREEN_WIDTH;
    public static  int SCREEN_HEIGHT;
    private Context mContext;
    private SurfaceHolder mHolder;
    //最大帧数 (1000 / 30)
    private static final int DRAW_INTERVAL = 30;

    private DrawThread mDrawThread;
    private FrameAnimation[] spriteAnimations;

    private Sprite mSprite;
    private int spriteWidth;
    private int spriteHeight;
    private float spriteSpeed = (float) ((500 * SCREEN_WIDTH / 480) * 0.001);

    private int row = 4;
    private int col = 4;


    public GameSurfaceView(Context context) {
        super(context);
        this.mContext = context;
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        initResources();
        mSprite = new Sprite(0,0,spriteWidth,spriteHeight,spriteSpeed,spriteAnimations);

    }

    private void initResources(){
        Bitmap[][] spriteImgs = generateBitmapArray(mContext, R.mipmap.sprite,row,col);
        spriteAnimations = new FrameAnimation[row];
        for (int i = 0; i < row ; i++) {
            Bitmap[] spriteImg = spriteImgs[i];
            FrameAnimation spriteAnimation = new FrameAnimation(spriteImg,new int[]{150,150,150,150},true);
            spriteAnimations[i] = spriteAnimation;
        }
    }
    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Bitmap decodeBitmapFromRes(Context context,int resourseId){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        InputStream inputStream = context.getResources().openRawResource(resourseId);
        return  BitmapFactory.decodeStream(inputStream,null,options);

    }
    public Bitmap createBitmap(Context context,Bitmap source,int row, int col ,int rowTotal,int colTotal){
        Bitmap bitmap = Bitmap.createBitmap(source,(col-1) * source.getWidth() / colTotal,
                (row - 1) * source.getHeight() / rowTotal,source.getWidth() / colTotal,
                source.getHeight() / rowTotal);

        return bitmap;
    }
    public Bitmap[][] generateBitmapArray(Context context,int resourceId,int row, int col){
        Bitmap bitmap[][] = new Bitmap[row][col];
        Bitmap source = decodeBitmapFromRes(context,resourceId);
        this.spriteWidth = source.getWidth() / 4;
        this.spriteHeight = source.getHeight() / 4;
        for (int i = 1; i <= row ; i++) {
            for (int j = 1; j <= col ; j++) {
                bitmap[i-1][j-1] = createBitmap(context,source,i,j,row,col);
            }
        }
        if (null != source && source.isRecycled()){
            source.recycle();
            source = null;
        }
        return bitmap;
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
            if (null == mDrawThread){
                mDrawThread = new DrawThread();
                mDrawThread.start();
            }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (null != mDrawThread){
            mDrawThread.stopThread();
        }

    }
    private class DrawThread extends Thread{
        public boolean isRunning = false;

        public DrawThread() {
            this.isRunning = true;
        }
        public void stopThread(){
            isRunning = false;
            boolean workIsNotFinish = true;
            while (workIsNotFinish){
                try {
                    this.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                workIsNotFinish = false;
            }
        }

        @Override
        public void run() {
            long deltaTime = 0;
            long tickTime = 0;
            tickTime = System.currentTimeMillis();
            while (isRunning){
                Canvas canvas = null;
                try {
                    synchronized (mHolder){
                        canvas = mHolder.lockCanvas();
                        //设置方向
                        mSprite.setDirection();
                        //更新精灵位置
                        mSprite.updatePosition(deltaTime);
                        drawSprite(canvas);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != mHolder){
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
                deltaTime = System.currentTimeMillis() - tickTime;
                if (deltaTime < DRAW_INTERVAL){
                    try {
                        Thread.sleep(DRAW_INTERVAL - deltaTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                tickTime = System.currentTimeMillis();
            }
        }
    }
    private void drawSprite(Canvas canvas){
        //清屏操作
        canvas.drawColor(Color.BLACK);
        mSprite.draw(canvas);
    }
}
