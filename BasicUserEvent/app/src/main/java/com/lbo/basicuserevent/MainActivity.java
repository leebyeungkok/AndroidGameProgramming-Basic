package com.lbo.basicuserevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final static int USER_EVENT = 1000;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnMakeEvent = (Button)findViewById(R.id.button_make_event);
        final EditText etInput = (EditText)findViewById(R.id.edittext_input);
        final TextView tvOutput = (TextView)findViewById(R.id.textview_output);
        btnMakeEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Message msg = handler.obtainMessage();
                msg.obj = etInput.getText().toString();
                msg.what = USER_EVENT;
                handler.sendMessage(msg);
            }
        });
        handler = new Handler(){
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case USER_EVENT:
                        tvOutput.setText((String)msg.obj);
                        break;
                }
            }
        };

    }
}