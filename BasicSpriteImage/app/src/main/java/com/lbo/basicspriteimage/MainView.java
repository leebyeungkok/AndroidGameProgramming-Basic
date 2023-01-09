package com.lbo.basicspriteimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;

public class MainView extends View {
    Bitmap bitmap;
    int bitmapWidth = 183;
    int bitmapHeight = 268;
    float scale;
    public MainView(Context context){
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie_sprite);
        scale = context.getResources().getDisplayMetrics().density;
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.YELLOW);
        /*
        canvas.drawBitmap(bitmap,
                new Rect(0,0, (int)(bitmapWidth * scale), (int)(bitmapHeight * scale)),
                new Rect(0,0, (int)(bitmapWidth * scale), (int)(bitmapHeight * scale)),
                null);

         */
        canvas.drawBitmap(bitmap,
                new Rect((int)(bitmapWidth * scale), 0, (int)(bitmapWidth * scale)*2, (int)(bitmapHeight * scale)*1),
                new Rect((int)(bitmapWidth * scale), 0, (int)(bitmapWidth * scale)*2, (int)(bitmapHeight * scale)*1),
                null);
    }
}
