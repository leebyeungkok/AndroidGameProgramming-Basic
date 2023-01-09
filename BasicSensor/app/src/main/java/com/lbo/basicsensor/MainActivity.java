package com.lbo.basicsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private Sensor orieSensor = null;
    private Sensor acceSensor = null;
    private MainView mMainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainView = new MainView(this);
        setContentView(mMainView);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        acceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        orieSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    }
    @Override
    protected void onStart(){
        super.onStart();
        if(acceSensor != null)
            sensorManager.registerListener(this,acceSensor, SensorManager.SENSOR_DELAY_GAME);
        if(orieSensor != null)
            sensorManager.registerListener(this,orieSensor, SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop(){
        if(acceSensor != null || orieSensor != null)
            sensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onDestroy(){
        if(acceSensor != null || orieSensor != null)
            sensorManager.unregisterListener(this);
        super.onDestroy();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}
    @Override
    public void onSensorChanged(SensorEvent event){
        synchronized(this){
            if(event.sensor.getType()== Sensor.TYPE_ORIENTATION){
                mMainView.moveSensorOrientation(event.values[0],event.values[1], event.values[2]);
            }
            if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
                mMainView.moveSensorAccelerometer(event.values[0],event.values[1], event.values[2]);
            }
            mMainView.invalidate();
        }
    }

}