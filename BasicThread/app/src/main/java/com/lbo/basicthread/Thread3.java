package com.lbo.basicthread;

import android.util.Log;

public class Thread3 extends Thread{
    public Thread3(){}

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(3000);
                Log.i("", "쓰레드3");
            }catch(Exception ex){}
        }
    }
}
