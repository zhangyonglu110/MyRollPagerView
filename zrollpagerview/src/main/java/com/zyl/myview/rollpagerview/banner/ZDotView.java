package com.zyl.myview.rollpagerview.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangyonglu on 2017/11/14 001416:07
 */

public class ZDotView extends View{
    private Paint mpaint;
    public ZDotView(Context context) {
        super(context);
        init();
    }



    public ZDotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mpaint=new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setAntiAlias(true);//消除锯齿
        mpaint.setStyle(Paint.Style.FILL);
    }
   public  void setCircleColor(int colorid){
      mpaint.setColor(getResources().getColor(colorid));
       invalidate();
   }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,mpaint);
    }
}
