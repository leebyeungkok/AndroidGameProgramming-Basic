package com.lbo.basicsensor;

import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
public class MainView extends View {
    private float sensorA0=0;
    private float sensorA1=0;
    private float sensorA2=0;
    private float sensorO0=0;
    private float sensorO1=0;
    private float sensorO2=0;

    public MainView(Context context) {
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(36);
        canvas.drawText("Acce 0" + sensorA0, 10, 100, paint);
        canvas.drawText("Acce 1" + sensorA1, 10, 150, paint);
        canvas.drawText("Acce 2" + sensorA2, 10, 200, paint);
        canvas.drawText("Orie 0" + sensorO0, 10, 250, paint);
        canvas.drawText("Orie 1" + sensorO1, 10, 300, paint);
        canvas.drawText("Orie 2" + sensorO2, 10, 350, paint);

    }
    public void moveSensorAccelerometer(float i0, float i1, float i2){
        sensorA0 = i0;
        sensorA1 = i1;
        sensorA2 = i2;
    }
    public void moveSensorOrientation(float i0, float i1, float i2){
        sensorO0 = i0;
        sensorO1 = i1;
        sensorO2 = i2;
    }
}
