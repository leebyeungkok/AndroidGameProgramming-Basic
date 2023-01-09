package com.lbo.basicwidgetuse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = (Button)findViewById(R.id.button_send);
        btnSend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText etInput = (EditText)findViewById(R.id.edittext_input);
                TextView tvOutput = (TextView)findViewById(R.id.textview_output);
                tvOutput.setText(etInput.getText());
            }

        });
    }
}