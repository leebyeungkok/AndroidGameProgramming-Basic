package com.lbo.basicpagemove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        Intent intent = getIntent();
        String strParam = intent.getStringExtra("PARAM1");
        TextView tvOutput = (TextView)findViewById(R.id.textview_output);
        tvOutput.setText(strParam);

        Button btnClose = (Button)findViewById(R.id.button_close);
        btnClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }
}