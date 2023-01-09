package com.lbo.basiccanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MainView extends View {
    public MainView(Context context){
        super(context);
    }
    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/4,
                canvas.getHeight()/10, paint);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(
                canvas.getWidth()/2 - canvas.getHeight()/10, canvas.getHeight()*2/4 - canvas.getHeight()/10,
                canvas.getWidth()/2 + canvas.getHeight()/10, canvas.getHeight()*2/4 + canvas.getHeight()/10,
                paint);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()*3/4, canvas.getHeight()/10, paint);
    }


}
