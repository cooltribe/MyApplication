package com.searun.surfaceviewdemo2;

import android.graphics.Bitmap;

/**
 * Created by 陈玉柱 on 2015/8/19.
 * FrameAnimation根据每一帧的显示时间返回当前的图片帧，若没有超过指定的时间则继续返回当前帧，否则返回下一帧。
 */
public class FrameAnimation {
    /**动画显示的资源**/
    private Bitmap[] bitmaps;
    /**动画每帧显示的时间**/
    private int[] duration;
    /**动画上一帧显示的时间*/
    protected  Long lastBitmapTime;
    /**动画显示的索引值，防止数组越界*/
    protected int step;
    /**动画是否重复播放*/
    protected  boolean repeat;
    /**动画重复播放的次数*/
    protected int repeatCount;

    /**
     *
     * @param bitmaps 显示的图片
     * @param duration  图片显示的时间
     * @param repeat  是否重复动画过程
     */
    public FrameAnimation(Bitmap[] bitmaps, int[] duration, boolean repeat) {
        this.bitmaps = bitmaps;
        this.duration = duration;
        this.repeat = repeat;
        lastBitmapTime = null;
        step = 0;
    }
    public Bitmap nextFrame(){
        //判断step是否越界
        if (step >= bitmaps.length){
            //如果不是无限循环
            if (!repeat){
                return  null;
            } else {
                lastBitmapTime = null;
            }
        }
        if (null == lastBitmapTime){
            //第一次执行
            lastBitmapTime = System.currentTimeMillis();
            return bitmaps[step = 0];
        }
        //第X次执行
        long nowTime = System.currentTimeMillis();
        if (nowTime - lastBitmapTime <= duration[step]){
            // 如果还在duration的时间段内,则继续返回当前Bitmap
            // 如果duration的值小于0,则表明永远不失效,一般用于背景
            return bitmaps[step];
        }
        lastBitmapTime = nowTime;
        return bitmaps[step++];
    }
}
