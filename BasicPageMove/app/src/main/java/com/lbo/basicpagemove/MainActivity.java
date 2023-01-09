package com.lbo.basicpagemove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = (Button)findViewById(R.id.button_send);
        EditText etInput = (EditText)findViewById(R.id.edittext_input);
        btnSend.setOnClickListener (new View.OnClickListener(){
            public void onClick(View v) {
                String strInput = etInput.getText().toString();
                Intent intent = new Intent(getApplicationContext(),
                        NewPageActivity.class);
                intent.putExtra("PARAM1", strInput);
                startActivity(intent);
            }
        });
    }
}