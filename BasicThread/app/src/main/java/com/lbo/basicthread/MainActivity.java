package com.lbo.basicthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private long mCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread1 = new Thread(){
            public void run(){
                while(true){
                    try{
                        Thread.sleep(2000);
                        myMethod(1);
                        Log.i("", "쓰레드1");
                    }catch(Exception ex){}
                }
            }
        };
        thread1.start();

        Thread thread2 = new Thread(){
            public void run(){
                while(true){
                    try{
                        Thread.sleep(3000);
                        myMethod(2);
                        Log.i("", "쓰레드2");
                    }catch(Exception ex){}
                }
            }
        };
        thread2.start();

    }
    public synchronized void myMethod(int i) throws Exception {
        mCount = mCount+1;
        Thread.sleep(2000);
        Log.i("", mCount + " " + i+ "에서 호출됨");
    }

}