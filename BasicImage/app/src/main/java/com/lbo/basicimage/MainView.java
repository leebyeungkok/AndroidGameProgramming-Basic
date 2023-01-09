package com.lbo.basicimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class MainView extends View {
    public MainView(Context context){
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        /*
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie1);
        canvas.drawBitmap(bitmap, canvas.getWidth()/2 - bitmap.getWidth()/2,
                canvas.getHeight()/2 - bitmap.getHeight()/2, paint);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie1);
        Bitmap bitmapScale = Bitmap.createScaledBitmap(bitmap, 150, 150, false);
        canvas.drawBitmap(bitmapScale, canvas.getWidth()/2 - bitmapScale.getWidth()/2,
                canvas.getHeight()/2 - bitmapScale.getHeight()/2, paint);

         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.zombie1);
        Matrix matrix = new Matrix();
        matrix.preScale(-1,1);
        matrix.preRotate(90);
        Bitmap bitmapMatrix = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

        canvas.drawBitmap(bitmapMatrix, canvas.getWidth()/2 - bitmapMatrix.getWidth()/2,
                canvas.getHeight()/2 - bitmapMatrix.getHeight()/2, paint);


    }

}
