package com.lbo.basictext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class MainView extends View {
    public MainView(Context context){
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(12);
        canvas.drawText("Canvas Text!!", canvas.getWidth()/2 - 100, canvas.getHeight()/4, paint);
        paint.setTextSize(24);
        Typeface typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD);
        canvas.drawText("Canvas Text!!", canvas.getWidth()/2 - 100, canvas.getHeight()*2/4, paint);

        paint.setTextSize(48);
        canvas.drawText("Canvas Text!!", canvas.getWidth()/2 - 100, canvas.getHeight()*3/4, paint);

    }
}
